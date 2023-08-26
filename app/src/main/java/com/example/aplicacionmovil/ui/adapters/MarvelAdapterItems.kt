package com.example.aplicacionmovil.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.logic.data.MarvelChars
import com.example.aplicacionmovil.databinding.MarvelCharactersBinding
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso

class MarvelAdapterItems(
    private var fnClick: (MarvelChars) -> Unit,
    private var fnSave: (MarvelChars) -> Boolean
) :
    RecyclerView.Adapter<MarvelAdapterItems.MarvelViewHolder>() {

    var items: List<MarvelChars> = listOf()
    class MarvelViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private val binding: MarvelCharactersBinding = MarvelCharactersBinding.bind(view)

        //    solo modificamos el render
        fun render(
            item: MarvelChars,
            fnClick: (MarvelChars) -> Unit,
            fnSave: (MarvelChars) -> Boolean
        ) {
            binding.textName.text = item.name
            binding.textComic.text = item.comic
            Picasso.get().load(item.image).into(binding.imgMarvel)

            itemView.setOnClickListener {
                fnClick(item)
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MarvelAdapterItems.MarvelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        return MarvelViewHolder(
            inflater.inflate(
                R.layout.marvel_characters,
                parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: MarvelAdapterItems.MarvelViewHolder, position: Int) {
        holder.render(items[position], fnClick, fnSave)
    }

    override fun getItemCount(): Int = items.size

    fun updateListItems(newItems: List<MarvelChars>){
        this.items = this.items.plus(newItems) //agrega a la lista los nuevos elementos
        notifyDataSetChanged()
    }

    //en este metodo no se suman los items nuevos a los anteriores, se remplaza todo
    fun replaceListItems(newItems: List<MarvelChars>){
        this.items = newItems //agrega a la lista los nuevos elementos
        notifyDataSetChanged()
    }

    fun submitList(newItems: List<MarvelChars>) {
        items = newItems
        notifyDataSetChanged()
    }



}