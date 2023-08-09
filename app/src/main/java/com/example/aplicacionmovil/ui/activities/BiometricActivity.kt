package com.example.aplicacionmovil.ui.activities

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.biometric.BiometricManager
import androidx.biometric.BiometricManager.Authenticators.BIOMETRIC_STRONG
import androidx.biometric.BiometricManager.Authenticators.DEVICE_CREDENTIAL
import androidx.biometric.BiometricPrompt
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import com.example.aplicacionmovil.databinding.ActivityBiometricBinding
import com.example.aplicacionmovil.ui.viewmodels.BiometricViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch

class BiometricActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBiometricBinding

    private val biometricViewModel by viewModels<BiometricViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBiometricBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAuthentication.setOnClickListener {
            autenticationBiometric()
        }

        biometricViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.lytMain.visibility = View.GONE
                binding.lytMainCopia.visibility = View.VISIBLE
            } else {
                binding.lytMain.visibility = View.VISIBLE
                binding.lytMainCopia.visibility = View.GONE
            }
        }
        lifecycleScope.launch {
            biometricViewModel.chargingData()
        }

    }

    private fun autenticationBiometric() {
        if (checkBiometric()) {
            val executor = ContextCompat.getMainExecutor(this)
            val biometricPrompt = BiometricPrompt.PromptInfo.Builder()
                .setTitle("Autenticación requerida")
                .setSubtitle("Ingrese su huella digital")
                .setAllowedAuthenticators(BIOMETRIC_STRONG)
                .setNegativeButtonText("Cancelar")
                .build()

            val biometricManager =
                BiometricPrompt(this, executor, object : BiometricPrompt.AuthenticationCallback() {
                    //para que salgan estos ctrl+o en el callback y seleccionamos los 3 ultimos
                    override fun onAuthenticationError(errorCode: Int, errString: CharSequence) {
                        super.onAuthenticationError(errorCode, errString)
                    }

                    override fun onAuthenticationSucceeded(result: BiometricPrompt.AuthenticationResult) {
                        super.onAuthenticationSucceeded(result)
                        val myIntent = Intent(this@BiometricActivity, SecondActivity::class.java)
                        startActivity(myIntent)
                    }

                    override fun onAuthenticationFailed() {
                        super.onAuthenticationFailed()
                    }
                })

            biometricManager.authenticate(biometricPrompt)
        } else {
            Snackbar.make(
                binding.root,
                "No existen los requisitos necesarios",
                Snackbar.LENGTH_LONG
            ).show()
        }
    }

    private fun checkBiometric(): Boolean {
        var returnValid: Boolean = false
        val biometricManager = BiometricManager.from(this)
        when (biometricManager.canAuthenticate(BIOMETRIC_STRONG)) {
            BiometricManager.BIOMETRIC_SUCCESS -> {
                returnValid = true
            }

            BiometricManager.BIOMETRIC_ERROR_NO_HARDWARE -> {
                returnValid = false
            }

            BiometricManager.BIOMETRIC_ERROR_HW_UNAVAILABLE -> {
                returnValid = false
            }

            BiometricManager.BIOMETRIC_ERROR_NONE_ENROLLED -> {
                val intentPrompt = Intent(Settings.ACTION_BIOMETRIC_ENROLL)
                intentPrompt.putExtra(
                    Settings.EXTRA_BIOMETRIC_AUTHENTICATORS_ALLOWED,
                    BIOMETRIC_STRONG or DEVICE_CREDENTIAL
                )
                startActivity(intentPrompt)
                returnValid = false
            }
        }
        return returnValid
    }
}