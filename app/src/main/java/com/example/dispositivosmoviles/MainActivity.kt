package com.example.dispositivosmoviles

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.snackbar.Snackbar

//clase que hereda de AppCompatActivity usando :
class MainActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        //onCreate hace que la clase que esta dentro en un objeto
        super.onCreate(savedInstanceState)
        //en esta funcion lo que haremos es inflar todos lo que hay en el layout a objetos
        //la letra R es la clase que viene implemenatada por omicion en Android
        //la herramienta da acceso a todos los recursos (res)
        //la clase R agrupa por tipo
        setContentView(R.layout.activity_main)
        //asignamos el boton 1 a su variable
        var boton1 = findViewById<Button>(R.id.button)
        var txtBuscar = findViewById<TextView>(R.id.txt_buscar)

        boton1.text="INGRESAR"
        boton1.setOnClickListener{
            txtBuscar.text = "El evento se ha ejecutado"
            //toast es un componente no visual, con makeText mostramos un texto
            //el contexto es conocer de manera previa lo que estamos haciendo
            //para enviar el 100% del contexto
            //Toast.makeText(this, "Este es un ejemplo", Toast.LENGTH_SHORT).show()
            //solo para enviar una parte del contexto
            //LEGTH... es el tiempo que se va a mostrar

//            var f = Snackbar.make(boton1, "Este es otro mensaje", Snackbar.LENGTH_SHORT)
//            f.show()
        }
    }
}