package com.example.examen1

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class BD(context: Context?) : SQLiteOpenHelper(context, "Examen1.db", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear base de datos

        val scriptSQLCrearTablaAerolinea =
            "CREATE TABLE t_aerolinea(" +
                    "codAerolinea INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "nombreAL TEXT NOT NULL," +
                    "Internacional INTEGER NOT NULL," +  // 0: false, 1: true
                    "cantidadVuelos INTEGER NOT NULL," +
                    "ingresos REAL NOT NULL," +
                    "fechaFund TEXT NOT NULL);"

        val scriptSQLCrearTablaVuelo =
            "CREATE TABLE t_vuelo(" +
                    "idVuelo INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "numeroIDVuelo TEXT NOT NULL," +
                    "Retrasado INTEGER NOT NULL," +  // 0: false, 1: true
                    "cantPasajeros INTEGER NOT NULL," +
                    "precioV REAL NOT NULL," +
                    "fechaSal TEXT NOT NULL," +
                    "codAerolinea INTEGER NOT NULL," +
                    "FOREIGN KEY(codAerolinea) REFERENCES t_aerolinea(codAerolinea));"

        db?.execSQL(scriptSQLCrearTablaAerolinea)
        db?.execSQL(scriptSQLCrearTablaVuelo)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS t_aerolinea")
        db?.execSQL("DROP TABLE IF EXISTS t_vuelo")
        onCreate(db)
    }
}
