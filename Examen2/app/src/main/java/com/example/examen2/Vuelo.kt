package com.example.examen2

import android.content.ContentValues
import android.content.Context
import android.util.Log

import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class Vuelo(
    var codVuelo: Int?,
    var numeroIDVuelo: String?,
    var Retrasado: Boolean,
    var cantPasajeros: Int?,
    var precioV: Double?,
    var fechaSal: String?,
    var codAerolinea: Int?
) {
    companion object {
        private const val TAG = "Vuelo"
    }

    // Métodos Set
    fun setcodVuelo(cVuelo: Int) {
        this.codVuelo = cVuelo
    }
    fun setNumeroVuelo(numeroVuelo: String) {
        this.numeroIDVuelo = numeroVuelo
    }

    fun setEstaRetrasado(estaRetrasado: Boolean) {
        this.Retrasado = estaRetrasado
    }

    fun setCantidadPasajeros(cantidadPasajeros: Int) {
        this.cantPasajeros = cantidadPasajeros
    }

    fun setPrecio(precio: Double) {
        this.precioV = precio
    }

    fun setFechaSalida(fechaSalida: String?) {
        this.fechaSal = fechaSalida
    }
    fun setCodigoAerolinea(codigoAerolinea: Int) {
        this.codAerolinea = codigoAerolinea
    }

    // Métodos Get
    fun getcodVuelo(): Int? {
        return codVuelo
    }

    fun getNumeroVuelo(): String? {
        return numeroIDVuelo
    }

    fun getEstaRetrasado(): Boolean {
        return Retrasado
    }

    fun getCantidadPasajeros(): Int? {
        return cantPasajeros
    }

    fun getPrecio(): Double? {
        return precioV
    }

    fun getFechaSalida(): String? {
        return fechaSal
    }
    fun getCodigoAerolinea(): Int? {
        return codAerolinea
    }

    // Función Insertar
    fun insertarVuelo() {
        val db = Firebase.firestore
        val vuelo = hashMapOf(
            "codVuelo" to this.codVuelo,
            "numeroIDVuelo" to this.numeroIDVuelo,
            "Retrasado" to this.Retrasado,
            "cantPasajeros" to this.cantPasajeros,
            "precioV" to this.precioV,
            "fechaSal" to this.fechaSal,
            "codAerolinea" to this.codAerolinea
        )

        db.collection("vuelos")
            .add(vuelo)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Vuelo agregado con ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al agregar vuelo", e)
            }

    }


    // Función Mostrar
    fun mostrarVuelo(callback: (ArrayList<Vuelo>) -> Unit) {
        val db = Firebase.firestore
        val lista = ArrayList<Vuelo>()

        db.collection("materias")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val vuelo = Vuelo(
                        document["codVuelo"] as Int?,
                        document["numeroIDVuelo"] as String?,
                        document["Retrasado"] as Boolean,
                        document["cantPasajeros"] as Int?,
                        document["precioV"] as Double?,
                        document["fechaSal"] as String?,
                        document["codAerolinea"] as? Int ?: 0
                    )
                    lista.add(vuelo)
                }
                callback(lista)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al obtener vuelos", exception)
            }

    }

    // Función Obtener por ID
    fun getVueloById(id: String, callback: (Vuelo?) -> Unit) {

        val db = Firebase.firestore
        db.collection("vuelos")
            .whereEqualTo("codVuelo", id)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        val vuelo = Vuelo(
                            document["codVuelo"] as Int?,
                            document["numeroIDVuelo"] as String?,
                            document["Retrasado"] as Boolean,
                            document["cantPasajeros"] as Int?,
                            document["precioV"] as Double?,
                            document["fechaSal"] as String?,
                            document["codAerolinea"] as Int
                        )
                        callback(vuelo)
                        return@addOnSuccessListener
                    }
                }
                callback(null)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al obtener vuelo", exception)
                callback(null)
            }
    }

    // Función Eliminar
    fun deleteVuelo(id: Int, callback: (Boolean) -> Unit) {
        val db = Firebase.firestore
        db.collection("vuelos")
            .whereEqualTo("codVuelo", id)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        db.collection("vuelos")
                            .document(document.id)
                            .delete()
                            .addOnSuccessListener {
                                callback(true)
                            }
                            .addOnFailureListener { exception ->
                                Log.w(TAG, "Error al eliminar vuelo", exception)
                                callback(false)
                            }
                        return@addOnSuccessListener
                    }
                } else {
                    callback(false)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al eliminar vuelo", exception)
                callback(false)
            }
    }

    // Función Actualizar
    fun updateVuelo(callback: (Boolean) -> Unit) {
        val db = Firebase.firestore
        db.collection("vuelos")
            .whereEqualTo("codVuelo", codVuelo)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        val data = hashMapOf(
                            "numeroIDVuelo" to numeroIDVuelo,
                            "Retrasado" to Retrasado,
                            "cantPasajeros" to cantPasajeros,
                            "precioV" to precioV,
                            "fechaSal" to fechaSal
                        )

                        db.collection("vuelos")
                            .document(document.id)
                            .update(data as Map<String, Any>)
                            .addOnSuccessListener {
                                callback(true)
                            }
                            .addOnFailureListener { exception ->
                                Log.w(TAG, "Error al actualizar vuelo", exception)
                                callback(false)
                            }
                        return@addOnSuccessListener
                    }
                } else {
                    callback(false)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al actualizar vuelo", exception)
                callback(false)
            }
    }

    // Método toString
    override fun toString(): String {
        return "Número de Vuelo: $numeroIDVuelo\n" +
                    "¿Está Retrasado?: $Retrasado\n" +
                    "Cantidad de Pasajeros: $cantPasajeros\n" +
                    "Precio: $precioV\n" +
                    "Fecha de Salida: $fechaSal\n" +
                    "Código de Aerolínea: $codAerolinea"

    }
}
