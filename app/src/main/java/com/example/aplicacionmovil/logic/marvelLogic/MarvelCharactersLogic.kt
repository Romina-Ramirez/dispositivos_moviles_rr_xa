package com.example.aplicacionmovil.logic.marvelLogic

import com.example.aplicacionmovil.data.converters.ApiConnection
import com.example.aplicacionmovil.data.endPoints.MarvelEndpoint
import com.example.aplicacionmovil.data.marvel.MarvelChars

class MarvelCharactersLogic {

    suspend fun getMarvelChars(name: String, limit: Int): ArrayList<MarvelChars> {
        var itemList = arrayListOf<MarvelChars>()

        var response = ApiConnection.getService(
            ApiConnection.typeApi.Marvel,
            MarvelEndpoint::class.java
        ).getCharactersStartWith(name, limit)

        if (response.isSuccessful) {
            response.body()!!.data.results.forEach {
                var comic: String = "No available"
                if (it.comics.items.size > 0) {
                    comic = it.comics.items[0].name
                }
                val m = MarvelChars(
                    it.id,
                    it.name,
                    comic,
                    it.thumbnail.path + "." + it.thumbnail.extension
                )
                itemList.add(m)
            }
        }
        return itemList
    }
}