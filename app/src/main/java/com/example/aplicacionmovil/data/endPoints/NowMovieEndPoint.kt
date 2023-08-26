package com.example.aplicacionmovil.data.endPoints

import com.example.aplicacionmovil.data.entities.movies.nowPlaying.NowMovies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NowMovieEndPoint {

    @GET("now_playing")
    suspend fun getAllNowMovies(
        @Query("language") language: String = "es-Es",
        @Query("api_key") api_key: String = "6226d5015005b8434fee3ab30b61cd97",
    ): Response<NowMovies>

}