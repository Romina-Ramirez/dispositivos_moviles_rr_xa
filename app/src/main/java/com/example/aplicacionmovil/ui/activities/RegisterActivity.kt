package com.example.aplicacionmovil.ui.activities


import android.content.Intent
import com.example.aplicacionmovil.R
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import com.example.aplicacionmovil.databinding.ActivityRegisterBinding
import com.google.android.material.snackbar.Snackbar

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.huellaRegistro.setOnClickListener {
            val intent= Intent(this, BiometricActivity::class.java)
            startActivity(intent)
        }

        //registro
        auth = Firebase.auth
        binding.ingresar.setOnClickListener {
            authWithFirebaseEmail(
                binding.ingresoCorreo.text.toString(),
                binding.ingresoContrasena.text.toString()
            )
        }
    }
}