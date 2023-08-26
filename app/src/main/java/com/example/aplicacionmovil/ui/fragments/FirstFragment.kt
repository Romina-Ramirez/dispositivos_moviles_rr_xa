package com.example.aplicacionmovil.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacionmovil.databinding.FragmentFirstBinding
import com.example.aplicacionmovil.logic.data.PopularMovies
import com.example.aplicacionmovil.logic.moviesLogic.PopularMoviesLogic
import com.example.aplicacionmovil.ui.activities.DetailsMovieItem
import com.example.aplicacionmovil.ui.adapters.PopularMoviesAdapter
import com.example.aplicacionmovil.ui.utilities.Metodos
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FirstFragment : Fragment() {

    private var rvAdapter: PopularMoviesAdapter =
        PopularMoviesAdapter { sendPopMovieItem(it) }
    private lateinit var binding: FragmentFirstBinding
    private lateinit var gManager: GridLayoutManager
    private lateinit var lmanager: LinearLayoutManager
    private var popMoviesItems: MutableList<PopularMovies> = mutableListOf<PopularMovies>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentFirstBinding.inflate(layoutInflater, container, false)

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
        chargeDataRVInit()
        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV()
            binding.rvSwipe.isRefreshing = false
        }

    }

    private fun sendPopMovieItem(item: PopularMovies) {
        val i = Intent(requireActivity(), DetailsMovieItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }

    private fun chargeDataRV() {
        lifecycleScope.launch(Dispatchers.Main) {
            popMoviesItems = withContext(Dispatchers.IO) {
                return@withContext (PopularMoviesLogic().getAllPopMovies())
            }
            rvAdapter.items = popMoviesItems
            binding.rvMarvelChars.apply {
                this.adapter = rvAdapter
                this.layoutManager = gManager
            }
        }
    }

    private fun chargeDataRVInit() {
        if (Metodos().isOnline(requireActivity())) {
            lifecycleScope.launch(Dispatchers.Main) {
                popMoviesItems = withContext(Dispatchers.IO) {
                    return@withContext (PopularMoviesLogic().getAllPopMovies())
                }
                rvAdapter.items = popMoviesItems
                binding.rvMarvelChars.apply {
                    this.adapter = rvAdapter
                    this.layoutManager = gManager
                }
            }
        } else {
            Snackbar.make(
                binding.cardView,
                "No hay conexión",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }
}