package com.example.aplicacionmovil.ui.utilities

import android.app.Application
import androidx.room.Room
import com.example.aplicacionmovil.data.converters.MarvelConnectionDB
import com.example.aplicacionmovil.data.entities.marvel.characters.database.MarvelCharsDB

//en esta clase si tenemois un ciclo de vida, esta clase estara atada al ciclio de la app
//solo habra un archivo de este tipo
//para que este registrada debe estar en el manifest
//si queremos que pase algo apenas se abra el proyecto se pone aqui
class AplicacionMovil : Application() {
    override fun onCreate() {
        super.onCreate()
        //aqui la variable nula pasa a ser una instancia a la base de datos
        db = Room.databaseBuilder(applicationContext, MarvelConnectionDB::class.java, "marvelDB").build()
    }

    //esto es un objeto que se crea dentro de una clase
    //nno se necesita instanciar
    companion object {
        private var db: MarvelConnectionDB? = null // empieza siendo nula
        //en este metodo ya llama al db con la base
        fun getdbInstancs(): MarvelConnectionDB {
            return db !! //la doble !!  ya nunca va a ser nula
        }
    }
}