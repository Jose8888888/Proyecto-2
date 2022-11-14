package mx.unam.fciencias.reproductor

import android.R
import android.widget.Button

import android.widget.*


import android.Manifest
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.media.MediaPlayer
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.EditText
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat



class Controlador : AppCompatActivity() {


    //imprime un error en la interfaz
    fun enviaError(error : String) {

    }

    //regresa el botón de play
    fun play(): Button {
        return findViewById<View>(mx.unam.fciencias.reproductor.R.id.play) as Button
    }

    //regresa la vista de imagen
    fun imagen(): ImageView {
        return findViewById<View>(mx.unam.fciencias.reproductor.R.id.imagen) as ImageView
    }

    //regresa una lista con las canciones que lee
    fun canciones(context : Context) : MutableList<MediaPlayer> {

        var lista = mutableListOf<MediaPlayer>()

        val minero = Minero()
        val archivos = minero.buscaMp3("storage/emulated/0/Download")
        val mp = MediaPlayer()
        mp.setDataSource("storage/emulated/0/Download/" + archivos[1])
        mp.prepare()

        lista.add(mp)

        return lista

    }



}