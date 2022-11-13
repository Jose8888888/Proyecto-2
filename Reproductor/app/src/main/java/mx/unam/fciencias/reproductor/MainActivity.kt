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
    private val manejador = ManejadorBaseDeDatos(this)



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


        val bdd = manejador.writableDatabase

        manejador.inicializa(bdd)
        manejador.agregaPersona("Java", "Juan", "ayer", "mañana", bdd)

        val cursor = bdd.rawQuery("SELECT * FROM persons", null)

        if(cursor != null && cursor.count > 0)
        {

            if (cursor.moveToFirst())
            {
                do {
                    println(cursor.getString(0))
                    println(cursor.getString(1))
                    println(cursor.getString(2))
                    println(cursor.getString(3))
                    println(cursor.getString(4))


                } while (cursor.moveToNext())
            }
        }


        bdd.close()
        cursor.close()



    }


    fun recibeEntrada(v: View) {
        val entrada = findViewById<View>(R.id.campo_texto) as EditText
        val ruta = entrada.text.toString()
        val archivos = minero.buscaMp3(ruta)
        archivos.forEach {
            println(minero.leeArtista("$ruta/$it"))
            println(minero.leeNombre("$ruta/$it"))
            println(minero.leeAlbum("$ruta/$it"))
            println(minero.leeFecha("$ruta/$it"))
            println(minero.leeGenero("$ruta/$it"))
            println(minero.leeNumero("$ruta/$it"))
        }
    }

}

