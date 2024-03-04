package com.example.examen2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class ActualizarAerolinea : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_actualizar_aerolinea)

        val idAerolinea = BAerolinea.idSeleccionado
        var aerolinea = Aerolinea(null,"", false, 0, 0.0, "",  this)
        aerolinea.getAerolineaById(idAerolinea) { aerolineaResult ->
            if (aerolineaResult != null) {

                var nombre = findViewById<EditText>(R.id.tv_updNombreAerolinea)
                nombre.setText(aerolineaResult.getNombre() ?: "")

                var internacional = findViewById<EditText>(R.id.tv_updInternacionalAerolinea)
                internacional.setText(aerolineaResult.getEsInternacional().toString())

                var cantidadVuelosDia = findViewById<EditText>(R.id.tv_updCantidadVuelosAerolinea)
                cantidadVuelosDia.setText(aerolineaResult.getCantidadVuelosDiarios().toString())

                var ingresosMensuales = findViewById<EditText>(R.id.tv_updIngresosMensualesAerolinea)
                ingresosMensuales.setText(aerolineaResult.getIngresosMensuales().toString())

                var fechaFundacion = findViewById<EditText>(R.id.tv_updFechaFundacionAerolinea)
                fechaFundacion.setText(aerolineaResult.getFechaFundacion() ?: "")
            }else {
                Toast.makeText(this, "No se pudo obtener la aerolinea", Toast.LENGTH_SHORT).show()
            }

        }

        // Dentro de la actividad ActualizarAerolinea
        val btnActualizarAerolinea = findViewById<Button>(R.id.btn_updAerolinea)
        btnActualizarAerolinea.setOnClickListener {
            val nombre = findViewById<EditText>(R.id.tv_updNombreAerolinea).text.toString()
            val internacional = findViewById<EditText>(R.id.tv_updInternacionalAerolinea).text.toString().toBoolean()
            val cantidadVuelosDia = findViewById<EditText>(R.id.tv_updCantidadVuelosAerolinea).text.toString().toInt()
            val ingresosMensuales = findViewById<EditText>(R.id.tv_updIngresosMensualesAerolinea).text.toString().toDouble()
            val fechaFundacion = findViewById<EditText>(R.id.tv_updFechaFundacionAerolinea).text.toString()

            aerolinea.setNombre(nombre)
            aerolinea.setEsInternacional(internacional)
            aerolinea.setCantidadVuelosDiarios(cantidadVuelosDia)
            aerolinea.setIngresosMensuales(ingresosMensuales)
            aerolinea.setFechaFundacion(fechaFundacion)

            // Después de la actualización y obtener los nuevos datos
            aerolinea.updateAerolinea { isSuccess ->
            if (isSuccess) {
                Toast.makeText(this, "REGISTRO ACTUALIZADO", Toast.LENGTH_LONG).show()

                // Limpiar los campos
                cleanEditText()

                // Llamar a la función showListViewAerolinea en BAerolinea para actualizar la lista
                val intent = Intent(this, BAerolinea::class.java)
                startActivity(intent)
            } else {
                Toast.makeText(this, "ERROR AL ACTUALIZAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
      }
    }

    // Limpiar los campos
    private fun cleanEditText() {
        //val codigo = findViewById<EditText>(R.id.tv_updCodigoAerolinea)
        val nombre = findViewById<EditText>(R.id.tv_updNombreAerolinea)
        val internacional = findViewById<EditText>(R.id.tv_updInternacionalAerolinea)
        val cantidadVuelosDia = findViewById<EditText>(R.id.tv_updCantidadVuelosAerolinea)
        val ingresosMensuales = findViewById<EditText>(R.id.tv_updIngresosMensualesAerolinea)
        val fechaFundacion = findViewById<EditText>(R.id.tv_updFechaFundacionAerolinea)

        nombre.text.clear()
        internacional.text.clear()
        cantidadVuelosDia.text.clear()
        ingresosMensuales.text.clear()
        fechaFundacion.text.clear()

        // Esto hace que el cursor se ponga en la primera entrada
        nombre.requestFocus()
    }
}