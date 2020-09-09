package com.famar.fampaudiosource.challenge.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.famar.fampaudiosource.challenge.R
import com.famar.fampaudiosource.challenge.model.UserSaved
import kotlinx.android.synthetic.main.layout_listitem.view.*

class UserSavedItemAdapter(private val context: Context, private var userList: MutableList<UserSaved>): RecyclerView.Adapter<UserSavedItemAdapter.ViewHolder>() {

    var onItemClick:((UserSaved) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.layout_listitem, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.txtName.text = userList[position].name

        Glide.with(this.context)
            .load(userList[position].picture)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun refreshList(userList: List<UserSaved>) {
        this.userList.clear()
        this.userList = userList.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder (itemView:View):RecyclerView.ViewHolder(itemView){
        var txtName: TextView
        var imageView: ImageView

        init {
            txtName = itemView.text
            imageView = itemView.image
            itemView.setOnClickListener {
                onItemClick?.invoke(userList[adapterPosition])
            }
        }

    }
}