package com.example.parser.ui.main

//import org.w3c.dom.*
import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.Data
import androidx.work.WorkerParameters
import org.jsoup.Jsoup

class ParserWork(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {

        // Do the work here--in this case, upload the images.
        //uploadImages()
        //val data = parseData()
        //val workData = Data()
        // Indicate whether the work finished successfully with the Result
        return Result.success(Data.Builder().putString("test", "sdfs").build())
    }

    private suspend fun parseData(): String {
        return ""
        val url =
            "https://www.avito.ru/nizhniy_novgorod/transport?q=%D0%BF%D0%B5%D1%80%D0%B5%D0%B4%D0%BD%D1%8F%D1%8F+%D0%BB%D0%B5%D0%B2%D0%B0%D1%8F+%D1%84%D0%B0%D1%80%D0%B0+%D0%BD%D0%B0+kia+rio+3&s=104"
        return try {
            val doc = Jsoup.connect(url).get()
            /*val items = doc.select("[class^=items-items]").first()
            items.children().forEach { element ->
                val isAd = element.classNames().any { it.contains("ads") }
                if (!isAd){
                    val img = element.selectFirst("a").selectFirst("img").attr("src")
                    val title =
                        element.select("[class^=iva-item-title]").first().children().attr("title")
                    val price = element.select("[class^=iva-item-price]").first().select("[class^=price-text]").text()
                    val geo = element.select("[class^=geo-geo]").text()
                    val date = element.select("[class^=date-root]").text()
                    println()
                }

            }*/
            doc.title()
        } catch (ex: Exception) {
            ex.message ?: ""
        }
        /*HttpClient().use { client ->
            return try{
                val data = client.get<String>(url)

                data
            } catch(ex: Exception){
                ex.message ?:""
            }
            //Log.i("ktor data", data)
        }*/
    }
}