package com.example.dispositivosmoviles.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.dispositivosmoviles.R
import com.example.dispositivosmoviles.databinding.ActivityMainBinding
import com.example.dispositivosmoviles.databinding.ActivitySecondBinding
import com.google.android.material.snackbar.Snackbar
import kotlin.math.log

class SecondActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySecondBinding

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
        var name: String =""
        intent.extras.let {
            name = it?.getString("var1")!!
        }
        Log.d("UCE","Hola ${name}")
        binding.vistaTexto.text =  "Bienvenidos " + name.toString()

        binding.boton.setOnClickListener {
            //binding.vistaTexto.text = "El codigo ejecuta correctamente"

            var intent = Intent(this, MainActivity::class.java)

            startActivity(intent)
        }
    }

}