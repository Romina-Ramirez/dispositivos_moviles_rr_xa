package com.example.aplicacionmovil.logic.marvelLogic

import com.example.aplicacionmovil.data.converters.ApiConnection
import com.example.aplicacionmovil.data.endPoints.MarvelEndpoint
import com.example.aplicacionmovil.data.entities.marvel.characters.database.MarvelCharsDB
import com.example.aplicacionmovil.data.entities.marvel.characters.getMarvelChar
import com.example.aplicacionmovil.logic.data.MarvelChars
import com.example.aplicacionmovil.logic.data.getMarvelCharsDB
import com.example.aplicacionmovil.ui.utilities.AplicacionMovil
import java.lang.Exception
import java.lang.RuntimeException

class MarvelCharactersLogic {

    suspend fun getMarvelChars(name: String, limit: Int): ArrayList<MarvelChars> {
        var itemList = arrayListOf<MarvelChars>()

        var response = ApiConnection.getService(
            ApiConnection.typeApi.Marvel,
            MarvelEndpoint::class.java
        ).getCharactersStartWith(name, limit)

        if (response.isSuccessful) {
            response.body()!!.data.results.forEach {
                val m = it.getMarvelChar()
                itemList.add(m)
            }
        }
        return itemList
    }

    suspend fun getAllMarvelChars(offset: Int, limit: Int): ArrayList<MarvelChars> {
        var itemList = arrayListOf<MarvelChars>()

        var response = ApiConnection.getService(
            ApiConnection.typeApi.Marvel,
            MarvelEndpoint::class.java
        ).getAllMarvelChars(offset, limit)

        if (response.isSuccessful) {
            response.body()!!.data.results.forEach {
                val m = it.getMarvelChar()
                itemList.add(m)
            }
        }
        return itemList
    }

    suspend fun getAllMarvelChardDB(): List<MarvelChars> {
        val items: ArrayList<MarvelChars> = arrayListOf()
        val items_aux = AplicacionMovil.getdbInstancs().marvelDao().getAllCharacters()
        items_aux.forEach {
            items.add(
                MarvelChars(
                    it.id, it.name, it.comic, it.image
                )
            )
        }
        return items
    }

    suspend fun getSavedMarvelChars(): List<MarvelChars> {
        return AplicacionMovil.getdbInstancs().marvelDao().getAllCharacters()
            .map {
                MarvelChars(
                    it.id,
                    it.name,
                    it.comic,
                    it.image
                )
            }
    }

    suspend fun getInitChars(limit: Int, offset: Int): MutableList<MarvelChars> {
        var items = mutableListOf<MarvelChars>()
        try {
            items = MarvelCharactersLogic().getAllMarvelChardDB().toMutableList()

            if (items.isEmpty()) {
                items = MarvelCharactersLogic().getAllMarvelChars(offset, limit)
                MarvelCharactersLogic().insertMarvelChartstoDB(items)
            }
            items
        } catch (ex: Exception) {
            throw RuntimeException(ex.message)
        } finally {
            return items
        }
    }

    suspend fun insertMarvelChartstoDB(items: List<MarvelChars>): Boolean {
        var itemsDB = arrayListOf<MarvelCharsDB>()
        items.forEach {
            itemsDB.add(it.getMarvelCharsDB())
        }
        AplicacionMovil
            .getdbInstancs()
            .marvelDao()
            .insertMarvelChar(itemsDB)
        return false
    }


}