# okcurl
> curl log for okhttp!

add `OkCurl` Interceptor for okhttp

```kotlin
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
```