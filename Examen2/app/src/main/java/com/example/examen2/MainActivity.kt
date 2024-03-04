package com.example.examen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import android.view.View

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar Firebase Authentication
        auth = FirebaseAuth.getInstance()

        // Ir a la actividad BAerolinea
        //   irActividad(BAerolinea::class.java)
    }

    // Función para ir a una actividad
    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }

    // Función que se ejecutará al hacer clic en el botón
    fun onClickIrAActividad(view: View) {
        irActividad(BAerolinea::class.java)}
}
