package com.example.examen1

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity

class VerVuelo : AppCompatActivity() {

    companion object {
        var idVueloSeleccionado = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_vuelo)

        val idAerolinea = BAerolinea.idSeleccionado
        var aerolinea = Aerolinea( null,"", false, 0, 0.0, "", this)
        val textViewPadre = findViewById<TextView>(R.id.tv_padreVerVuelo)
        textViewPadre.text = "Aerolinea: " + aerolinea.getAerolineaById(idAerolinea).getNombre()

        val btnCrearVuelos = findViewById<Button>(R.id.btn_CrearVuelo)
        btnCrearVuelos.setOnClickListener {
            irActividad(BVuelo::class.java)
        }
        showListView(idAerolinea)
    }
    fun showListView(id:Int) {
        val objVuelo = Vuelo(null,"", false, 0, 0.0, "", 0, this)
        val listViewVuelos = findViewById<ListView>(R.id.lv_Vuelo)

        val adaptador = ArrayAdapter(
            this,
            android.R.layout.simple_list_item_1,
            objVuelo.mostrarVuelo(id)
        )
        listViewVuelos.adapter = adaptador
        adaptador.notifyDataSetChanged()
        registerForContextMenu(listViewVuelos)
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
        idVueloSeleccionado = info.position
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.mi_editarvuelo -> {
                irActividad(ActualizarVuelo::class.java)
                return true
            }
            R.id.mi_eliminarvuelo -> {
                abrirDialogo()
                return true
            }
            else -> super.onContextItemSelected(item)
        }
    }

    fun abrirDialogo() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("¿Desea eliminar este vuelo?")
        builder.setPositiveButton(
            "SI"
        ) { dialog, which ->
            val vuelo = Vuelo(null,"", false, 0, 0.0, "", 0, this)
            val resultado = vuelo.deleteVuelo(idVueloSeleccionado)
            if (resultado > 0) {
                Toast.makeText(this, "REGISTRO ELIMINADO", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "ERROR AL ELIMINAR REGISTRO", Toast.LENGTH_LONG).show()
            }
            val idAerolinea = BAerolinea.idSeleccionado
            showListView(idAerolinea)
        }
        builder.setNegativeButton("NO", null)

        val dialogo = builder.create()
        dialogo.show()
    }

    fun irActividad(clase: Class<*>) {
        val intent = Intent(this, clase)
        startActivity(intent)
    }
}