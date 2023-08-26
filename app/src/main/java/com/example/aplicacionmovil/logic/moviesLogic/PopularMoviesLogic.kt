package com.example.aplicacionmovil.logic.moviesLogic

import com.example.aplicacionmovil.data.converters.ApiConnection
import com.example.aplicacionmovil.data.endPoints.PopularMovieEndPoint
import com.example.aplicacionmovil.data.entities.movies.popular.getPopularMovie
import com.example.aplicacionmovil.logic.data.PopularMovies

class PopularMoviesLogic {

    suspend fun getAllPopMovies(): ArrayList<PopularMovies> {
        var itemList = arrayListOf<PopularMovies>()

        var response = ApiConnection.getService(
            ApiConnection.typeApi.Movies,
            PopularMovieEndPoint::class.java
        ).getAllPopularMovies()

        if (response.isSuccessful) {
            response.body()!!.results.forEach {
                val m = it.getPopularMovie()
                itemList.add(m)
            }
        }
        return itemList
    }

}