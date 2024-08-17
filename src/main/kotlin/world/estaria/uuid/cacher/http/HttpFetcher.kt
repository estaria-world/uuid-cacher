package world.estaria.uuid.cacher.http

import java.net.HttpURLConnection
import java.net.URI

/**
 * @author Niklas Nieberler
 */

object HttpFetcher {

    /**
     * Gets the context of a url
     * @param urlString the url
     * @return context of this url
     */
    fun getStringFromUrl(urlString: String): String? {
        val url = URI(urlString).toURL()
        val connection = url.openConnection() as HttpURLConnection
        connection.requestMethod = "GET"
        connection.connect()

        if (connection.responseCode != HttpURLConnection.HTTP_OK)
            return null

        val inputStream = connection.inputStream
        val content = inputStream.bufferedReader().use { it.readText() }
        connection.disconnect()
        return content
    }

}