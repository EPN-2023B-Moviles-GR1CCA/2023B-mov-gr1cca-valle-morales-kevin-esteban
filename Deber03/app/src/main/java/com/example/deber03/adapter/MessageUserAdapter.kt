package com.example.deber03.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deber03.R
import com.example.deber03.model.User

class MessageUserAdapter(private val aCtx: Context, private val list: List<User>) :
    RecyclerView.Adapter<MessageUserAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view =
            LayoutInflater.from(aCtx).inflate(R.layout.card_useritem, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.name.text = list[position].username
        Glide.with(aCtx).load(list[position].imageUrl).into(holder.userImage)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var name: TextView = itemView.findViewById(R.id.item_text)
        var userImage: ImageView = itemView.findViewById(R.id.item_image)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun setHasStableIds(hasStableIds: Boolean) {
        super.setHasStableIds(hasStableIds)
    }
}
