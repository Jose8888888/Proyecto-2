package mx.unam.fciencias.reproductor

import android.media.MediaPlayer
import android.widget.Button
import android.widget.ImageView
import android.Manifest
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat


class Reproductor {
    val controlador = Controlador()
    val play : Button?
    val mp : MediaPlayer?
    val imagen : ImageView?
    var i = 0
    var canciones = mutableListOf<MediaPlayer>()

    constructor() {
        play = null
        mp = null
        imagen = null
    }

    constructor(play : Button, mp : MediaPlayer, imagen : ImageView, context : Context) {
        this.play = play
        this.mp = mp
        this.imagen = imagen
        canciones = controlador.canciones(context)
    }

    //reproduce o pausa la canción
    fun reproducePausa(context : Context) {
        if (canciones[i].isPlaying) {
            canciones[i].pause()
            play?.setBackgroundResource(mx.unam.fciencias.reproductor.R.drawable.reproducir)
            Toast.makeText(context, "Canción pausada", Toast.LENGTH_SHORT).show()

        } else {
            canciones[i].start()
            play?.setBackgroundResource(mx.unam.fciencias.reproductor.R.drawable.pausa)
            Toast.makeText(context, "Reproduciendo canción", Toast.LENGTH_SHORT).show()
        }
    }
}