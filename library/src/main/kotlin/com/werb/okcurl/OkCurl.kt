package com.werb.okcurl

import android.util.Log
import okhttp3.Interceptor
import okhttp3.Response

/**
 * Created by wanbo on 2018/9/5.
 */
class OkCurl(private val tag: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        Log.d(tag, OkCurlWorker(request).work())
        return chain.proceed(request)
    }

}