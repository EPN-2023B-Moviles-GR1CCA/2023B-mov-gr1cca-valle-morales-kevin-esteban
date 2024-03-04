package com.example.examen2


import android.content.Context
import com.google.firebase.firestore.FirebaseFirestore
import android.util.Log

class Aerolinea(
    var codAerolinea: Int?,
    var nombreAL: String?,
    var internacional: Boolean,
    var cantidadVuelos: Int,
    var ingresos: Double,
    var fechaFund: String?,
    val context: Context?
) {
    companion object {
        private const val TAG = "Aerolinea"
    }

    // Métodos Set

    fun setIdAerolinea(idAerolinea: Int) {
        this.codAerolinea = idAerolinea
    }

    fun setNombre(nombre: String) {
        this.nombreAL = nombre
    }

    fun setEsInternacional(esInternacional: Boolean) {
        this.internacional = esInternacional
    }

    fun setCantidadVuelosDiarios(cantidadVuelosDiarios: Int) {
        this.cantidadVuelos = cantidadVuelosDiarios
    }

    fun setIngresosMensuales(ingresosMensuales: Double) {
        this.ingresos = ingresosMensuales
    }

    fun setFechaFundacion(fechaFundacion: String?) {
        this.fechaFund = fechaFundacion
    }


    // Métodos Get
    fun getIdAerolinea(): Int? {
        return codAerolinea
    }

    fun getNombre(): String? {
        return nombreAL
    }

    fun getEsInternacional(): Boolean {
        return internacional
    }

    fun getCantidadVuelosDiarios(): Int {
        return cantidadVuelos
    }

    fun getIngresosMensuales(): Double {
        return ingresos
    }

    fun getFechaFundacion(): String? {
        return fechaFund
    }

    // Función Insertar
    fun insertarAerolinea() {
        val firestore = FirebaseFirestore.getInstance()
        val aerolinea = hashMapOf(
        "codAerolinea" to codAerolinea,
        "nombreAL" to nombreAL,
        "internacional" to internacional,
        "cantidadVuelos" to cantidadVuelos,
        "ingresos" to ingresos,
        "fechaFund" to fechaFund
        )

        firestore.collection("aerolineas")
            .add(aerolinea)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "Aerolinea agregada con ID: ${documentReference.id}")

            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error al agregar aerolinea", e)

            }

    }

    // Función Mostrar
    fun mostrarAerolinea(callback: (ArrayList<Aerolinea>) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        val listaAerolinea = ArrayList<Aerolinea>()

        firestore.collection("aerolineas")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    val aerolinea = Aerolinea(
                        document["codAerolinea"] as Int?,
                        document["nombreAL"] as String?,
                        document["internacional"] as Boolean,
                        document["cantidadVuelos"] as Int,
                        document["ingresos"] as Double,
                        document["fechaFund"] as String?,
                        context
                    )
                    listaAerolinea.add(aerolinea)
                }
                callback(listaAerolinea)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al obtener aerolineas", exception)
            }
        }


    // Función Obtener por ID
    fun getAerolineaById(id: Int, callback: (Aerolinea?) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("aerolineas")
            .whereEqualTo("codAerolinea", id)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    val document = result.documents[0] // Suponiendo que solo hay una aerolinea con ese ID
                    val aerolinea = Aerolinea(
                        document["codAerolinea"] as Int?,
                        document["nombreAL"] as String?,
                        document["internacional"] as Boolean,
                        document["cantidadVuelos"] as Int,
                        document["ingresos"] as Double,
                        document["fechaFund"] as String?,
                        context
                    )
                    callback(aerolinea)
                } else {
                    callback(null)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al obtener aerolinea por ID", exception)
                callback(null)
            }
    }

    // Función Eliminar
    fun deleteAerolinea(callback: (Boolean) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("aerolineas")
            .whereEqualTo("codAerolinea", codAerolinea)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        firestore.collection("aerolineas")
                            .document(document.id)
                            .delete()
                            .addOnSuccessListener {
                                Log.d(TAG, "Hospital eliminado con ID: ${document.id}")
                                callback(true)
                            }
                            .addOnFailureListener { exception ->
                                Log.w(TAG, "Error al eliminar la aerolinea", exception)
                                callback(false)
                            }
                        return@addOnSuccessListener
                    }
                } else {
                    callback(false)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al eliminar la aerolinea", exception)
                callback(false)
            }
    }

    // Función Actualizar
    fun updateAerolinea(callback: (Boolean) -> Unit) {
        val firestore = FirebaseFirestore.getInstance()
        firestore.collection("aerolineas")
            .whereEqualTo("codAerolinea", codAerolinea)
            .get()
            .addOnSuccessListener { result ->
                if (!result.isEmpty) {
                    for (document in result) {
                        val data = hashMapOf(
                            "nombreAL" to nombreAL,
                            "internacional" to internacional,
                            "cantidadVuelos" to cantidadVuelos,
                            "ingresos" to ingresos,
                            "fechaFund" to fechaFund
                        )

                        firestore.collection("eaerolineas")
                            .document(document.id)
                            .update(data as Map<String, Any>)
                            .addOnSuccessListener {
                                callback(true)
                            }
                            .addOnFailureListener { exception ->
                                Log.w(TAG, "Error al actualizar aerolinea", exception)
                                callback(false)
                            }
                        return@addOnSuccessListener
                    }
                } else {
                    callback(false)
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error al actualizar aerolinea", exception)
                callback(false)
            }
    }
}
