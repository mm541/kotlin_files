package com.example.top10downloader
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.top10downloader.databinding.ActivityMainBinding
import java.net.URL

private const val TAG = "MainActivity: "
class FeedEntry {
    var name: String = ""
    var artist:String = ""
    var releaseDate:String = ""
    var summary:String = ""
    var imageUrl:String = ""

    override fun toString(): String {
        return """
            name = $name
            artist = $artist
            releaseDate = $releaseDate
            imageUrl = $imageUrl
        """.trimIndent()
    }
}
class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d(TAG,"onCreate: called")
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_main)

        val downloadData = DownloadData()
        downloadData.execute("http://ax.itunes.apple.com/WebObjects/MZStoreServices.woa/ws/RSS/topfreeapplications/limit=10/xml")

    }

    companion object {
        private class DownloadData : AsyncTask<String, Void, String>() {
            private val tag = "DownloadData: "
            override fun doInBackground(vararg url: String?): String {
               Log.d(tag,"doInBackground: called with starting ${url[0]}")
                val rssFeed = xmlDownload(url[0])
                if(rssFeed.isEmpty()) {
                    Log.e(tag,"doInBackground: downloading error")
                }
                return rssFeed
            }

            override fun onPostExecute(result: String) {
                super.onPostExecute(result)
//                Log.d(tag,"ibPostExecute: called with parameter $result")
                val parseApplication = ParseApplication()
                parseApplication.parse(result)
            }

            fun xmlDownload(urlPath:String?): String {
//                val rssFeed = StringBuilder()
//                try {
//                    val url = URL(urlPath)
//                    val connection:HttpURLConnection = url.openConnection() as HttpURLConnection
//                    val response = connection.responseCode
//                    Log.d(tag,"downloadXML: The response is code was $response")
//
////                    val reader = BufferedReader(InputStreamReader(connection.inputStream))
////                    val inputBuffer = CharArray(500)
////                    var charsRead = 0
////                    while (charsRead >= 0) {
////                        charsRead = reader.read(inputBuffer)
////                        if(charsRead > 0) {
////                            rssFeed.append(String(inputBuffer,0,charsRead))
////                        }
////                    } reader.close()
//
//                    connection.inputStream.buffered().reader().use { rssFeed.append(it.readText()) }
//                    Log.d(tag, "result: ${rssFeed.length}")
//                    return rssFeed.toString()
//                }
////                catch (e: MalformedURLException) {
////                    Log.e(tag,"downloadXML: Invalid url ${e.message}")
////                }catch (e: IOException) {
////                    Log.e(tag,"downloadXML: IO exception : ${e.message}")
////                }catch (e: SecurityException) {
////                    Log.e(tag,"downloadXML: Security Exception. needs permission? ${e.message}")
////                } catch (e: Exception) {
////                    Log.e(tag,"downloadXMl, Unknown Exception ${e.message}")
////                }
//                catch (e: Exception) {
//                    val errorMessage: String = when(e) {
//                        is MalformedURLException -> "downloadXML: Invalid URL ${e.message}"
//                        is IOException -> "downloadXML: IOException while reading: ${e.message}"
//                        is SecurityException -> {
//                            e.printStackTrace()
//                            "downloadXML: Security Exception. Needs permission? ${e.message}"
//                        }
//                        else -> "downloadXML: Unknown Error: {e.message}"
//                    }
//                    Log.e(tag,errorMessage)
//                }
//                return ""
                return URL(urlPath).readText()
            }
        }
    }
}