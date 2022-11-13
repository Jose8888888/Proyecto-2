package mx.unam.fciencias.reproductor

import android.content.ContentValues
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class CreadorBaseDeDatos : SQLiteOpenHelper {



    constructor (context: Context?) : super(context, "basededatos.sqlite", null, 1) {


    }

    override fun onCreate(db: SQLiteDatabase) {
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}





}