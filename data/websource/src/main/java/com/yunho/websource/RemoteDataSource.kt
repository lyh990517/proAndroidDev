package com.yunho.websource

import org.jsoup.Jsoup

class RemoteDataSource {
    fun main() {
        val url = "https://example.com"

        val document = Jsoup.connect(url).get()
    }
}