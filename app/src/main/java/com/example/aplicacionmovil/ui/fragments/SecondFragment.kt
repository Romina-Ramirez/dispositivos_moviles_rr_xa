package com.example.aplicacionmovil.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.data.entities.marvel.characters.database.MarvelCharsDB
import com.example.aplicacionmovil.logic.data.MarvelChars
import com.example.aplicacionmovil.databinding.FragmentSecondBinding
import com.example.aplicacionmovil.logic.data.getMarvelCharsDB
import com.example.aplicacionmovil.logic.marvelLogic.MarvelCharactersLogic
import com.example.aplicacionmovil.ui.activities.DetailsMarvelItem
import com.example.aplicacionmovil.ui.adapters.MarvelAdapter
import com.example.aplicacionmovil.ui.utilities.AplicacionMovil
import com.example.aplicacionmovil.ui.utilities.Metodos
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment() {

    private var rvAdapter: MarvelAdapter = MarvelAdapter ({ sendMarvelItem(it) },{saveMarvelItem(it)})
    private lateinit var binding: FragmentSecondBinding
    private lateinit var lmanager: LinearLayoutManager
    private lateinit var gManager: GridLayoutManager
    private val limit = 99
    private var offset = 0
    private var marvelCharsItems: MutableList<MarvelChars> = mutableListOf<MarvelChars>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(
            layoutInflater, container, false)
        // Inflate the layout for this fragment
        lmanager = LinearLayoutManager(
            requireActivity(),
            LinearLayoutManager.VERTICAL,
            false
        )
        gManager = GridLayoutManager(requireActivity(), 2)
        return binding.root
    }
    override fun onStart() {
        super.onStart()
        chargeDataRV(offset,limit)
        lifecycleScope.launch((Dispatchers.IO)) {
            val newItems = MarvelCharactersLogic().getAllMarvelChars(offset, limit)
            withContext(Dispatchers.Main) {
                rvAdapter.updateListItems(newItems)
                this@SecondFragment.offset += limit
            }
        }
        binding.rvSwipe.setOnRefreshListener {
            chargeDataRVInit(offset,limit)
            binding.rvSwipe.isRefreshing = false
        }
        //Para cargar mas contenido
        binding.rvMarvelChars.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(
                    recyclerView,
                    dx,
                    dy
                ) //dy es para el scroll de abajo y dx es de izquierda a derech para buscar elementos
                if (dy > 0) {
                    val v = lmanager.childCount  //cuantos elementos han pasado
                    val p = lmanager.findFirstVisibleItemPosition() //posicion actual
                    val t = lmanager.itemCount //cuantos tengo en total
                    //necesitamos comprobar si el total es mayor igual que los elementos que han pasado entonces ncesitamos actualizar ya que estamos al final de la lista
                    if ((v + p) >= t) {
                        chargeDataRV(offset,limit)
                        lifecycleScope.launch((Dispatchers.IO)) {
                            val newItems = MarvelCharactersLogic().getAllMarvelChars(offset, limit)
                            withContext(Dispatchers.Main) {
                                rvAdapter.updateListItems(newItems)
                                this@SecondFragment.offset += limit
                            }
                        }
                    }
                }
            }
        })
    }
    fun corroutine() {
        lifecycleScope.launch(Dispatchers.Main) {
            var name = "Xavier"
            name = withContext(Dispatchers.IO) {
                name = "Xavi"
                return@withContext name
            }
            binding.cardView.radius
        }
    }
    private fun sendMarvelItem(item: MarvelChars): Unit {
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }
    private fun saveMarvelItem(item: MarvelChars): Boolean {
        var booleano:Boolean=false
        lifecycleScope.launch(Dispatchers.Main){
            var result:Boolean=withContext(Dispatchers.IO){
                return@withContext MarvelCharactersLogic().insertMarvelChartstoDB(listOf(item))
            }
            booleano=result
        }
        return booleano
    }
    fun chargeDataRV(limit: Int,offset: Int) {
        lifecycleScope.launch(Dispatchers.Main) {
            marvelCharsItems = withContext(Dispatchers.IO) {
                return@withContext (MarvelCharactersLogic().getAllMarvelChars(offset, limit))
            }
            rvAdapter.items = marvelCharsItems

            binding.rvMarvelChars.apply {
                this.adapter = rvAdapter
                this.layoutManager = gManager
            }
            this@SecondFragment.offset += limit
        }
    }

    fun chargeDataRVInit(offset: Int,limit: Int) {
        if (Metodos().isOnline(requireActivity())) {
            lifecycleScope.launch(Dispatchers.Main) {
                marvelCharsItems = withContext(Dispatchers.IO) {
                    return@withContext MarvelCharactersLogic().getInitChars(offset, limit)
                }
                rvAdapter.items = marvelCharsItems
                binding.rvMarvelChars.apply {
                    this.adapter = rvAdapter
                    this.layoutManager = gManager
                }
                this@SecondFragment.offset += limit
            }
        } else {
            Snackbar.make(
                binding.cardView,
                "No hay conexion",
                Snackbar.LENGTH_LONG
            )
                .show()
        }
    }
}
