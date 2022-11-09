package mx.unam.fciencias.reproductor

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.util.Log
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import java.io.File
import java.nio.file.Files
import java.nio.file.Paths


class MainActivity : AppCompatActivity() {

    val minero = Minero()


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

        val path = "storage/emulated/0/Download"
        Log.d("Files", "Path: $path")


        val directory = File(path)
        val files = directory.listFiles()
        Log.d("Files", "Size: " + files.size)
        for (i in files.indices) {

            val indice: Int = files[i].getAbsolutePath().lastIndexOf(".")

            if (indice != -1) {

                val extension: String =
                    files[i].getAbsolutePath().substring(indice)

                if (extension == ".mp3") {
                    Log.d("Files", "FileName:" + files[i].name)

                }
            }
        }

    }


    fun recibeEntrada(v: View) {
        val entrada = findViewById<View>(R.id.campo_texto) as EditText
        println(minero.leeArtista(entrada.getText().toString()))
        println(minero.leeNombre(entrada.getText().toString()))
        println(minero.leeAlbum(entrada.getText().toString()))
        println(minero.leeFecha(entrada.getText().toString()))
        println(minero.leeGenero(entrada.getText().toString()))
        println(minero.leeNumero(entrada.getText().toString()))
    }

}

