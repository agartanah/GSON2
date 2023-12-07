package com.bignerdranch.android.gson2

import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide

class PicActivity : AppCompatActivity() {
    private lateinit var pic: ImageView
    private lateinit var toolBar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pic)

        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)

        val link = intent.getStringExtra(R.string.key_link.toString())

        pic = findViewById(R.id.imageView)

        Glide.with(this).load(link).into(pic)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.heart_item) {
            Toast.makeText(
                this,
                "Добавлено в избранное",
                Toast.LENGTH_LONG
            ).show()
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }
}