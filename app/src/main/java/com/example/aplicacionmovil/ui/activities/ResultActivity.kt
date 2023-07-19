package com.example.aplicacionmovil.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.aplicacionmovil.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()

        binding.buttonResult.setOnClickListener {
            val i = Intent()
            i.putExtra("result", "Resultado exitoso")
            setResult(RESULT_OK, i)
            finish()
        }

        binding.buttonFalse.setOnClickListener {
            val i = Intent()
            i.putExtra("result", "Resultado fallido")
            setResult(RESULT_CANCELED, i)
            finish()
        }
    }
}