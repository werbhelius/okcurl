package com.werb.okcurl

import android.text.TextUtils.join
import com.werb.okcurl.OkCurlString.CONTENT_TYPE
import com.werb.okcurl.OkCurlString.FORMAT_BODY
import com.werb.okcurl.OkCurlString.FORMAT_HEADER
import com.werb.okcurl.OkCurlString.FORMAT_METHOD
import com.werb.okcurl.OkCurlString.FORMAT_URL
import okhttp3.MediaType
import okhttp3.Request
import okhttp3.internal.http2.Header
import okhttp3.RequestBody
import okio.Buffer
import java.io.IOException
import java.nio.charset.Charset
import java.nio.charset.Charset.defaultCharset



/**
 * deal request and work with curl
 * Created by wanbo on 2018/9/6.
 */
class OkCurlWorker(request: Request) {

    private var url: String = request.url().toString()
    private var method: String = request.method()
    private var contentType: String = request.body()?.contentType().toString()
    private val headers = arrayListOf<Header>()
    private val body: String

    init {
        // header
        val requestHeaders = request.headers()
        (0 until requestHeaders.size()).forEach {
            headers.add(Header(requestHeaders.name(it), requestHeaders.value(it)))
        }

        // body
        body = getBodyAsString(request.body())
    }

    private fun getBodyAsString(body: RequestBody?): String {
        return try {
            val sink = Buffer()
            val mediaType = body?.contentType()
            val charset = getCharset(mediaType)
            body?.writeTo(sink)
            sink.readString(charset)
        } catch (e: IOException) {
            "Error while reading body: " + e.toString()
        }

    }

    private fun getCharset(mediaType: MediaType?): Charset {
        mediaType?.let {
            return it.charset(defaultCharset()) ?: Charset.defaultCharset()
        } ?: return Charset.defaultCharset()
    }

    fun work(): String {
        val parts = arrayListOf<String>()
        parts.add("curl")
        parts.add(String.format(FORMAT_METHOD, method.toUpperCase()))

        for (header in headers) {
            val headerPart = String.format(FORMAT_HEADER, header.name.utf8(), header.value.utf8())
            parts.add(headerPart)
        }

        if (contentType.isNotEmpty() && contentType != "null") {
            parts.add(String.format(FORMAT_HEADER, CONTENT_TYPE, contentType))
        }

        if (body.isNotEmpty()) {
            parts.add(String.format(FORMAT_BODY, body))
        }

        parts.add(String.format(FORMAT_URL, url))

        return join(" ", parts)
    }

}