package com.example.aplicacionmovil.data.converters

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiConnection {

    fun getJcanConnnection(): Retrofit {
        var retrofit = Retrofit.Builder()
            .baseUrl("https://api.jikan.moe/v4/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

}