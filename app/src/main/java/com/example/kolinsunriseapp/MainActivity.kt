package com.example.kolinsunriseapp

import android.annotation.SuppressLint
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchData()
    }

    protected fun fetchData() {

        var url = "https://api.github.com/users/saiashish9"
        MyAsyncTask().execute(url)

    }

    inner class MyAsyncTask() : AsyncTask<String, String, String>() {

        override fun doInBackground(vararg params: String?): String {
            try {
                val url = URL(params[0])
                val urlConnect = url.openConnection() as HttpURLConnection
                urlConnect.connectTimeout = 7000
                var inString = ConvertStreamToString(urlConnect.inputStream)
                publishProgress(inString)

            } catch (e: Exception) {
            }

            return "success";


        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
        }

        override fun onProgressUpdate(vararg values: String?) {
            try {
                var json = JSONObject(values[0])
                val username = json.getString("login")
//                getJSONObject
                tv.text = username
            } catch (ex: java.lang.Exception) {
            }

        }
    }

    fun ConvertStreamToString(inputStream: InputStream): String {
        val bufferedReader = BufferedReader(InputStreamReader(inputStream))
        var line: String = ""
        var AllString: String = ""

        try {
            do {
                line = bufferedReader.readLine()
                if (line != null) {
                    AllString += line
                }
            } while (line != null)
            inputStream.close()
        } catch (ex: Exception) {
        }

        return AllString
    }

}