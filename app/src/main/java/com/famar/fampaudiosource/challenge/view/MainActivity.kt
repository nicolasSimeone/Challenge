package com.famar.fampaudiosource.challenge.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.widget.SearchView
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.famar.fampaudiosource.challenge.R
import com.famar.fampaudiosource.challenge.model.User
import com.famar.fampaudiosource.challenge.model.UserSaved
import com.famar.fampaudiosource.challenge.utils.formatName
import com.famar.fampaudiosource.challenge.viewModel.HomeViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class MainActivity : AppCompatActivity() {


    private val viewModel by viewModel<HomeViewModel>()
    private lateinit var adapterHome: UserSavedItemAdapter
    private lateinit var adapterUsers: UsersAdapter
    private var userListSaved : MutableList<UserSaved> = arrayListOf()
    private var usersList : MutableList<User> = arrayListOf()
    var displayList:MutableList<User> = arrayListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapterHome = UserSavedItemAdapter(this, userListSaved)
        adapterUsers = UsersAdapter(this, usersList)

        findViewById<RecyclerView>(R.id.savedList).apply {
            layoutManager = LinearLayoutManager(context)
            layoutManager = LinearLayoutManager (context, LinearLayoutManager.HORIZONTAL, false)
            adapter = adapterHome
            adapterHome.onItemClick = {
                userSaved ->
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("valuesSaved", userSaved)
                intent.putExtra("isFavorite", true)
                startActivity(intent)

            }

        }

        findViewById<RecyclerView>(R.id.usersAll).apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = adapterUsers
            adapterUsers.onItemClick = {
                user ->
                val intent = Intent(this@MainActivity, DetailActivity::class.java)
                intent.putExtra("values", user)
                intent.putExtra("isFavorite", false)
                startActivity(intent)
            }
        }



        viewModel.userSaved.observe(this, Observer {
            val userSavedlist = it
            adapterHome.refreshList(userSavedlist)
        })

        viewModel.user.observe(this, Observer {
            val users = it.results
            displayList = it.results
            adapterUsers.refreshList(users)
        })

        viewModel.getAllUsers()
        viewModel.getSavedUsers()


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {

        menuInflater.inflate(R.menu.menu, menu)
        val menuItem = menu!!.findItem(R.id.search)
        val favoriteItem = menu!!.findItem(R.id.action_star)
        favoriteItem.setVisible(false)
        if(menuItem != null)
        {
            val searchView = menuItem.actionView as SearchView

            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
                override fun onQueryTextSubmit(p0: String?): Boolean {
                    return true
                }

                override fun onQueryTextChange(p0: String?): Boolean {
                    if(p0!!.isNotEmpty()){
                        usersList.clear()
                        val search = p0.toLowerCase(Locale.getDefault())
                        displayList.forEach {
                            if (it.name.first!!.toLowerCase(Locale.getDefault()).contains(search))usersList.add(it)
                        }
                        adapterUsers.updateList(usersList)
                    }
                    else {
                        usersList.clear()
                        usersList.addAll(displayList)
                        adapterUsers.updateList(usersList)
                    }
                    return true
                }

            })
        }
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return super.onOptionsItemSelected(item)
    }

}