package com.werb.okcurlsample

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Intent

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        request.setOnClickListener {
            sendRequest(RequestFactory.TYPE_GET)
        }
    }

    private fun sendRequest(type: String) {
        val intent = Intent(this, RequestService::class.java)
        intent.putExtra(RequestService.REQUEST_TYPE, type)
        startService(intent)
    }
}
