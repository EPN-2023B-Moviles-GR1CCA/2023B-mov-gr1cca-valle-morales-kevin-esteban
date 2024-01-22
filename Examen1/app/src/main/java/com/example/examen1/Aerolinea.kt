package com.example.examen1

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class Aerolinea(
    var codAerolinea: Int?,
    var nombreAL: String?,
    var Internacional: Boolean,
    var cantidadVuelos: Int,
    var ingresos: Double,
    var fechaFund: String?,
    val context: Context?
) {

    // Métodos Set

    fun setIdAerolinea(idAerolinea: Int) {
        this.codAerolinea = idAerolinea
    }

    fun setNombre(nombre: String) {
        this.nombreAL = nombre
    }

    fun setEsInternacional(esInternacional: Boolean) {
        this.Internacional = esInternacional
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
    // fun setnumeroVuelo(numeroIDVuelo: String){
    //   this.numeroIDVuelo = numeroIDVuelo
    // }

    // Métodos Get
    fun getIdAerolinea(): Int? {
        return codAerolinea
    }

    fun getNombre(): String? {
        return nombreAL
    }

    fun getEsInternacional(): Boolean {
        return Internacional
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
    fun insertarAerolinea(): Long {
        val dbHelper: BD = BD(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val values: ContentValues = ContentValues()

        values.put("codAerolinea", this.codAerolinea)
        values.put("nombreAL", this.nombreAL)
        values.put("Internacional", this.Internacional)
        values.put("cantidadVuelos", this.cantidadVuelos)
        values.put("ingresos", this.ingresos)
        values.put("fechaFund", this.fechaFund)
        // values.put("NumeroVuelo", this.numeroIDVuelo)

        return db.insert("t_aerolinea", null, values)
    }

    // Función Mostrar
    fun mostrarAerolinea(): ArrayList<Aerolinea> {
        val dbHelper: BD = BD(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        val listaAerolinea = ArrayList<Aerolinea>()
        var aerolinea: Aerolinea
        var cursorAerolinea: Cursor? = null

        cursorAerolinea = db.rawQuery("SELECT * FROM t_aerolinea", null)

        if (cursorAerolinea.moveToFirst()) {
            do {
                aerolinea = Aerolinea(null,"", false, 0, 0.0, "", null)

                aerolinea.setIdAerolinea(cursorAerolinea.getString(0).toInt())
                aerolinea.setNombre(cursorAerolinea.getString(1))
                // Obtener el valor de la columna 1 como un entero y convertirlo a booleano
                val esInternacionalInt = cursorAerolinea.getInt(2)
                val esInternacional = esInternacionalInt == 1
                aerolinea.setEsInternacional(esInternacional)
                aerolinea.setCantidadVuelosDiarios(cursorAerolinea.getInt(3))
                aerolinea.setIngresosMensuales(cursorAerolinea.getDouble(4))
                aerolinea.setFechaFundacion(cursorAerolinea.getString(5))
                listaAerolinea.add(aerolinea)
            } while (cursorAerolinea.moveToNext())
        }

        cursorAerolinea.close()
        return listaAerolinea
    }

    // Función Obtener por ID
    fun getAerolineaById(id: Int): Aerolinea {
        val dbHelper = BD(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var aerolinea = Aerolinea(null,"", false, 0, 0.0, "", this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_aerolinea WHERE codAerolinea = ${id+1}", null)

        if (cursor.moveToFirst()) {
            do {
                aerolinea.setIdAerolinea(cursor.getString(0).toInt())
                aerolinea.setNombre(cursor.getString(1))
                // Obtener el valor de la columna 1 como un entero y convertirlo a booleano
                val esInternacionalInt = cursor.getInt(2)
                val esInternacional = esInternacionalInt == 1
                aerolinea.setEsInternacional(esInternacional)
                aerolinea.setCantidadVuelosDiarios(cursor.getInt(3))
                aerolinea.setIngresosMensuales(cursor.getDouble(4))
                aerolinea.setFechaFundacion(cursor.getString(5))
            } while (cursor.moveToNext())
        }

        cursor.close()
        return aerolinea
    }

    // Función Eliminar
    fun deleteAerolinea(id: Int): Int {
        val dbHelper = BD(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        return db.delete("t_aerolinea", "codAerolinea="+(id+1), null)
    }

    // Función Actualizar
    fun updateAerolinea(): Int {
        val dbHelper = BD(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values: ContentValues = ContentValues()

        values.put("nombreAL", this.nombreAL)
        values.put("Internacional", this.Internacional)
        values.put("CantidadVuelos", this.cantidadVuelos)
        values.put("ingresos", this.ingresos)
        values.put("FechaFund", this.fechaFund)
        return db.update("t_aerolinea", values, "codAerolinea="+this.codAerolinea, null)
    }

    // Método toString
    override fun toString(): String {
        val salida =
            "ID Aerolínea: ${codAerolinea}\n" +
                    "Nombre: ${nombreAL}\n" +
                    "Es Internacional: ${Internacional}\n" +
                    "Cantidad de Vuelos Diarios: ${cantidadVuelos}\n" +
                    "Ingresos Mensuales: ${ingresos}\n" +
                    "Fecha Fundación: ${fechaFund}"

        return salida
    }
}

