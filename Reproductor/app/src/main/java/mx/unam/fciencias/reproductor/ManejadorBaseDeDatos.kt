package mx.unam.fciencias.reproductor

import android.content.ContentValues
import android.database.sqlite.SQLiteDatabase

class ManejadorBaseDeDatos {

     private val bdd : SQLiteDatabase

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

    constructor(bdd: SQLiteDatabase) {
        this.bdd = bdd
    }

    //crea las tablas y agrega los datos de la tabla types
    fun inicializa() {
        bdd.execSQL(types)
        bdd.execSQL(performers)
        bdd.execSQL(persons)
        bdd.execSQL(groups)
        bdd.execSQL(albums)
        bdd.execSQL(rolas)
        bdd.execSQL(in_group)


        if (bdd != null) {
            val datos = ContentValues()
            datos.put("description", "Person")
            bdd.insert("types", null, datos)
            datos.clear()
            datos.put("description", "Group")
            bdd.insert("types", null, datos)
            datos.clear()
            datos.put("description", "Unknow")
            bdd.insert("types", null, datos)

        }
    }

    //agrega datos a la tabla performers
    fun agregaPerformer(tipo: Int, nombre: String) {

        val cursor = bdd.rawQuery("SELECT * FROM performers WHERE name = '$nombre'", null)

        if (cursor.count == 0) {

            val datos = ContentValues()
            datos.put("id_type", tipo)
            datos.put("name", nombre)

            bdd.insert("performers", null, datos)
        }
    }

    //agrega datos a la tabla persons
    fun agregaPersona(nombreArtistico: String, nombre: String, nacimiento: String, muerte: String) {

        val cursor = bdd.rawQuery("SELECT * FROM persons WHERE stage_name = '$nombreArtistico'", null)

        if (cursor.count == 0) {

            val datos = ContentValues()
            datos.put("stage_name", nombreArtistico)
            datos.put("real_name", nombre)
            datos.put("birth_date", nacimiento)
            datos.put("death_date", muerte)

            bdd.insert("persons", null, datos)
        }
    }
}