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
import com.famar.fampaudiosource.challenge.model.User
import com.famar.fampaudiosource.challenge.model.UserSaved
import com.famar.fampaudiosource.challenge.utils.formatName
import kotlinx.android.synthetic.main.layout_listitem.view.*
import kotlinx.android.synthetic.main.layout_listitem.view.text
import kotlinx.android.synthetic.main.list_users.view.*

class UsersAdapter(private val context: Context, private var userList: MutableList<User>): RecyclerView.Adapter<UsersAdapter.ViewHolder>() {

    var onItemClick:((User) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersAdapter.ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.list_users, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UsersAdapter.ViewHolder, position: Int) {
        holder.txtName.text = formatName(userList[position].name)

        Glide.with(this.context)
            .load(userList[position].picture.large)
            .into(holder.imageView)
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    fun refreshList(userList: List<User>) {
        this.userList.clear()
        this.userList = userList.toMutableList()
        notifyDataSetChanged()
    }

    fun updateList(list:MutableList<User>)
    {
        userList = list
        notifyDataSetChanged()
    }

    inner class ViewHolder (itemView: View):RecyclerView.ViewHolder(itemView){

        var txtName: TextView
        var imageView: ImageView

        init {
            txtName = itemView.text
            imageView = itemView.imageView
            itemView.setOnClickListener {
                onItemClick?.invoke(userList[adapterPosition])
            }
        }

    }
}