package com.example.aplicacionmovil.data.entities.movies.nowPlaying.database

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.aplicacionmovil.logic.data.NowPlayingMovies
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity
data class NowMoviesDB(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val poster: String,
    val language: String,
    val date: String,
    val vote: Double
) : Parcelable

fun NowMoviesDB.getNowMovies(): NowPlayingMovies {
    return NowPlayingMovies(
        id,
        title,
        description,
        poster,
        language,
        date,
        vote
    )
}

