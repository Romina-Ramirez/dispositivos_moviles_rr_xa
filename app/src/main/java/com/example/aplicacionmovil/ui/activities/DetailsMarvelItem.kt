package com.example.aplicacionmovil.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.data.marvel.MarvelChars
import com.example.aplicacionmovil.databinding.ActivityDetailsMarvelItemBinding
import com.example.aplicacionmovil.databinding.MarvelCharactersBinding
import com.squareup.picasso.Picasso

class DetailsMarvelItem : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsMarvelItemBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsMarvelItemBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
//        var name: String? = ""
//
//        intent.extras?.let {
//            name = it.getString("name")
//        }
//        if (!name.isNullOrEmpty()) {
//            binding.txtMarvel.text = name
//        }

        val item = intent.getParcelableExtra<MarvelChars>("name")

        if (item != null){
            binding.txtMarvel.text = item.name
            Picasso.get().load(item.image).into(binding.imageMarvel)
            binding.txtComic.text = item.comic
        }
    }
}