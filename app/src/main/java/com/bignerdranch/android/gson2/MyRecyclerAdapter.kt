package com.bignerdranch.android.gson2

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Context.CLIPBOARD_SERVICE
import android.content.Context.CONTEXT_INCLUDE_CODE
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.core.content.getSystemService
import androidx.recyclerview.widget.RecyclerView
import com.bignerdranch.android.gson2.CellClickListener
import com.bignerdranch.android.gson2.R
import com.bumptech.glide.Glide

class RecycleAdapter(private val context: Context, private val links: Array<String>, private val clickListener: CellClickListener) : RecyclerView.Adapter<RecycleAdapter.ViewHolder>() {
    class ViewHolder (itemView: View) : RecyclerView.ViewHolder (itemView) {
        val pic : ImageView = itemView.findViewById(R.id.imageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.r_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return links.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data = links[position]

        Glide.with(context).load(data).into(holder.pic)

        holder.itemView.setOnClickListener {
            clickListener.onCellClickListener(data)
        }
    }
}