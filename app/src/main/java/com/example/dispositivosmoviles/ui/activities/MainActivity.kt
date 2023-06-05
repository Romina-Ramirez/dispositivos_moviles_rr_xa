package com.example.dispositivosmoviles.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.dispositivosmoviles.databinding.ActivityMainBinding
import com.example.dispositivosmoviles.logic.validator.LoginValidator
import com.google.android.material.snackbar.Snackbar

//clase que hereda de AppCompatActivity usando :
class MainActivity : AppCompatActivity() {

    // private Modificafor privado
    // lateinit que especifica que la variale se va a inicializar luego
    // var para cambiar en el tiempo
    private lateinit var binding: ActivityMainBinding

    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        //onCreate hace que la clase que esta dentro en un objeto
        super.onCreate(savedInstanceState)

        //Binding
        // layoutInflater inflar el layout asociado
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onStart() {
        super.onStart()
        //initClass()
    }

    override fun onDestroy() {
        super.onDestroy()
    }

//    private fun initClass() {
//        binding.btnLogin.setOnClickListener {
//
//            val check = LoginValidator().checkLogin(
//                binding.txtName.text.toString(),
//                binding.txtPass.text.toString()
//            )
//
//            if (check) {
//                var intent = Intent(
//                    this,
//                    SecondActivity::class.java
//                )
//
//                intent.putExtra(
//                    "var1",
//                    //binding.txtBuscar.text.toString()
//                    ""
//                )
//
//                intent.putExtra("var2", 2)
//                startActivity(intent)
//            } else {
//                Snackbar.make(
//                    binding.bienvenida, "Usuario o contraseña inválidos",
//                    Snackbar.LENGTH_LONG
//                ).show()
//            }
//        }
//    }
}