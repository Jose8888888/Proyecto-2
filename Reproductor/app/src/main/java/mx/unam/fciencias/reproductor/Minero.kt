package mx.unam.fciencias.reproductor

import android.media.MediaMetadataRetriever
import android.util.Log
import java.io.File

var controlador = Controlador()

class Minero {

    private fun lee(ruta: String, etiqueta: Int): String? {
        val mmr: MediaMetadataRetriever = MediaMetadataRetriever()
        mmr.setDataSource(ruta)

        val dato = mmr.extractMetadata(etiqueta)
        if (dato != null) {
            return dato
        } else {
            controlador.error("El archivo no existe")
        }
        return null
    }

    //regresa el artista de la canción que recibe
    fun  leeArtista(ruta: String): String? {
        return lee(ruta, MediaMetadataRetriever.METADATA_KEY_ARTIST)
    }

    //regresa el nombre de la canción que recibe
    fun  leeNombre(ruta: String): String? {
        return lee(ruta, MediaMetadataRetriever.METADATA_KEY_TITLE)
    }

    //regresa el álbum de la canción que recibe
    fun  leeAlbum(ruta: String): String? {
        return lee(ruta, MediaMetadataRetriever.METADATA_KEY_ALBUM)
    }
    //regresa la fecha de la canción que recibe
    fun  leeFecha(ruta: String): String? {
        return lee(ruta, MediaMetadataRetriever.METADATA_KEY_DATE)
    }
    //regresa el género de la canción que recibe
    fun  leeGenero(ruta: String): String? {
        return lee(ruta, MediaMetadataRetriever.METADATA_KEY_GENRE)
    }
    //regresa el número de pista de la canción que recibe
    fun  leeNumero(ruta: String): String? {
        return lee(ruta, MediaMetadataRetriever.METADATA_KEY_DISC_NUMBER)
    }

    //lee los archivos de un directorio y regresa los mp3
    fun buscaMp3(directorio: String): List<String> {
        val dir = File(directorio)
        val files = dir.listFiles()
        val archivos = mutableListOf<String>()
        for (i in files.indices) {

            val indice: Int = files[i].getAbsolutePath().lastIndexOf(".")

            if (indice != -1) {

                val extension: String =
                    files[i].getAbsolutePath().substring(indice)

                if (extension == ".mp3") {
                    archivos.add(files[i].name)
                }
            }
        }
        return archivos
    }
}