package com.example.aplicacionmovil.logic.jikanLogic

import com.example.aplicacionmovil.data.converters.ApiConnection
import com.example.aplicacionmovil.data.endPoints.JikanEndpoint
import com.example.aplicacionmovil.logic.data.MarvelChars

class JikanAnimeLogic {

    suspend fun getAllAnimes(): List<MarvelChars> {
        var itemList = arrayListOf<MarvelChars>()

        var response = ApiConnection.getService(
            ApiConnection.typeApi.Jikan,
            JikanEndpoint::class.java
        ).getAllAnimes() //endpoint base al especifico

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