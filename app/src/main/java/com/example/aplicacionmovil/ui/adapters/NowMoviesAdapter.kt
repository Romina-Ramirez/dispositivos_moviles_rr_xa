package com.example.aplicacionmovil.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.databinding.MarvelCharactersBinding
import com.example.aplicacionmovil.logic.data.NowPlayingMovies
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class NowMoviesAdapter(
    private var fnClick: (NowPlayingMovies) -> Unit,
    private var fnSave: (NowPlayingMovies) -> Boolean
) : RecyclerView.Adapter<NowMoviesAdapter.NowMoviesViewHolder>() {

    var items: List<NowPlayingMovies> = listOf()

    class NowMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: MarvelCharactersBinding = MarvelCharactersBinding.bind(view)

        fun render(
            item: NowPlayingMovies,
            fnClick: (NowPlayingMovies) -> Unit,
            fnSave: (NowPlayingMovies) -> Boolean
        ) {
            binding.textName.text = item.title
            binding.textComic.text = item.language
            Picasso.get().load(item.poster).into(binding.imgMarvel)

            itemView.setOnClickListener {
                fnClick(item)
            }

            binding.btnSave.setOnClickListener {
                var checkInsert: Boolean = false
                checkInsert = fnSave(item)
                if (checkInsert) {
                    Snackbar.make(
                        binding.imgMarvel,
                        "Se agregó a favoritos",
                        Snackbar.LENGTH_SHORT
                    ).show()
                } else {
                    Snackbar.make(
                        binding.imgMarvel,
                        "Ya está en tus favoritos",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): NowMoviesViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return NowMoviesViewHolder(
            inflater.inflate(
                R.layout.marvel_characters,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: NowMoviesViewHolder, position: Int) {
        holder.render(items[position], fnClick, fnSave)
    }

    override fun getItemCount(): Int = items.size

    fun submitList(newItems: List<NowPlayingMovies>) {
        items = newItems
        notifyDataSetChanged()
    }

}