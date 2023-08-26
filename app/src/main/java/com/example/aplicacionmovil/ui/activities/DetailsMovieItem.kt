package com.example.aplicacionmovil.ui.activities

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import androidx.lifecycle.lifecycleScope
import com.example.aplicacionmovil.databinding.ActivityDetailsMarvelItemBinding
import com.example.aplicacionmovil.logic.data.NowPlayingMovies
import com.example.aplicacionmovil.logic.data.PopularMovies
import com.example.aplicacionmovil.logic.moviesLogic.NowMoviesLogic
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailsMovieItem : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsMarvelItemBinding
    private var nowMoviesItemsDB: MutableList<NowPlayingMovies> = mutableListOf()
    private val favoriteNowMoviesItems: MutableList<NowPlayingMovies> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMarvelItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        val item = intent.getParcelableExtra<Parcelable>("name")

        if (item != null) {
            if (item is NowPlayingMovies) {
                binding.txtMarvel.text = item.title
                binding.txtComic.text = item.description
                Picasso.get().load(item.poster).into(binding.imageMarvel)

                binding.btnFavoritos.setOnClickListener {
                    var checkInsert: Boolean = saveNowMovieItem(
                        NowPlayingMovies(
                            item.id,
                            item.title,
                            item.description,
                            item.poster,
                            item.language,
                            item.date,
                            item.vote
                        )
                    )

                    if (checkInsert) {
                        Snackbar.make(
                            binding.imageMarvel,
                            "Se agregó a favoritos",
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
            } else if (item is PopularMovies) {
                binding.txtMarvel.text = item.title
                binding.txtComic.text = item.description
                Picasso.get().load(item.poster).into(binding.imageMarvel)
                binding.btnFavoritos.visibility = View.GONE
            }
        }
    }

    private fun saveNowMovieItem(item: NowPlayingMovies): Boolean {

        return if (item == null || nowMoviesItemsDB.contains(item)) {
            false
        } else {
            lifecycleScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    NowMoviesLogic().insertNowMovietoDB(listOf(item))
                    nowMoviesItemsDB = NowMoviesLogic().getAllNowMoviesDB().toMutableList()
                    favoriteNowMoviesItems.add(item)
                }
            }
            true
        }
    }

    private fun updateFavoriteList() {
        val returnIntent = Intent()
        returnIntent.putParcelableArrayListExtra("favorites", ArrayList(favoriteNowMoviesItems))
        setResult(Activity.RESULT_OK, returnIntent)
        finish()
    }

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                favoriteNowMoviesItems.clear()
                favoriteNowMoviesItems.addAll(NowMoviesLogic().getAllNowMoviesDB())
                nowMoviesItemsDB = NowMoviesLogic().getAllNowMoviesDB().toMutableList()
            }
        }
    }
}