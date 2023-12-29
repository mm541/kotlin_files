package com.example.top10downloader

import android.util.Log
import org.xmlpull.v1.XmlPullParser
import org.xmlpull.v1.XmlPullParserFactory

class ParseApplication {
    private val tag = "Parse Application"
    val applications = ArrayList<FeedEntry>()

    // add a feature to check if image has height resolution '53' then only add that to current FeedEntry

    fun parse(xmlData:String):Boolean {
        Log.d(tag,"inside parse function")
        var status = true
        var inEntry = false
        var textValue = ""

        try {
            val factory = XmlPullParserFactory.newInstance()
            factory.isNamespaceAware = true
            val xpp = factory.newPullParser()
            xpp.setInput(xmlData.reader())
            var eventType = xpp.eventType
            var currentEntry = FeedEntry()
            while(eventType != XmlPullParser.END_DOCUMENT) {
                val tagName = xpp?.name?.lowercase()
                when(eventType) {
                    XmlPullParser.START_TAG -> {
                        Log.d(tag, "start tag $tagName")
                        if(tagName == "entry") {
                            inEntry = true
                        }
                    }

                    XmlPullParser.TEXT -> textValue = xpp.text

                    XmlPullParser.END_TAG -> {
                        if (inEntry) {
                            when (tagName) {
                                "entry" -> {
                                    applications.add(currentEntry)
                                    currentEntry = FeedEntry()
                                }

                                "name" -> currentEntry.name = textValue
                                "artist" -> currentEntry.artist = textValue
                                "releasedate" -> currentEntry.releaseDate = textValue
                                "summary" -> currentEntry.summary = textValue
                                "image" -> currentEntry.imageUrl = textValue
                            }
                       }
                    }
                }
                eventType = xpp.next()
            }
            for(app in applications) {
                Log.d(tag,"***********************************")
                Log.d(tag,app.toString())
            }
        }catch (e: Exception) {
            e.printStackTrace()
            status = false
        }
        return status
    }
}