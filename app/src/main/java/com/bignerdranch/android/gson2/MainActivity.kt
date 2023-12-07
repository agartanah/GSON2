package com.bignerdranch.android.gson2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.google.gson.Gson
import okhttp3.Call
import okhttp3.Callback
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import timber.log.Timber
import java.io.IOException
import java.sql.Wrapper

interface  CellClickListener {
    fun onCellClickListener (link: String)
}

data class Photo(val id : Number, val owner : String, val secret : String, val server : Number,
                 val farm : Number, val title : String, val isPublic : Boolean, val isFriend : Boolean,
                 val isFamily : Boolean)

data class Photos(val page : Number, val pages : Number, val perpage : Number, val total : Number,
                  val photo : Array<Photo>)

data class MyWrapper(val photos: Photos, val stat : String)

class MainActivity : AppCompatActivity(), CellClickListener {

    private val URL = "https://api.flickr.com/services/rest/?method=flickr.photos.search&api_key=ff49fcd4d4a08aa6aafb6ea3de826464&tags=cat&format=json&nojsoncallback=1"

    private var links : Array<String> = arrayOf()

    private lateinit var recyclerView: RecyclerView

    private val okHttpClient : OkHttpClient = OkHttpClient()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.rView)

        Timber.plant(Timber.DebugTree())

        getJsonServer()
    }

    private fun getJsonServer() {
        val request : Request = Request.Builder().url(URL).build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call, response: Response) {
                val jsonFromServer = response.body?.string()

                parseJson(jsonFromServer)
            }
        })
    }

    private fun parseJson(jsonFromServer: String?) {
        val result = Gson().fromJson(jsonFromServer, MyWrapper::class.java)

        for (i in 0 .. result.photos.photo.size - 1) {
            // https://farm${Photo.farm}.staticflickr.com/${Photo.server}/${Photo.id}_${Photo.secret}_z.jpg

            links += ("https://farm${result.photos.photo[i].farm}.staticflickr.com/${result.photos.photo[i].server}" + "/${result.photos.photo[i].id}_${result.photos.photo[i].secret}_z.jpg")

            Timber.d("Photo_Link", links[i].toString())
        }

        runOnUiThread() {
            recyclerView.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

            recyclerView.adapter = RecycleAdapter(this, links, this)
        }
    }

    override fun onCellClickListener(link: String) {
        val intent = Intent(this, PicActivity::class.java)
        intent.putExtra(R.string.key_link.toString(), link)
        startActivity(intent)
    }
}