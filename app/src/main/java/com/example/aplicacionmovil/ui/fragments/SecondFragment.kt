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
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.data.marvel.MarvelChars
import com.example.aplicacionmovil.databinding.FragmentSecondBinding
import com.example.aplicacionmovil.logic.jikanLogic.JikanAnimeLogic
import com.example.aplicacionmovil.logic.validator.ListItems
import com.example.aplicacionmovil.ui.activities.DetailsMarvelItem
import com.example.aplicacionmovil.ui.activities.MainActivity
import com.example.aplicacionmovil.ui.adapters.MarvelAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class SecondFragment : Fragment() {

    private lateinit var binding: FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentSecondBinding.inflate(
            layoutInflater,
            container,
            false
        )

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
        //binding.listview.adapter = adapter
        chargeDataRV()

        binding.rvSwipe.setOnRefreshListener {
            chargeDataRV()
            binding.rvSwipe.isRefreshing = false
        }

    }

    fun sendMarvelItem(item: MarvelChars) {
        val i = Intent(requireActivity(), DetailsMarvelItem::class.java)
        i.putExtra("name", item)
        startActivity(i)
    }

    fun chargeDataRV() {
        lifecycleScope.launch(Dispatchers.IO) {

            val rvAdapter = MarvelAdapter(
                JikanAnimeLogic().getAllAnimes()
            )
            { sendMarvelItem(it) }

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

            //false en orden del listado u true al reves
        }
    }

}