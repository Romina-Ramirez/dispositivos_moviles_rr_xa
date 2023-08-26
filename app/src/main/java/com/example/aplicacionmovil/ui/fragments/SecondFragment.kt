package com.example.aplicacionmovil.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import androidx.datastore.preferences.core.stringPreferencesKey
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplicacionmovil.data.UserDataStore
import com.example.aplicacionmovil.ui.activities.dataStore
import kotlinx.coroutines.flow.map
import com.example.aplicacionmovil.databinding.FragmentSecondBinding
import com.example.aplicacionmovil.logic.data.NowPlayingMovies
import com.example.aplicacionmovil.logic.moviesLogic.NowMoviesLogic
import com.example.aplicacionmovil.ui.activities.DetailsMovieItem
import com.example.aplicacionmovil.ui.adapters.NowMoviesAdapter
import com.example.aplicacionmovil.ui.utilities.Metodos
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment() {

    private var rvAdapter: NowMoviesAdapter =
        NowMoviesAdapter({ sendNowMovieItem(it) }, { saveNowMovieItem(it) })
    private lateinit var binding: FragmentSecondBinding
    private lateinit var lmanager: LinearLayoutManager
    private lateinit var gManager: GridLayoutManager
    private var nowMoviesItems: MutableList<NowPlayingMovies> = mutableListOf<NowPlayingMovies>()
    private var nowMoviesItemsDB: MutableList<NowPlayingMovies> = mutableListOf<NowPlayingMovies>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSecondBinding.inflate(layoutInflater, container, false)

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
        lifecycleScope.launch(Dispatchers.Main) {
            getDataStore()
                .collect { user ->
                    Log.d("UCE", user.email)
                    Log.d("UCE", user.name)
                    Log.d("UCE", user.session)
                }
        }

        chargeDataRVInit()
        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV()
            binding.rvSwipe.isRefreshing = false
        }

    }

    private fun sendNowMovieItem(item: NowPlayingMovies) {
        val i = Intent(requireActivity(), DetailsMovieItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }

    private fun saveNowMovieItem(item: NowPlayingMovies): Boolean {
        return if (item == null || nowMoviesItemsDB.contains(item)) {
            false
        } else {
            lifecycleScope.launch(Dispatchers.Main) {
                withContext(Dispatchers.IO) {
                    NowMoviesLogic().insertNowMovietoDB(listOf(item))
                    nowMoviesItemsDB = NowMoviesLogic().getAllNowMoviesDB().toMutableList()
                }
            }
            true
        }
    }

    private fun chargeDataRV() {
        lifecycleScope.launch(Dispatchers.Main) {
            nowMoviesItems = withContext(Dispatchers.IO) {
                return@withContext (NowMoviesLogic().getAllNowMovies())
            }
            rvAdapter.items = nowMoviesItems
            binding.rvMarvelChars.apply {
                this.adapter = rvAdapter
                this.layoutManager = gManager
            }
        }
    }

    private fun chargeDataRVInit() {
        if (Metodos().isOnline(requireActivity())) {
            lifecycleScope.launch(Dispatchers.Main) {
                nowMoviesItems = withContext(Dispatchers.IO) {
                    return@withContext (NowMoviesLogic().getAllNowMovies())
                }
                rvAdapter.items = nowMoviesItems
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

    override fun onResume() {
        super.onResume()
        lifecycleScope.launch(Dispatchers.Main) {
            withContext(Dispatchers.IO) {
                nowMoviesItemsDB = NowMoviesLogic().getAllNowMoviesDB().toMutableList()
            }
        }
    }

    private fun getDataStore() =
        requireActivity().dataStore.data.map { prefs ->
            UserDataStore(
                name = prefs[stringPreferencesKey("user")].orEmpty(),
                email = prefs[stringPreferencesKey("email")].orEmpty(),
                session = prefs[stringPreferencesKey("session")].orEmpty()
            )
        }
}
