package mx.unam.fciencias.reproductor

import android.Manifest
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class MainActivity : AppCompatActivity() {

    private val minero = Minero()
    private val crea = CreaBaseDeDatos(this)



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

        if (crea != null) {
            println("SIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIII" + crea)

            val bdd = crea.getWritableDatabase()

            crea.agregaTipos(bdd)

            val c = bdd.rawQuery("SELECT * FROM types", null);

            if (c != null) {
                while (c.moveToNext()) {
                    println(c.getString(0))
                    println(c.getString(1))

                }
            }
            c.close()
        } else {
            println("Noooooooooooooooooooooooooooooooooooooooooooooooooo")
        }


    }


    fun recibeEntrada(v: View) {
        val entrada = findViewById<View>(R.id.campo_texto) as EditText
        val ruta = entrada.getText().toString()
        val archivos = minero.buscaMp3(ruta)
        archivos.forEach {
            println(minero.leeArtista(ruta + "/" + it))
            println(minero.leeNombre(ruta + "/" +it))
            println(minero.leeAlbum(ruta + "/" +it))
            println(minero.leeFecha(ruta + "/" +it))
            println(minero.leeGenero(ruta + "/" +it))
            println(minero.leeNumero(ruta + "/" + it))
        }
    }

}

