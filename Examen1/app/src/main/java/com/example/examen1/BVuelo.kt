package com.example.examen1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class BVuelo : AppCompatActivity() {

    var idItemSeleccionado = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bvuelo)

        val numeroVuelo = findViewById<EditText>(R.id.ed_numeroVuelo)
        numeroVuelo.requestFocus()

        val retrasado = findViewById<EditText>(R.id.edt_retrasado)
        val cantidadPasajeros = findViewById<EditText>(R.id.edt_cantidadPasajeros)
        val precio = findViewById<EditText>(R.id.edt_precio)
        val fechaSalida = findViewById<EditText>(R.id.edt_fechaSalida)
        val codigoAerolinea = findViewById<EditText>(R.id.edt_codigoAerolinea)

        val btnInsertar = findViewById<Button>(R.id.btn_InsertarVuelo)
        btnInsertar.setOnClickListener {

            val vuelo: Vuelo = Vuelo(
                null,
                numeroVuelo.text.toString(),
                retrasado.text.toString().toBoolean(),
                cantidadPasajeros.text.toString().toInt(),
                precio.text.toString().toDouble(),
                fechaSalida.text.toString(),
                codigoAerolinea.text.toString().toInt(),
                this
            )

            val resultado = vuelo.insertarVuelo()

            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show()
                cleanEditText()
            } else {
                Toast.makeText(this, "ERROR AL INSERTAR REGISTRO", Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)

        val inflater = menuInflater
        inflater.inflate(R.menu.menu_vuelo, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idItemSeleccionado = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarvuelo -> {
                "${idItemSeleccionado}"
                return true
            }
            R.id.mi_eliminarvuelo -> {
                "${idItemSeleccionado}"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    // Limpiar los campos
    fun cleanEditText() {

        val codigoAerolinea = findViewById<EditText>(R.id.edt_codigoAerolinea)
        codigoAerolinea.setText("")

        val numeroVuelo = findViewById<EditText>(R.id.ed_numeroVuelo)
        numeroVuelo.setText("")

        val retrasado = findViewById<EditText>(R.id.edt_retrasado)
        retrasado.setText("")

        val cantidadPasajeros = findViewById<EditText>(R.id.edt_cantidadPasajeros)
        cantidadPasajeros.setText("")

        val precio = findViewById<EditText>(R.id.edt_precio)
        precio.setText("")

        val fechaSalida = findViewById<EditText>(R.id.edt_fechaSalida)
        fechaSalida.setText("")

    }
}