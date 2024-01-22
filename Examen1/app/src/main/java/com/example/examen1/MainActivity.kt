package com.example.examen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.database.sqlite.SQLiteDatabase
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Inicializar la base de datos y sus tablas
        val dbHelper = BD(this)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        //dbHelper.onCreate(db)

        if (db != null) {
            // Puedes realizar acciones adicionales relacionadas con la base de datos si es necesario
        } else {
            Toast.makeText(this, "ERROR AL CREAR LA BD", Toast.LENGTH_LONG).show()
        }

        // Ir a la actividad BAerolinea
       // irActividad(BAerolinea::class.java)
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