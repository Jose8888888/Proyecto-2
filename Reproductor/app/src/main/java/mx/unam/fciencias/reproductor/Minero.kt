package mx.unam.fciencias.reproductor

import android.media.MediaMetadataRetriever

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

}