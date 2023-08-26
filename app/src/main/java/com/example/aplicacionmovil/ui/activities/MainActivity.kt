package com.example.aplicacionmovil.ui.activities

import androidx.biometric.BiometricPrompt
import androidx.fragment.app.FragmentActivity
import android.Manifest
import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.location.Location
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.speech.RecognizerIntent
import android.util.Log
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts.*
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
//import com.example.aplicacionmovil.databinding.ActivityMainBinding
import com.example.aplicacionmovil.logic.validator.LoginValidator
import com.google.android.material.snackbar.Snackbar
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import com.example.aplicacionmovil.R
import com.example.aplicacionmovil.databinding.ActivityMainBinding
import com.example.aplicacionmovil.ui.utilities.MyLocationManager
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.LocationSettingsRequest
import com.google.android.gms.location.LocationSettingsStatusCodes
import com.google.android.gms.location.Priority
import com.google.android.gms.location.SettingsClient
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID
import java.util.concurrent.Executor

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")


class MainActivity : AppCompatActivity() {
    //ubicacion
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient
    private lateinit var binding: ActivityMainBinding
    private lateinit var locationRequest: LocationRequest
    private lateinit var locationCallBack: LocationCallback

    private lateinit var auth: FirebaseAuth
    private val TAG = "UCE"


    //variables para pedir al servicio, de localizacion que active la solicitud de ejecucion
    private lateinit var client: SettingsClient
    private lateinit var locationSettingsRequest: LocationSettingsRequest

    private var currentLocation: Location? = null

    private val speechToText =
        registerForActivityResult(StartActivityForResult()) { activityResult ->

            val sn = Snackbar.make(
                binding.welcome,
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

    @SuppressLint("MissingPermission")
    private val locationContract =
        registerForActivityResult(RequestPermission()) { isGranted ->
            when (isGranted) {
                true -> {
                    //si la ubicacion esta activa hara esto
                    client.checkLocationSettings(locationSettingsRequest).apply {
                        addOnSuccessListener {
                            val task = fusedLocationProviderClient.lastLocation
                            task.addOnSuccessListener { location ->

                                fusedLocationProviderClient.requestLocationUpdates(
                                    locationRequest,
                                    locationCallBack,
                                    Looper.getMainLooper()
                                )
                            }
                        }

                        addOnFailureListener { ex ->
                            if (ex is ResolvableApiException) {
                                ex.startResolutionForResult(
                                    this@MainActivity,
                                    LocationSettingsStatusCodes.RESOLUTION_REQUIRED
                                )
                            }
                        }
                    }
                }

                shouldShowRequestPermissionRationale(
                    Manifest.permission.ACCESS_FINE_LOCATION
                ) -> {
                    Snackbar.make(
                        binding.welcome,
                        "Ayude con el permiso porfa",
                        Snackbar.LENGTH_LONG
                    ).show()
                }

                false -> {
                    Snackbar.make(
                        binding.welcome,
                        "Encienda el GPS por favor",
                        Snackbar.LENGTH_LONG
                    ).show()
                }
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        executor = ContextCompat.getMainExecutor(this)

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)

        locationRequest = LocationRequest.Builder(
            Priority.PRIORITY_HIGH_ACCURACY,
            1000
        ).build()

        locationCallBack = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                super.onLocationResult(locationResult)
                if (locationResult != null) {
                    locationResult.locations.forEach { location ->
                        currentLocation = location
                        Log.d(
                            "UCE",
                            "Ubicación: ${location.latitude}, ${location.longitude}"
                        )
                    }
                } else {
                    Log.d("UCE", "GPS apagado")
                }
            }
        }
        client = LocationServices.getSettingsClient(this)
        locationSettingsRequest =
            LocationSettingsRequest.Builder().addLocationRequest(locationRequest).build()

        //ingresar
        auth = Firebase.auth
        binding.ingresar.setOnClickListener {
            signInWithEmailAndPassword(
                binding.ingresoCorreo.text.toString(),
                binding.ingresoContrasena.text.toString()
            )
        }
        //recuperar contraseña
        auth = Firebase.auth
        binding.btnOlvido.setOnClickListener {
            recoveryPasswordWhithEmail(
                binding.ingresoCorreo.text.toString()
            )
        }
        binding.btnHuella.setOnClickListener {
            val intent = Intent(this, BiometricActivity::class.java)
            startActivity(intent)
        }

        binding.registro.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }

    }


    private fun signInWithEmailAndPassword(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                    Log.d(TAG, "signInWithEmail:success")
                    val user = auth.currentUser
                    startActivity(Intent(this, SecondActivity::class.java))
                } else {
                    // If sign in fails, display a message to the user.
                    Log.w(TAG, "signInWithEmail:failure", task.exception)
                    Toast.makeText(
                        baseContext,
                        "Authentication failed.",
                        Toast.LENGTH_SHORT,
                    ).show()
                }
            }
    }

    private fun recoveryPasswordWhithEmail(email: String) {
        auth.sendPasswordResetEmail(email).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                //de una forma
                Toast.makeText(
                    this,
                    "Correo de recuperacion enviado correctamente",
                    Toast.LENGTH_SHORT,
                ).show()
                // de otra
                MaterialAlertDialogBuilder(this).apply {
                    setTitle("Alert")
                    setMessage("Correo de recuperacion enviado correcftamente")
                    setCancelable(true)
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
//        initClass()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

    override fun onPause() {
        super.onPause()
        fusedLocationProviderClient.removeLocationUpdates(locationCallBack)
    }

    private suspend fun saveDataStore(stringData: String) {
        dataStore.edit { prefs ->
            prefs[stringPreferencesKey("user")] = stringData
            prefs[stringPreferencesKey("session")] = UUID.randomUUID().toString()
            prefs[stringPreferencesKey("email")] = "dispositivos_moviles@uce.edu.ec"
        }
    }

}