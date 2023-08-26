package com.example.aplicacionmovil.ui.utilities

import android.app.Application
import androidx.room.Room
import com.example.aplicacionmovil.data.converters.NowMovieConnectionDB

class AplicacionMovil : Application() {

    override fun onCreate() {
        super.onCreate()
        db =
            Room.databaseBuilder(applicationContext, NowMovieConnectionDB::class.java, "nowMovieDB")
                .build()
    }

    companion object {
        private var db: NowMovieConnectionDB? = null
        fun getdbInstance(): NowMovieConnectionDB {
            return db!!
        }
    }
}