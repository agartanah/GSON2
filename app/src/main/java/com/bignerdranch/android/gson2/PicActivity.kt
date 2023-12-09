package com.bignerdranch.android.gson2

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import timber.log.Timber


class PicActivity : AppCompatActivity() {
    private var link: String? = null
    private lateinit var pic: ImageView
    private lateinit var toolBar: Toolbar
    private lateinit var coordinatorLayout: CoordinatorLayout
    private var launcher: ActivityResultLauncher<Intent>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pic)

        toolBar = findViewById(R.id.toolbar)
        setSupportActionBar(toolBar)

        link = intent.getStringExtra(R.string.key_link.toString())

        pic = findViewById(R.id.imageView)

        Glide.with(this).load(link).into(pic)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.heart_item) {
            val intent = Intent()
            intent.putExtra("link", link)
            setResult(RESULT_OK, intent)
            finish()

            return true
        }

        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar_menu, menu)

        return true
    }
}