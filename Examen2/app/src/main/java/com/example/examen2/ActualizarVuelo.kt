package com.example.examen2

import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class ActualizarVuelo : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_vuelo)

        val idVuelo = VerVuelo.idVueloSeleccionado
        val vuelo = Vuelo(null,"", false, 0, 0.0, "", 0)

        vuelo.getVueloById(idVuelo) { vueloResult ->
            if (vueloResult != null) {
                findViewById<EditText>(R.id.edt_codVuelo).setText(vueloResult.getcodVuelo().toString())
                findViewById<EditText>(R.id.edtNumeroVuelo).setText(vueloResult.getNumeroVuelo())
                findViewById<EditText>(R.id.edtRetrasado).setText(vueloResult.getEstaRetrasado().toString())
                findViewById<EditText>(R.id.edtCantidadPasajeros).setText(vueloResult.getCantidadPasajeros().toString())
                findViewById<EditText>(R.id.edtPrecio).setText(vueloResult.getPrecio().toString())
                findViewById<EditText>(R.id.edtFechaSalida).setText(vueloResult.getFechaSalida())
                findViewById<EditText>(R.id.edt_idAerolinea).setText(vueloResult.getCodigoAerolinea().toString())
            } else {
                Toast.makeText(this, "No se pudo obtener el vuelo", Toast.LENGTH_SHORT).show()
            }
        }


        val btnActualizarVuelo = findViewById<Button>(R.id.btnActualizarVuelo)
        btnActualizarVuelo.setOnClickListener {

            val numeroVuelo = findViewById<EditText>(R.id.edtNumeroVuelo).text.toString()
            val retrasado = findViewById<EditText>(R.id.edtRetrasado).text.toString().toBoolean()
            val cantidadPasajeros = findViewById<EditText>(R.id.edtCantidadPasajeros).text.toString().toInt()
            val precio = findViewById<EditText>(R.id.edtPrecio).text.toString().toDouble()
            val fechaSalida = findViewById<EditText>(R.id.edtFechaSalida).text.toString()
            val idA = findViewById<EditText>(R.id.edt_idAerolinea).text.toString().toInt()


            vuelo.setNumeroVuelo(numeroVuelo)
            vuelo.setEstaRetrasado(retrasado)
            vuelo.setCantidadPasajeros(cantidadPasajeros)
            vuelo.setPrecio(precio)
            vuelo.setFechaSalida(fechaSalida)
            vuelo.setCodigoAerolinea(idA)

            vuelo.updateVuelo { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
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