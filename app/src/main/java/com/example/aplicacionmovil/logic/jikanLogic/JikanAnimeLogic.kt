package com.example.aplicacionmovil.logic.jikanLogic

import com.example.aplicacionmovil.data.converters.ApiConnection
import com.example.aplicacionmovil.data.endPoints.JikanEndpoint
import com.example.aplicacionmovil.data.marvel.MarvelChars

class JikanAnimeLogic {

    suspend fun getAllAnimes(): List<MarvelChars> {
        var call = ApiConnection.getJcanConnnection() //llamamos al endpoint base
        val response = call.create(JikanEndpoint::class.java).getAllAnimes() //endpoint base al especifico
        var itemList = arrayListOf<MarvelChars>()
        if (response.isSuccessful) {
            response.body()!!.data.forEach {
                val m = MarvelChars(
                    it.mal_id,
                    it.title,
                    it.titles[0].title,
                    it.images.jpg.image_url
                )
                itemList.add(m)
            }
        }
        return itemList
    }
}