package com.example.deber02

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase

class Vuelo(
    var codVuelo: Int?,
    var numeroIDVuelo: String?,
    var Retrasado: Boolean,
    var cantPasajeros: Int,
    var precioV: Double,
    var fechaSal: String?,
    var codAerolinea: Int,
    val context: Context?
) {

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

    fun getCantidadPasajeros(): Int {
        return cantPasajeros
    }

    fun getPrecio(): Double {
        return precioV
    }

    fun getFechaSalida(): String? {
        return fechaSal
    }
    fun getCodigoAerolinea(): Int {
        return codAerolinea
    }

    // Función Insertar
    fun insertarVuelo(): Long {
        val dbHelper = BD(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values: ContentValues = ContentValues()

        values.put("numeroIDVuelo", this.numeroIDVuelo)
        values.put("Retrasado", this.Retrasado)
        values.put("cantPasajeros", this.cantPasajeros)
        values.put("precioV", this.precioV)
        values.put("fechaSal", this.fechaSal)
        values.put("codAerolinea", this.codAerolinea)


        return db.insert("t_vuelo", null, values)
    }


    // Función Mostrar
    fun mostrarVuelo(id: Int): ArrayList<Vuelo> {
        val dbHelper = BD(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var lista = ArrayList<Vuelo>()
        // var vuelo: Vuelo
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_vuelo WHERE idVuelo = ${id+1}", null)
        //cursor = db.rawQuery("SELECT * FROM t_vuelo WHERE codAerolinea = $codAerolinea", null)

        if (cursor.moveToFirst()) {
            do {
                val vuelo = Vuelo(null,"", false, 0, 0.0, "",0, null)

                vuelo.setcodVuelo(cursor.getString(0).toInt())
                vuelo.setNumeroVuelo(cursor.getString(1))
                // Obtener el valor de la columna 1 como un entero y convertirlo a booleano
                val estaRetrasadoInt = cursor.getInt(2)
                val estaRetrasado = estaRetrasadoInt == 1
                vuelo.setEstaRetrasado(estaRetrasado)
                vuelo.setCantidadPasajeros(cursor.getInt(3))
                vuelo.setPrecio(cursor.getDouble(4))
                vuelo.setFechaSalida(cursor.getString(5))
                vuelo.setCodigoAerolinea(cursor.getInt(6))
                lista.add(vuelo)
            } while (cursor.moveToNext())
        }

        cursor.close()
        return lista
    }

    // Función Obtener por ID
    fun getVueloById(id: Int): Vuelo {
        val dbHelper = BD(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        var vuelo = Vuelo(null,"", false, 0, 0.0, "", 0, this.context)
        var cursor: Cursor? = null

        cursor = db.rawQuery("SELECT * FROM t_vuelo WHERE idVuelo = ${id+1}", null)

        if (cursor.moveToFirst()) {
            do {
                vuelo.setcodVuelo(cursor.getString(0).toInt())
                vuelo.setNumeroVuelo(cursor.getString(1))
                // Obtener el valor de la columna 1 como un entero y convertirlo a booleano
                val estaRetrasadoInt = cursor.getInt(2)
                val estaRetrasado = estaRetrasadoInt == 1
                vuelo.setEstaRetrasado(estaRetrasado)
                vuelo.setCantidadPasajeros(cursor.getInt(3))
                vuelo.setPrecio(cursor.getDouble(4))
                vuelo.setFechaSalida(cursor.getString(5))
                vuelo.setCodigoAerolinea(cursor.getString(6).toInt())
            } while (cursor.moveToNext())
        }

        cursor.close()
        return vuelo
    }

    // Función Eliminar
    fun deleteVuelo(id: Int): Int {
        val dbHelper = BD(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase

        return db.delete("t_vuelo", "idVuelo=?", arrayOf((id+1).toString()))
    }

    // Función Actualizar
    fun updateVuelo(): Int {
        val dbHelper = BD(this.context)
        val db: SQLiteDatabase = dbHelper.writableDatabase
        val values: ContentValues = ContentValues()

        values.put("NumeroIDVuelo", this.numeroIDVuelo)
        values.put("Retrasado", this.Retrasado)
        values.put("cantPasajeros", this.cantPasajeros)
        values.put("precioV", this.precioV)
        values.put("fechaSal", this.fechaSal)
        values.put("codAerolinea", this.codAerolinea)

        return db.update("t_vuelo", values, "idVuelo="+this.codVuelo, null)
    }

    // Método toString
    override fun toString(): String {
        val salida =
            "Número de Vuelo: ${numeroIDVuelo}\n" +
                    "¿Está Retrasado?: ${Retrasado}\n" +
                    "Cantidad de Pasajeros: ${cantPasajeros}\n" +
                    "Precio: ${precioV}\n" +
                    "Fecha de Salida: ${fechaSal}" +
                     "Código de Aerolínea: ${codAerolinea}"


        return salida
    }
}
