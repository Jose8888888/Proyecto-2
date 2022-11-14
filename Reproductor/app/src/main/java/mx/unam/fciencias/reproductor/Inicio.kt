package mx.unam.fciencias.reproductor

import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText

//Actividad que aparece al iniciar la aplicación para poner el archivo donde se encuentran las canciones
class Inicio : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inicio)
    }

    //Recibe la ruta del directorio y la guarda en la actividad principal
    fun recibeDirectorio(vista : View) {
        val entrada = findViewById<View>(R.id.texto) as EditText
        val ruta = entrada.text.toString()
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}