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

    //agrega datos a la tabla groups
    fun agregaGrupo( nombre: String, inicio: String, fin: String) {

        val cursor = bdd.rawQuery("SELECT * FROM groups WHERE name = '$nombre'", null)

        if (cursor.count == 0) {

            val datos = ContentValues()
            datos.put("name", nombre)
            datos.put("start_date", inicio)
            datos.put("end_date", fin)

            bdd.insert("groups", null, datos)
        }
    }

    //agrega datos a la tabla albums
    fun agregaAlbum(ruta: String, nombre: String, año: Int) {

        val cursor = bdd.rawQuery("SELECT * FROM albums WHERE path = '$ruta'", null)

        if (cursor.count == 0) {

            val datos = ContentValues()
            datos.put("path", ruta)
            datos.put("name", nombre)
            datos.put("year", año)

            bdd.insert("albums", null, datos)
        }
    }
    //agrega datos a la tabla rolas
    fun agregaRola(performer: Int, album: Int, ruta: String, titulo: String, track: Int, año: Int, genero: String ) {

        val cursor = bdd.rawQuery("SELECT * FROM rolas WHERE path = '$ruta'", null)

        if (cursor.count == 0) {

            val datos = ContentValues()
            datos.put("id_performer", performer)
            datos.put("id_album", album)
            datos.put("path", ruta)
            datos.put("tittle", titulo)
            datos.put("track", track)
            datos.put("year", año)
            datos.put("genre", genero)

            bdd.insert("rolas", null, datos)
        }
    }

    //agrega datos a la tabla in_group
    fun agregaEnGrupo(persona: Int, grupo: Int) {

        val cursor = bdd.rawQuery("SELECT * FROM in_group WHERE id_person = '$persona' AND id_group = '$grupo'", null)

        if (cursor.count == 0) {

            val datos = ContentValues()
            datos.put("id_person", persona)
            datos.put("id_group", grupo)

            bdd.insert("in_group", null, datos)
        }
    }



}