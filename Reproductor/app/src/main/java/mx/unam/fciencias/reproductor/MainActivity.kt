package mx.unam.fciencias.reproductor

import android.Manifest
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {

    private val minero = Minero()
    private val creador = CreadorBaseDeDatos(this)
    private var reproductor = Reproductor()
    private val controlador = Controlador()

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (!ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            )
        ) {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),
                1
            )
        }
        val bdd: SQLiteDatabase = creador.writableDatabase
        val manejador = ManejadorBaseDeDatos(bdd)
        manejador.inicializa()

        val play = findViewById<View>(mx.unam.fciencias.reproductor.R.id.play) as Button
        val mp = MediaPlayer.create(this, mx.unam.fciencias.reproductor.R.raw.tea)
        val imagen = findViewById<View>(mx.unam.fciencias.reproductor.R.id.imagen) as ImageView
        reproductor = Reproductor(play, mp, imagen, this)


    }

    //Guarda el archivo que recibe como entrada en la base de datos
    fun recibeEntrada(v: View) {
        val entrada = findViewById<View>(R.id.campo_texto) as EditText
        val ruta = entrada.text.toString()
        val archivos = minero.buscaMp3(ruta)

        val bdd: SQLiteDatabase = creador.writableDatabase
        val manejador = ManejadorBaseDeDatos(bdd)

        archivos.forEach {

            val artista = minero.leeArtista("$ruta/$it")
            if (artista != null) {
                manejador.agregaPerformer(2, artista)
            }

            val album = minero.leeAlbum("$ruta/$it")
            if (album != null) {
                manejador.agregaAlbum("$ruta/$it", album, 0)
            }


        }
    }

    //reproduce o pausa la canci??n
    fun reproducePausa(view : View) {
        reproductor.reproducePausa(this)
    }

}