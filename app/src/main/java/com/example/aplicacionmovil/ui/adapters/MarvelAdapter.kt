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

class MarvelAdapter(
    private var fnClick: (MarvelChars) -> Unit,
    private var fnSave : (MarvelChars) -> Boolean
) :
    RecyclerView.Adapter<MarvelAdapter.MarvelViewHolder>() {

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

            binding.btnSave.setOnClickListener {
                var checkInsert:Boolean=false
                checkInsert=fnSave(item)
                if(checkInsert){
                    Snackbar.make(
                        binding.imgMarvel,
                        "Se agrego a favoritos",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }else{
                    Snackbar.make(
                        binding.imgMarvel,
                        "No se pudo agregar a favoritos",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
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

}