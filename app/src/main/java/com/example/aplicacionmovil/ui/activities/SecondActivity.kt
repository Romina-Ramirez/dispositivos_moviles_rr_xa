package com.example.aplicacionmovil.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.databinding.ActivitySecondBinding
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

                    var suma : Int = 0

                    for (i in listOf(1,2,3)){
                        suma += i
                    }
                    Snackbar.make(binding.vistaTexto,"La suma es $suma", Snackbar.LENGTH_LONG).show()
                    true
                }
                R.id.favoritos -> {
                    var suma : Int = 0

                    for (i in listOf(8,12,13)){
                        suma += i
                    }
                    Snackbar.make(binding.vistaTexto,"La suma es $suma", Snackbar.LENGTH_LONG).show()

                    true
                }
                R.id.apis -> {
                    true
                }
                else -> false
            }
        }

    }



}