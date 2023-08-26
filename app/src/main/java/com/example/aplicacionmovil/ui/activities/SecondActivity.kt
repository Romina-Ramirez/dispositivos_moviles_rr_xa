package com.example.aplicacionmovil.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.databinding.ActivitySecondBinding
import com.example.aplicacionmovil.ui.fragments.FirstFragment
import com.example.aplicacionmovil.ui.fragments.SecondFragment
import com.example.aplicacionmovil.ui.fragments.ThirdFragment
import com.example.aplicacionmovil.ui.utilities.FragmentsManager

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initClass()
    }

    private fun initClass() {
        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.inicio -> {
                    FragmentsManager().replaceFragmet(supportFragmentManager, binding.frmContainer.id, SecondFragment())
                    true
                }

                R.id.favoritos -> {
                    FragmentsManager().replaceFragmet(supportFragmentManager, binding.frmContainer.id, FirstFragment())
                    true
                }

                R.id.apis -> {

                    FragmentsManager().replaceFragmet(supportFragmentManager, binding.frmContainer.id, ThirdFragment())
                    true
                }

                else -> false
            }
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
    }

}