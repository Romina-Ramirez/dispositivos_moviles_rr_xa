package com.example.aplicacionmovil.data.converters

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.aplicacionmovil.data.dao.marvel.MarvelCharsDAO
import com.example.aplicacionmovil.data.entities.marvel.characters.database.MarvelCharsDB

@Database(
    entities = [MarvelCharsDB::class],
    version = 1
)
abstract class MarvelConnectionDB : RoomDatabase() {

    abstract fun marvelDao(): MarvelCharsDAO

}