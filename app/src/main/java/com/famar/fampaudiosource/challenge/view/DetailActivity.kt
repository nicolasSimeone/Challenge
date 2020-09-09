package com.famar.fampaudiosource.challenge.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.famar.fampaudiosource.challenge.R
import com.famar.fampaudiosource.challenge.model.User
import com.famar.fampaudiosource.challenge.model.UserSaved
import com.famar.fampaudiosource.challenge.utils.formatName
import com.famar.fampaudiosource.challenge.viewModel.DetailViewModel
import kotlinx.android.synthetic.main.activity_detail.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailActivity : AppCompatActivity() {

    private val viewModel by viewModel<DetailViewModel>()
    var data:User = User()
    var saveData:UserSaved = UserSaved("","","","")
    var nameC:String? = ""
    var phoneC:String? = ""
    var emailC:String? = ""
    var pictureC:String? =""
    var isFavorite:Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)

        isFavorite = intent.getBooleanExtra("isFavorite", false)

        if(isFavorite)
        {
            saveData = intent.getParcelableExtra("valuesSaved")!!

            saveData.name.let {
                name.text = it
                nameC = it
            }

            saveData.email.let {
                email.text = it
            }

            saveData.phone.let{
                phone.text = it
                phoneC = it
            }

            saveData.picture.let {
                Glide.with(this)
                    .load(it)
                    .into(imageDetail)
            }
        }
        else{
            data = intent.getParcelableExtra("values")!!

            data.name.let {
                name.text = formatName(it)
                nameC = formatName(it)
            }

            data.email?.let {
                email.text = it
                emailC = it
            }

            data.phone?.let {
                phone.text = it
                phoneC = it
            }

            data.picture.medium.let {
                pictureC = it
                Glide.with(this)
                    .load(it)
                    .into(imageDetail)
            }
        }





        addContact.setOnClickListener {
            val intent = Intent(ContactsContract.Intents.Insert.ACTION)
            intent.setType(ContactsContract.RawContacts.CONTENT_TYPE)
            intent.putExtra(ContactsContract.Intents.Insert.NAME, nameC)
            intent.putExtra(ContactsContract.Intents.Insert.PHONE, phoneC)
            startActivityForResult(intent, 1)
        }

    }

    override fun onActivityResult(
        requestCode: Int,
        resultCode: Int,
        intent: Intent?
    ) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                Toast.makeText(this, "Added Contact", Toast.LENGTH_SHORT).show()
            }
            if (resultCode == Activity.RESULT_CANCELED) {
                Toast.makeText(this, "Cancelled Added Contact", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        if(isFavorite) menu!!.findItem(R.id.action_star).icon = ContextCompat.getDrawable(this, R.drawable.favorite_true)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.action_star ->{
                item.icon = ContextCompat.getDrawable(this, R.drawable.favorite_true)
                viewModel.saveUser(emailC, pictureC, nameC, phoneC)
                Toast.makeText(this, "You Marked the user as Favorite", Toast.LENGTH_SHORT).show()
            }
            android.R.id.home -> {
                intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
        }
        return true
    }
}