package com.example.deber02

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
        aerolinea = aerolinea.getAerolineaById(idAerolinea)

        val nombre = findViewById<EditText>(R.id.tv_updNombreAerolinea)
        nombre.setText(aerolinea.getNombre())

        val internacional = findViewById<EditText>(R.id.tv_updInternacionalAerolinea)
        internacional.setText(aerolinea.getEsInternacional().toString())

        val cantidadVuelosDia = findViewById<EditText>(R.id.tv_updCantidadVuelosAerolinea)
        cantidadVuelosDia.setText(aerolinea.getCantidadVuelosDiarios().toString())

        val ingresosMensuales = findViewById<EditText>(R.id.tv_updIngresosMensualesAerolinea)
        ingresosMensuales.setText(aerolinea.getIngresosMensuales().toString())

        val fechaFundacion = findViewById<EditText>(R.id.tv_updFechaFundacionAerolinea)
        fechaFundacion.setText(aerolinea.getFechaFundacion())

        // Dentro de la actividad ActualizarAerolinea
        val btnActualizarAerolinea = findViewById<Button>(R.id.btn_updAerolinea)

        btnActualizarAerolinea.setOnClickListener {
            aerolinea.setNombre(nombre.text.toString())
            aerolinea.setEsInternacional(internacional.text.toString().toBoolean())
            aerolinea.setCantidadVuelosDiarios(cantidadVuelosDia.text.toString().toInt())
            aerolinea.setIngresosMensuales(ingresosMensuales.text.toString().toDouble())
            aerolinea.setFechaFundacion(fechaFundacion.text.toString())

            val resultado = aerolinea.updateAerolinea()

            // Después de la actualización y obtener los nuevos datos
            if (resultado > 0) {
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