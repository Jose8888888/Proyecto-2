package mx.unam.fciencias.reproductor

import android.content.ContentValues
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class CreaBaseDeDatos : SQLiteOpenHelper {

    private val types =
        "CREATE TABLE IF NOT EXISTS types(id_type INTEGER PRIMARY KEY, description TEXT);"
    private val performers =
        "CREATE TABLE IF NOT EXISTS performers ( id_performer INTEGER PRIMARY KEY, id_type INTEGER, name TEXT, FOREIGN KEY (id_type) REFERENCES types(id_type) );"
    private val persons =
        "CREATE TABLE IF NOT EXISTS persons ( id_person INTEGER PRIMARY KEY, stage_name TEXT, real_name TEXT, birth_date TEXT, death_date TEXT );"
    private val groups =
        "CREATE TABLE IF NOT EXISTS groups ( id_group INTEGER PRIMARY KEY, path TEXT, start_date TEXT, end_date TEXT );"
    private val albums =
        "CREATE TABLE IF NOT EXISTS albums ( id_album INTEGER PRIMARY KEY, path TEXT, name TEXT, year INTEGER );"
    private val rolas =
        "CREATE TABLE IF NOT EXISTS rolas ( id_rola INTEGER PRIMARY KEY, id_performer INTEGER, id_album INTEGER, path TEXT, tittle TEXT, track INTEGER, year INTEGER, genre TEXT, " +
                "FOREIGN KEY (id_performer) REFERENCES performers(id_performer), FOREIGN KEY (id_album) REFERENCES albums(id_album) );"
    private val in_group =
        "CREATE TABLE IF NOT EXISTS in_group ( id_person INTEGER, id_group INTEGER, PRIMARY KEY (id_person, id_group), FOREIGN KEY (id_person) REFERENCES persons(id_person), " +
                "FOREIGN KEY (id_group) REFERENCES groups(id_group) );"


    constructor (context: Context?) : super(context, "basededatos.sqlite", null, 1) {

    }

    override fun onCreate(db: SQLiteDatabase) {

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {}


    //agrega los datos de la tabla types
     fun agregaTipos(db: SQLiteDatabase) {
        db.execSQL(types)
        db.execSQL(performers)
        db.execSQL(persons)
        db.execSQL(groups)
        db.execSQL(albums)
        db.execSQL(rolas)
        db.execSQL(in_group)

        println("oooooooooooooooooooooooooooooooooooooooooooooooooosmg")

        if (db != null) {
            val datos = ContentValues()
            datos.put("description", "Person")
            println(datos)
            db.insert("types", null, datos)
            datos.clear()
            datos.put("description", "Group")
            db.insert("types", null, datos)
            datos.clear()
            datos.put("description", "Unknow")
            db.insert("types", null, datos)

        }
    }




}