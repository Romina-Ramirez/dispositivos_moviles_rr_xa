package com.example.aplicacionmovil.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.databinding.MarvelBuscadorCharactersBinding
import com.example.aplicacionmovil.logic.data.PopularMovies
import com.squareup.picasso.Picasso

class PopularMoviesAdapter(
    private var fnClick: (PopularMovies) -> Unit
) : RecyclerView.Adapter<PopularMoviesAdapter.PopularMoviesViewHolder>() {

    var items: List<PopularMovies> = listOf()

    class PopularMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: MarvelBuscadorCharactersBinding =
            MarvelBuscadorCharactersBinding.bind(view)

        fun render(
            item: PopularMovies,
            fnClick: (PopularMovies) -> Unit
        ) {
            binding.textName.text = item.title
            binding.textComic.text = item.language
            Picasso.get().load(item.poster).into(binding.imgMarvel)

            itemView.setOnClickListener {
                fnClick(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PopularMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return PopularMoviesViewHolder(
            inflater.inflate(
                R.layout.marvel_buscador_characters,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: PopularMoviesViewHolder, position: Int) {
        holder.render(items[position], fnClick)
    }

    override fun getItemCount(): Int = items.size

}