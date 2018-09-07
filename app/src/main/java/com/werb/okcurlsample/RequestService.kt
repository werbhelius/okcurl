package com.werb.okcurlsample

import android.app.IntentService
import android.content.Intent
import com.werb.okcurl.OkCurl
import okhttp3.OkHttpClient
import java.io.IOException


/**
 * Created by wanbo on 2018/9/6.
 */
class RequestService : IntentService("RequestService") {

    companion object {
        const val REQUEST_TYPE = "request_type"
    }

    override fun onHandleIntent(intent: Intent?) {

        intent?.apply {
            val curlInterceptor = OkCurl("RequestService")
            val client = OkHttpClient.Builder()
                .addInterceptor(curlInterceptor)
                .build()
            val requestType = intent.getStringExtra(REQUEST_TYPE)
            val request = RequestFactory.getRequest(requestType)
            try {
                client.newCall(request).execute()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }

    }
}