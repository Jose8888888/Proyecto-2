package mx.unam.fciencias.reproductor

import android.Manifest
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.os.Environment
import android.view.View
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import java.io.File

import androidx.core.app.ActivityCompat
import android.widget.Toast
import android.util.Log


class MainActivity : AppCompatActivity() {

    val minero =  Minero()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }

  }



    fun recibeEntrada(v: View) {
        val entrada = findViewById<View>(R.id.campo_texto) as EditText
        println(minero.leeArtista(entrada.getText().toString()))
        println(minero.leeNombre(entrada.getText().toString()))
    }

    //val ruta = "storage/emulated/0/Music/Samsung/Over_the_Horizon.mp3"


}