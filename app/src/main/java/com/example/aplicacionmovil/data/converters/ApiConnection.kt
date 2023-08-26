package com.example.aplicacionmovil.data.converters

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConnection {

    enum class typeApi { Movies }

    private val API_MOVIES = "https://api.themoviedb.org/3/movie/"

    private fun getConnnection(base: String): Retrofit {
        return Retrofit.Builder()
            .baseUrl(base)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun <T, E : Enum<E>> getService(api: E, service: Class<T>): T {
        var BASE = ""
        when (api.name) {
            typeApi.Movies.name -> {
                BASE = API_MOVIES
            }
        }
        return getConnnection(BASE).create(service)
    }

}