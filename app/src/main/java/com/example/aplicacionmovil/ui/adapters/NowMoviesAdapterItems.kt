package com.example.aplicacionmovil.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.databinding.MarvelCharactersBinding
import com.example.aplicacionmovil.logic.data.NowPlayingMovies
import com.squareup.picasso.Picasso

class NowMoviesAdapterItems (
    private var fnClick: (NowPlayingMovies) -> Unit
) : RecyclerView.Adapter<NowMoviesAdapterItems.NowMoviesViewHolder>(){

    var items: List<NowPlayingMovies> = listOf()
    class NowMoviesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: MarvelCharactersBinding = MarvelCharactersBinding.bind(view)

        fun render(
            item: NowPlayingMovies,
            fnClick: (NowPlayingMovies) -> Unit
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
        holder.render(items[position], fnClick)
    }

    override fun getItemCount(): Int = items.size

    fun updateListItems(newItems: List<NowPlayingMovies>){
        this.items = this.items.plus(newItems)
        notifyDataSetChanged()
    }

    fun replaceListItems(newItems: List<NowPlayingMovies>){
        this.items = newItems
        notifyDataSetChanged()
    }

    fun submitList(newItems: List<NowPlayingMovies>) {
        items = newItems
        notifyDataSetChanged()
    }

}