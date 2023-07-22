package com.example.aplicacionmovil.ui.activities

import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.location.Geocoder
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import androidx.core.content.PermissionChecker.PermissionResult
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.aplicacionmovil.databinding.ActivityMainBinding
import com.example.aplicacionmovil.logic.validator.LoginValidator
import com.google.android.material.snackbar.Snackbar
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.aplicacionmovil.R
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.Locale
import java.util.UUID

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")



class MainActivity : AppCompatActivity() {

    //ubicacion
    private lateinit var fusedLocationProviderClient : FusedLocationProviderClient
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    override fun onStart() {
        super.onStart()
        initClass()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    @SuppressLint("MissingPermission")
    private fun initClass() {
        binding.ingresar.setOnClickListener {

            val check = LoginValidator().checkLogin(
                binding.ingresoCorreo.text.toString(),
                binding.ingresoContrasena.text.toString()
            )

            if (check) {
                lifecycleScope.launch(Dispatchers.IO) {
                    saveDataStore(binding.ingresoCorreo.text.toString())
                }

                var intent = Intent(
                    this,
                    SecondActivity::class.java
                )

                intent.putExtra(
                    "var1",
                    ""
                )

                intent.putExtra("var2", 2)
                startActivity(intent)
            } else {
                Snackbar.make(
                    binding.pedOnl, "Usuario o contraseña inválidos",
                    Snackbar.LENGTH_LONG
                ).show()
            }
        }

        //para que al darle click al boton facebook habra un link
        binding.btnActionF.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-0.2006288, -78.5786066")) //o "link" o "tel:09987434
            val intent = Intent(
                Intent.ACTION_WEB_SEARCH
            )
            intent.setClassName(
                "com.google.android.googlequicksearchbox",
                "com.google.android.googlequicksearchbox.SearchActivity"
            )
            intent.putExtra(SearchManager.QUERY, "UCE")
            startActivity(intent)
        }

        val appResultLocal =
            registerForActivityResult(StartActivityForResult()) { resultActivity ->

                val sn = Snackbar.make(
                    binding.hola,
                    "",
                    Snackbar.LENGTH_LONG
                )

                var message = when (resultActivity.resultCode) {
                    RESULT_OK -> {
                        sn.setBackgroundTint(resources.getColor(R.color.verde))
                        resultActivity.data?.getStringExtra("result").orEmpty()
                    }

                    RESULT_CANCELED -> {
                        sn.setBackgroundTint(resources.getColor(R.color.marvel))
                        resultActivity.data?.getStringExtra("result").orEmpty()
                    }

                    else -> {
                        "Dudoso"
                    }
                }
                sn.setText(message)
                sn.show()

            }

        val speechToText = registerForActivityResult(StartActivityForResult()) { activityResult ->

            val sn = Snackbar.make(
                binding.hola,
                "",
                Snackbar.LENGTH_LONG
            )
            var message = ""
            when (activityResult.resultCode) {
                RESULT_OK -> {
                    val msg = activityResult
                        .data?.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS)
                        ?.get(0).toString()
                    if (msg.isNotEmpty()) {
                        val intent = Intent(
                            Intent.ACTION_WEB_SEARCH
                        )
                        intent.setClassName(
                            "com.google.android.googlequicksearchbox",
                            "com.google.android.googlequicksearchbox.SearchActivity"
                        )
                        intent.putExtra(SearchManager.QUERY, msg)
                        startActivity(intent)
                    }
                }

                RESULT_CANCELED -> {
                    message = "Proceso cancelado"
                    sn.setBackgroundTint(resources.getColor(R.color.marvel))
                }

                else -> {
                    message = "Ocurrió un error"
                    sn.setBackgroundTint(resources.getColor(R.color.marvel))
                }

            }

            sn.setText(message)
            sn.show()

        }

        val locationContract =
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
                when (isGranted) {
                    true -> {
//                        val task = fusedLocationProviderClient.lastLocation
                        fusedLocationProviderClient.lastLocation.addOnSuccessListener {
                            it.longitude
                            it.latitude
                            val a = Geocoder(this)
                            a.getFromLocation(it.latitude, it.longitude, 1)
                        }
//                        task.addOnSuccessListener {
//                            if (task.result != null) {
//                                Snackbar.make(
//                                    binding.hola,
//                                    "${it.latitude},${it.longitude} ",
//                                    Snackbar.LENGTH_LONG
//                                ).show()
//                            }
                        }
                        shouldShowRequestPermissionRationale(
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ) -> {
                            Snackbar.make(
                                binding.hola,
                                "Ayude con el permiso porfa",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        false -> { Snackbar.make(
                                binding.hola,
                                "Encienda el GPS por favor",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }

                }
            }
        binding.btnActionT.setOnClickListener {
//            val intentSpeech =Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
//            intentSpeech.putExtra(
//                RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM
//            )
//            intentSpeech.putExtra(
//                RecognizerIntent
//                    .EXTRA_LANGUAGE,
//                Locale.getDefault()
//            )
//            intentSpeech.putExtra(
//                RecognizerIntent.EXTRA_PROMPT,
//                "Di algo..."
//            )
//            speechToText.launch(intentSpeech)


//            val resIntent = Intent(this, ResultActivity::class.java)
//            appResultLocal.launch(resIntent)

            //para los permisos
            locationContract.launch(Manifest.permission.ACCESS_FINE_LOCATION)

        }


    }

    private suspend fun saveDataStore(stringData: String) {
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey("user")] = stringData
            prefs[stringPreferencesKey("session")] = UUID.randomUUID().toString()
            prefs[stringPreferencesKey("email")] = "dispositivos_moviles@uce.edu.ec"
        }
    }
}