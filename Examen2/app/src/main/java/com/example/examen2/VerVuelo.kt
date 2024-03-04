package com.example.examen2

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.google.firebase.firestore.FirebaseFirestore

class VerVuelo : AppCompatActivity() {

    companion object {
        var idVueloSeleccionado = ""
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_vuelo)

        val idAerolinea = BAerolinea.idSeleccionado
        val aerolinea = Aerolinea( null,"", false, 0, 0.0, "", this)

        /* val textViewPadre = findViewById<TextView>(R.id.tv_padreVerVuelo)
          textViewPadre.text = "Aerolinea: " + aerolinea.getAerolineaById(idAerolinea).getNombre()*/

        val btnCrearVuelos = findViewById<Button>(R.id.btn_CrearVuelo)
        btnCrearVuelos.setOnClickListener {
            irActividad(BVuelo::class.java)
        }
          showListView(idAerolinea)
    }

 private fun showListView(id:Int) {
    val listViewVuelos = findViewById<ListView>(R.id.lv_Vuelo)
    val firestore = FirebaseFirestore.getInstance()

    firestore.collection("vuelos")
        .whereEqualTo("CodAerolinea", id)
        .get()
        .addOnSuccessListener { documents ->
            val vuelos = ArrayList<String>()
            val vueloIds = ArrayList<String>()

            for (document in documents) {
                val nombreVuelo = document.getString("nombreVuelo") ?: ""
                vuelos.add(nombreVuelo)
                vueloIds.add(document.id)
            }
            val adaptador = ArrayAdapter(
        this,
               android.R.layout.simple_list_item_1,
               vuelos
            )
            listViewVuelos.adapter = adaptador
            listViewVuelos.setOnItemClickListener { _, _, position, _ ->
                idVueloSeleccionado = vueloIds[position]
            }
            registerForContextMenu(listViewVuelos)
        }
        .addOnFailureListener { exception ->
            Toast.makeText(this, "Error al obtener vuelos: $exception", Toast.LENGTH_LONG).show()
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
idVueloSeleccionado = info.position.toString()
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

private fun abrirDialogo() {
val builder = AlertDialog.Builder(this)
builder.setTitle("¿Desea eliminar este vuelo?")
builder.setPositiveButton("SI") {  _, _ ->
    val firestore = FirebaseFirestore.getInstance()
    firestore.collection("vuelos").document(idVueloSeleccionado)
        .delete()
        .addOnSuccessListener {
            Toast.makeText(this, "Vuelo eliminado correctamente", Toast.LENGTH_LONG).show()
            val idAerolinea = BAerolinea.idSeleccionado
            showListView(idAerolinea)
        }
        .addOnFailureListener { e ->
            Toast.makeText(this, "Error al eliminar vuelo: $e", Toast.LENGTH_LONG).show()
        }
    }
    builder.setNegativeButton("NO", null)
    val dialogo = builder.create()
    dialogo.show()
}

   private fun irActividad(clase: Class<*>) {
       val intent = Intent(this, clase)
       startActivity(intent)
      }
}