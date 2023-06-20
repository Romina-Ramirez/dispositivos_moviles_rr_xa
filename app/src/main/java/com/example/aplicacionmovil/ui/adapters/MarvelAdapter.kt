package com.example.aplicacionmovil.ui.adapters

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.data.marvel.MarvelChars
import com.example.aplicacionmovil.databinding.MarvelCharactersBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class MarvelAdapter(private val items: List<MarvelChars>) :
    RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder>() {

    class MarvelViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val binding: MarvelCharactersBinding = MarvelCharactersBinding.bind(view)

        //    solo modificamos el render
        fun render(item: MarvelChars) {
            binding.textName.text = item.name
            binding.textComic.text = item.comic
            Picasso.get().load(item.image).into(binding.imgMarvel)

            binding.imgMarvel.setOnClickListener {
                Snackbar.make(binding.imgMarvel, item.name, Snackbar.LENGTH_SHORT).show()
//            de una clase no se puede ir a un activity solo se puede de activity a otra activity
//            basicamente no se puede hacer: val intent =  Intent()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelAdapter.MarvelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarvelViewHolder(
            inflater.inflate(
                R.layout.marvel_characters,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MarvelAdapter.MarvelViewHolder, position: Int) {
        holder.render(items[position])
    }

    override fun getItemCount(): Int = items.size


}