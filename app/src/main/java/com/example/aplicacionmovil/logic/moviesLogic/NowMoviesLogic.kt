package com.example.aplicacionmovil.logic.moviesLogic

import com.example.aplicacionmovil.data.converters.ApiConnection
import com.example.aplicacionmovil.data.endPoints.NowMovieEndPoint
import com.example.aplicacionmovil.data.entities.movies.nowPlaying.database.NowMoviesDB
import com.example.aplicacionmovil.data.entities.movies.nowPlaying.getNowMovie
import com.example.aplicacionmovil.logic.data.NowPlayingMovies
import com.example.aplicacionmovil.logic.data.getNowMoviesDB
import com.example.aplicacionmovil.ui.utilities.AplicacionMovil
import java.lang.Exception
import java.lang.RuntimeException

class NowMoviesLogic {

    suspend fun getAllNowMovies(): ArrayList<NowPlayingMovies> {
        var itemList = arrayListOf<NowPlayingMovies>()

        var response = ApiConnection.getService(
            ApiConnection.typeApi.Movies,
            NowMovieEndPoint::class.java
        ).getAllNowMovies()

        if (response.isSuccessful) {
            response.body()!!.results.forEach {
                val m = it.getNowMovie()
                itemList.add(m)
            }
        }
        return itemList
    }

    fun getAllNowMoviesDB(): List<NowPlayingMovies> {
        val items: ArrayList<NowPlayingMovies> = arrayListOf()
        val items_aux = AplicacionMovil.getdbInstance().nowMoviesDao().getAllMovies()
        items_aux.forEach {
            items.add(
                NowPlayingMovies(
                    it.id,
                    it.title,
                    it.description,
                    it.poster,
                    it.language,
                    it.date,
                    it.vote
                )
            )
        }
        return items
    }

    fun getSavedMovies(): List<NowPlayingMovies> {
        return AplicacionMovil.getdbInstance().nowMoviesDao().getAllMovies()
            .map {
                NowPlayingMovies(
                    it.id,
                    it.title,
                    it.description,
                    it.poster,
                    it.language,
                    it.date,
                    it.vote
                )
            }
    }

    fun insertNowMovietoDB(items: List<NowPlayingMovies>): Boolean {
        var itemsDB = arrayListOf<NowMoviesDB>()
        items.forEach {
            itemsDB.add(it.getNowMoviesDB())
        }
        AplicacionMovil.getdbInstance().nowMoviesDao().insertMovie(itemsDB)
        return false
    }

}