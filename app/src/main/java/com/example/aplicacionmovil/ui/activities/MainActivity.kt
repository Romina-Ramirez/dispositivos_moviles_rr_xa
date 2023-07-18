package com.example.aplicacionmovil.ui.activities

import android.annotation.SuppressLint
import android.app.SearchManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult.*
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.example.aplicacionmovil.databinding.ActivityMainBinding
import com.example.aplicacionmovil.logic.validator.LoginValidator
import com.google.android.material.snackbar.Snackbar
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.UUID

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class MainActivity : AppCompatActivity() {


    private lateinit var binding: ActivityMainBinding

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        initClass()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

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
        //para que al darle clicl al boton facebook habra un link
        binding.btnActionF.setOnClickListener {
//            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("geo:-0.2006288, -78.5786066")) //o "link" o "tel:09987434
            val intent = Intent(
                Intent.ACTION_WEB_SEARCH
            )
            intent.setClassName(
                "com.google.android.googlequicksearchbox",
                "com.google.android.googlequicksearchbox.SearchActivity")
            intent.putExtra(SearchManager.QUERY, "UCE")
            startActivity(intent)
        }

        val appResulttLocal = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){resultActivity ->
            when(resultActivity.resultCode){
                RESULT_OK -> {Snackbar.make(binding.hola, "Resultado exitoso", Snackbar.LENGTH_LONG).show()}
                RESULT_CANCELED -> {Log.d("UCE", "Resultado fallido")}
                else -> {Log.d("UCE", "Resultado dudoso")}
            }
            //lo mismo pero mas barato
//            if(resultActivity.resultCode == RESULT_OK){
//
//            }else{
//                if(resultActivity.resultCode == RESULT_CANCELED){
//
//                }else{
//
//                }
//            }

        }
        binding.btnActionT.setOnClickListener {
            val resIntent =  Intent(this, ResultActivity::class.java)
            appResulttLocal.launch(resIntent)
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