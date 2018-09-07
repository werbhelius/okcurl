package com.werb.okcurlsample

import okhttp3.RequestBody
import okhttp3.MediaType
import okhttp3.Request

/**
 * Created by wanbo on 2018/9/6.
 */
object RequestFactory {

    const val TYPE_GET = "type_get"
    const val TYPE_POST = "type_post"
    const val TYPE_DELETE = "type_delete"

    private val NEW_REPO_BODY = "{" +
        "  \"name\": \"Hello-World\"," +
        "  \"description\": \"This is your first repository\"," +
        "  \"homepage\": \"https://github.com\"," +
        "  \"private\": false," +
        "  \"has_issues\": true," +
        "  \"has_wiki\": true," +
        "  \"has_downloads\": true" +
        "}"

    private fun sampleGetRequest(): Request {
        return Request.Builder()
            .url("https://api.github.com/events")
            .addHeader("Authorization", "token xxxx") // replace your github Personal access tokens
            .build()
    }

    private fun samplePostRequest(): Request {
        return Request.Builder()
            .url("https://api.github.com/user/repos")
            .post(RequestBody.create(MediaType.parse("application/json; charset=utf-8"), NEW_REPO_BODY))
            .addHeader("Authorization", "token xxxx") // replace your github Personal access tokens
            .build()
    }

    private fun sampleDeleteRequest(): Request {
        return Request.Builder()
            .url("https://api.github.com/repos/Werb/Hello-World")
            .delete()
            .addHeader("Authorization", "token xxxx") // replace your github Personal access tokens
            .build()
    }

    fun getRequest(type: String): Request {
        return when (type) {
            TYPE_GET -> sampleGetRequest()
            TYPE_POST -> samplePostRequest()
            TYPE_DELETE -> sampleDeleteRequest()
            else -> throw IllegalArgumentException("Invalid request type")
        }
    }

}