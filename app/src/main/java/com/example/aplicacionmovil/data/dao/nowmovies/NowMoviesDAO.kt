package com.example.aplicacionmovil.data.dao.nowmovies

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.aplicacionmovil.data.entities.movies.nowPlaying.database.NowMoviesDB

@Dao
interface NowMoviesDAO {

    @Query("select * from NowMoviesDB")
    fun getAllMovies(): List<NowMoviesDB>

    @Query("select * from NowMoviesDB where id = :pk")
    fun getOneMovie(pk: Int): NowMoviesDB

    @Insert
    fun insertMovie(ch: List<NowMoviesDB>)

}