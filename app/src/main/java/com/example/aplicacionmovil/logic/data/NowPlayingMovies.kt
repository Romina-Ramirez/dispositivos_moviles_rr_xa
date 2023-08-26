package com.example.aplicacionmovil.logic.data

import android.os.Parcelable
import com.example.aplicacionmovil.data.entities.movies.nowPlaying.database.NowMoviesDB
import kotlinx.parcelize.Parcelize

@Parcelize
data class NowPlayingMovies(
    val id: Int,
    val title: String,
    val description: String,
    val poster: String,
    val language: String,
    val date: String,
    val vote: Double
) : Parcelable

fun NowPlayingMovies.getNowMoviesDB(): NowMoviesDB {
    return NowMoviesDB(
        id, title, description, poster, language, date, vote
    )
}