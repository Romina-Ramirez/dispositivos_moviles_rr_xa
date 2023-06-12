package com.example.aplicacionmovil.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.databinding.ActivitySecondBinding
import com.example.aplicacionmovil.ui.fragments.FirstFragment
import com.example.aplicacionmovil.ui.fragments.SecondFragment
import com.example.aplicacionmovil.ui.fragments.ThirdFragment
import com.google.android.material.snackbar.Snackbar
import kotlin.math.log

class SecondActivity : AppCompatActivity() {

    private lateinit var binding : ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d("UCE", "Entrando a Create")
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        Log.d("UCE", "Entrando a Start")
        initClass()
    }

    private fun initClass() {
        var name: String = ""
//        intent.extras.let {
//            name = it?.getString("var1")!!
//        }
//        Log.d("UCE","Hola ${name}")
        binding.vistaTexto.text = "Bienvenidos " + name.toString()

        binding.boton.setOnClickListener {
            var intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.inicio -> {
                    val frag = SecondFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(binding.frmContainer.id, frag)
                    transaction.addToBackStack(null)
                    transaction.commit()

                    true
                }
                R.id.favoritos -> {
                    val frag = FirstFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(binding.frmContainer.id, frag)
                    transaction.addToBackStack(null)
                    transaction.commit()

                    true
                }
                R.id.apis -> {
                    val frag = ThirdFragment()
                    val transaction = supportFragmentManager.beginTransaction()
                    transaction.add(binding.frmContainer.id, frag)
                    transaction.addToBackStack(null)
                    transaction.commit()

                    true
                }
                else -> false
            }
        }

    }



}