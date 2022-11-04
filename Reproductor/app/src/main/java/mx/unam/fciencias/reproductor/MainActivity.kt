package mx.unam.fciencias.reproductor

import android.Manifest
import android.media.MediaMetadataRetriever
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import java.io.File

import androidx.core.app.ActivityCompat
import android.widget.Toast





class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(!ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.READ_EXTERNAL_STORAGE)) {
            ActivityCompat.requestPermissions(this@MainActivity, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE), 1)
        }

        val lector =  Lector()
        println(lector.leeArtista("storage/emulated/0/Music/Samsung/Over_the_Horizon.mp3"))


    }



}