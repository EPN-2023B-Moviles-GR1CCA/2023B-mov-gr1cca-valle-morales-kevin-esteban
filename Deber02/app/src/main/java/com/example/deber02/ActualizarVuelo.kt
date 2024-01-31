package com.example.deber02

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActualizarVuelo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_vuelo)

        val idVuelo = VerVuelo.idVueloSeleccionado
        var vuelo = Vuelo(null,"", false, 0, 0.0, "", 0,this)
        vuelo = vuelo.getVueloById(idVuelo)

        var codVuelo  = findViewById<EditText>(R.id.edt_codVuelo)
        codVuelo.setText(vuelo.getcodVuelo().toString())

        val numeroVuelo = findViewById<EditText>(R.id.edtNumeroVuelo)
        numeroVuelo.setText(vuelo.getNumeroVuelo())

        val retrasado = findViewById<EditText>(R.id.edtRetrasado)
        retrasado.setText(vuelo.getEstaRetrasado().toString())

        val cantidadPasajeros = findViewById<EditText>(R.id.edtCantidadPasajeros)
        cantidadPasajeros.setText(vuelo.getCantidadPasajeros().toString())

        val precio = findViewById<EditText>(R.id.edtPrecio)
        precio.setText(vuelo.getPrecio().toString())

        val fechaSalida = findViewById<EditText>(R.id.edtFechaSalida)
        fechaSalida.setText(vuelo.getFechaSalida())

        var idA = findViewById<EditText>(R.id.edt_idAerolinea)
        idA.setText(vuelo.getCodigoAerolinea().toString())

        val btnActualizarVuelo = findViewById<Button>(R.id.btnActualizarVuelo)
        btnActualizarVuelo.setOnClickListener {

            vuelo.setNumeroVuelo(numeroVuelo.text.toString())
            vuelo.setEstaRetrasado(retrasado.text.toString().toBoolean())
            vuelo.setCantidadPasajeros(cantidadPasajeros.text.toString().toInt())
            vuelo.setPrecio(precio.text.toString().toDouble())
            vuelo.setFechaSalida(fechaSalida.text.toString())
            vuelo.setCodigoAerolinea(idA.text.toString().toInt())

            val resultado = vuelo.updateVuelo()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    // Limpiar las cajas de texto
    private fun cleanEditText() {
        val numeroVuelo = findViewById<EditText>(R.id.edtNumeroVuelo)
        numeroVuelo.setText("")

        val retrasado = findViewById<EditText>(R.id.edtRetrasado)
        retrasado.setText("")

        val cantidadPasajeros = findViewById<EditText>(R.id.edtCantidadPasajeros)
        cantidadPasajeros.setText("")

        val precio = findViewById<EditText>(R.id.edtPrecio)
        precio.setText("")

        val fechaSalida = findViewById<EditText>(R.id.edtFechaSalida)
        fechaSalida.setText("")

        val codA = findViewById<EditText>(R.id.edt_idAerolinea)
        codA.setText("")
    }
}