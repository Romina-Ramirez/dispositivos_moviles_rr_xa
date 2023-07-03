package com.example.aplicacionmovil.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.data.marvel.MarvelChars
import com.example.aplicacionmovil.databinding.FragmentSecondBinding
import com.example.aplicacionmovil.logic.jikanLogic.JikanAnimeLogic
import com.example.aplicacionmovil.logic.marvelLogic.MarvelCharactersLogic
import com.example.aplicacionmovil.logic.validator.ListItems
import com.example.aplicacionmovil.ui.activities.DetailsMarvelItem
import com.example.aplicacionmovil.ui.activities.MainActivity
import com.example.aplicacionmovil.ui.adapters.MarvelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment() {

    private  var rvAdapter: MarvelAdapter = MarvelAdapter { sendMarvelItem(it) }
    private lateinit var binding: FragmentSecondBinding
    private lateinit var lmanager: LinearLayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondBinding.inflate(
            layoutInflater,
            container,
            false
        )

        lmanager = LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false)

        return binding.root
    }

    override fun onStart() {
        super.onStart()
        val names = arrayListOf<String>(
            "Carlos", "Xavier", "Andres",
            "Pepe", "Mariano", "Rosa"
        )
        val adapter =
            ArrayAdapter<String>(requireActivity(), R.layout.simple_layout, names)
        binding.spinner.adapter = adapter
        chargeDataRV("cap")

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV("cap")
            binding.rvSwipe.isRefreshing = false
        }

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
                        chargeDataRV("spider")
                        lifecycleScope.launch((Dispatchers.IO)) {
                            val newItems = JikanAnimeLogic().getAllAnimes()
                            withContext(Dispatchers.Main) {
                                rvAdapter.updateListItems(newItems)
                            }
                        }
                    }
                }
            }
        })

    }

    fun sendMarvelItem(item: MarvelChars) {
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }

    fun corrottine() {
        lifecycleScope.launch(Dispatchers.Main) {
            var name = "Xavier"

            name = withContext(Dispatchers.IO) {
                name = "Romi"
                return@withContext name
            }

            binding.cardView.radius
        }
    }

    fun chargeDataRV(search: String) {
        lifecycleScope.launch(Dispatchers.IO) {

            rvAdapter.items =JikanAnimeLogic().getAllAnimes()
            withContext(Dispatchers.Main) {
                with(binding.rvMarvelChars) {
                    this.adapter = rvAdapter
                    this.layoutManager = LinearLayoutManager(
                        requireActivity(),
                        LinearLayoutManager.VERTICAL,
                        false
                    )
                }
            }
        }
    }

}