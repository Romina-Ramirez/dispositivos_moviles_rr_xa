package com.example.aplicacionmovil.data.converters

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aplicacionmovil.data.dao.nowmovies.NowMoviesDAO
import com.example.aplicacionmovil.data.entities.movies.nowPlaying.database.NowMoviesDB

@Database(
    entities = [NowMoviesDB::class],
    version = 1
)

abstract class NowMovieConnectionDB : RoomDatabase() {
    abstract fun nowMoviesDao(): NowMoviesDAO
}