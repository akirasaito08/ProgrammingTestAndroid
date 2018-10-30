package com.example.akira08.programmingtest

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import android.widget.Toast
import com.example.akira08.programmingtest.adapter.MainAdapter
import com.example.akira08.programmingtest.api.`interface`.ApiInterface
import com.example.akira08.programmingtest.api.model.ContactModel
import com.example.akira08.programmingtest.api.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AddDataActivity : AppCompatActivity() {

    lateinit var firstNameText:TextView
    lateinit var lastNameText:TextView
    lateinit var ageText:TextView
    lateinit var photoText:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_data)

        supportActionBar?.setTitle("Add Data")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        firstNameText = findViewById(R.id.firstNameText)
        lastNameText = findViewById(R.id.lastNameText)
        ageText = findViewById(R.id.ageText)
        photoText = findViewById(R.id.photoText)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                finish()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun addData(view:View){

        var firstName = firstNameText.text.toString()
        var lastName = lastNameText.text.toString()
        var age = ageText.text.toString().toInt()
        var photo = photoText.text.toString()

        val service = ApiService.retrofitInstance.create(ApiInterface::class.java)

        val call = service.savePost(firstName, lastName, age, photo)
        call.enqueue(object : Callback<ContactModel> {
            override fun onResponse(call: Call<ContactModel>, response: Response<ContactModel>) {

                    Toast.makeText(this@AddDataActivity, "Data Added", Toast.LENGTH_SHORT).show()

            }

            override fun onFailure(call: Call<ContactModel>, t: Throwable) {
                Toast.makeText(this@AddDataActivity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
