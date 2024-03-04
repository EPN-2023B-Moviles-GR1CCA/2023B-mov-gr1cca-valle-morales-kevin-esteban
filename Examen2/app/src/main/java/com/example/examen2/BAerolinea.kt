package com.example.examen2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import android.widget.ArrayAdapter
import android.widget.ListView
import android.content.Intent
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView

class BAerolinea : AppCompatActivity() {

    companion object {
        var idSeleccionado = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_baerolinea)
        showListViewAerolinea()

        val nombre = findViewById<EditText>(R.id.editTextText_NombreAerolinea)
        nombre.requestFocus()
        val esInternacional = findViewById<EditText>(R.id.editTextText_EsInternacional)
        val vuelosAlDia = findViewById<EditText>(R.id.editTextText_VuelosAlDia)
        val ingresosMensuales = findViewById<EditText>(R.id.editTextText_IngresosMensuales)
        val fechaFundacion = findViewById<EditText>(R.id.editTextText_FechaFundacion)

        val btnCrearAerolinea = findViewById<Button>(R.id.btnCrearAerolinea)
        btnCrearAerolinea.setOnClickListener {
            val aerolinea = Aerolinea(
                null,
                nombre.text.toString(),
                esInternacional.text.toString().toBoolean(),
                vuelosAlDia.text.toString().toInt(),
                ingresosMensuales.text.toString().toDouble(),
                fechaFundacion.text.toString(),
                this
            )
            val idInsertado = aerolinea.insertarAerolinea()

            if (idInsertado != null) {
                Toast.makeText(this, "Registro Guardado", Toast.LENGTH_LONG).show()
                cleanEditText()
                showListViewAerolinea()
            } else {
                Toast.makeText(this, "Error", Toast.LENGTH_LONG).show()
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
        inflater.inflate(R.menu.menu_aerolinea, menu)
        val info = menuInfo as AdapterView.AdapterContextMenuInfo
        idSeleccionado = info.position
    }
    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editaraerolinea -> {
                irActividad(ActualizarAerolinea::class.java)
                return true
            }
            R.id.mi_eliminaraerolinea -> {
                abrirDialogo()
                return true
            }
            R.id.mi_vervuelo -> {
                irActividad(VerVuelo::class.java)
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

     private fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar esta aerolínea?")

        builder.setPositiveButton("SI") { dialog, which ->
            if (idSeleccionado  >= 0 ) {
                val aerolinea = Aerolinea(null,"", false, 0, 0.0, "", this)

                aerolinea.deleteAerolinea {resultado ->
                    if (resultado) {
                        Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
                        runOnUiThread {
                            showListViewAerolinea()
                        }
                    } else {
                        Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
                    }
                 }
                }else {
                    Toast.makeText(this, "Selección no válida", Toast.LENGTH_LONG).show()
                }
        }

        builder.setNegativeButton("NO") { dialog, which ->
            Toast.makeText(this, "Operación cancelada", Toast.LENGTH_LONG).show()
        }

        val dialogo = builder.create()
        dialogo.show()
    }

    private fun showListViewAerolinea() {
        val aerolinea = Aerolinea(null,"",  false, 0, 0.0, "", this)
        aerolinea.mostrarAerolinea { listaAerolineas ->
        val listView = findViewById<ListView>(R.id.lvView_Aerolinea)
        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            listaAerolineas
        )
        listView.adapter = adaptador
        registerForContextMenu(listView)
      }
    }

   private fun cleanEditText() {
        val nombre = findViewById<EditText>(R.id.editTextText_NombreAerolinea)
        val esInternacional = findViewById<EditText>(R.id.editTextText_EsInternacional)
        val vuelosAlDia = findViewById<EditText>(R.id.editTextText_VuelosAlDia)
        val ingresosMensuales = findViewById<EditText>(R.id.editTextText_IngresosMensuales)
        val fechaFundacion = findViewById<EditText>(R.id.editTextText_FechaFundacion)

        nombre.text.clear()
        esInternacional.text.clear()
        vuelosAlDia.text.clear()
        ingresosMensuales.text.clear()
        fechaFundacion.text.clear()

        nombre.requestFocus()
    }

   private fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}
