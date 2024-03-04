package com.example.examen2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore

class BVuelo : AppCompatActivity() {
    var idItemSeleccionado = ""

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

            val vuelo = Vuelo(
                null,
                numeroVuelo.text.toString(),
                retrasado.text.toString().toBoolean(),
                cantidadPasajeros.text.toString().toInt(),
                precio.text.toString().toDouble(),
                fechaSalida.text.toString(),
                codigoAerolinea.text.toString().toInt(),
            )

            insertarVueloEnFirestore(vuelo)
        }
    }

    private fun insertarVueloEnFirestore(vuelo: Vuelo) {
        val firestore = FirebaseFirestore.getInstance()

        firestore.collection("vuelos")
            .add(vuelo.toMap())
            .addOnSuccessListener { documentReference ->
                Toast.makeText(this, "Vuelo agregado con ID: ${documentReference.id}", Toast.LENGTH_SHORT).show()
                cleanEditText()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al agregar vuelo: $e", Toast.LENGTH_SHORT).show()
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
        idItemSeleccionado = info.position.toString()
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarvuelo -> {
                "$idItemSeleccionado"
                return true
            }
            R.id.mi_eliminarvuelo -> {
                "$idItemSeleccionado"
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }


    // Limpiar los campos
    private fun cleanEditText() {

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

    // Convertir materia a un mapa para almacenar en Firestore
    private fun Vuelo.toMap(): Map<String, Any?> {
        return hashMapOf(
            "numeroIDVuelo" to numeroIDVuelo,
            "Retrasado" to Retrasado,
            "cantPasajeros" to cantPasajeros,
            "precioV" to precioV,
            "fechaSal" to fechaSal,
            "CodigoAerolinea" to codAerolinea
        )
    }
}