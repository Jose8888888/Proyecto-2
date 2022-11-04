package mx.unam.fciencias.reproductor

import android.media.MediaMetadataRetriever
import android.os.Bundle

var controlador = Controlador()

class Lector {

    //regresa el artista del archivo que recibe
    fun  leeArtista(ruta: String): String? {

        val mmr: MediaMetadataRetriever = MediaMetadataRetriever()
        mmr.setDataSource(ruta)

        val artista = mmr.extractMetadata(MediaMetadataRetriever.METADATA_KEY_ARTIST)
        if (artista != null) {
            return artista
        } else {
            controlador.Error("El archivo no existe")
        }
        return null
    }

}