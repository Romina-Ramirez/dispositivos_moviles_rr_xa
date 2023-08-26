package com.example.aplicacionmovil.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import com.example.aplicacionmovil.logic.data.MarvelChars
import com.example.aplicacionmovil.databinding.ActivityDetailsMarvelItemBinding
import com.example.aplicacionmovil.logic.marvelLogic.MarvelCharactersLogic
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsMarvelItem : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsMarvelItemBinding
    private var marvelCharsItemsDB: MutableList<MarvelChars> = mutableListOf<MarvelChars>()
    private val favoriteMarvelItems: MutableList<MarvelChars> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMarvelItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val item = intent.getParcelableExtra<MarvelChars>("name")

        if (item != null) {
            binding.txtMarvel.text = item.name
            binding.txtComic.text =  item.comic
            Picasso.get().load(item.image).into(binding.imageMarvel)
            binding.btnFavoritos.setOnClickListener {
                var checkInsert: Boolean = saveMarvelItem(
                    MarvelChars(
                        item.id,
                        binding.txtMarvel.text.toString(),
                        item.comic,
                        item.image
                    )
                )
                if (checkInsert) {
                    Snackbar.make(
                        binding.imageMarvel,
                        "Se agrego a favoritos",
                        Snackbar.LENGTH_SHORT
                    ).show()
                    updateFavoriteList()

                } else {
                    Snackbar.make(
                        binding.imageMarvel,
                        "No se puedo agregar o Ya esta agregado",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }


    }

    private fun saveMarvelItem(item: MarvelChars): Boolean {
        return if (item == null || marvelCharsItemsDB.contains(item)) {
            false
        } else {
            lifecycleScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    MarvelCharactersLogic().insertMarvelChartstoDB(listOf(item))
                    marvelCharsItemsDB = MarvelCharactersLogic().getAllMarvelChardDB().toMutableList()
                    favoriteMarvelItems.add(item)
                }
            }
            true
        }
    }



    private fun updateFavoriteList() {
        // Notificar al ThirdFragment que la lista de favoritos se actualizó
        val returnIntent = Intent()
        returnIntent.putParcelableArrayListExtra("favorites", ArrayList(favoriteMarvelItems))
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }


    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                favoriteMarvelItems.clear()
                favoriteMarvelItems.addAll(MarvelCharactersLogic().getAllMarvelChardDB())
                marvelCharsItemsDB = MarvelCharactersLogic().getAllMarvelChardDB().toMutableList()

            }
        }
    }
}