package com.example.akira08.programmingtest

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.akira08.programmingtest.api.`interface`.ApiInterface
import com.example.akira08.programmingtest.api.model.ContactDeleteModel
import com.example.akira08.programmingtest.api.model.ContactModel
import com.example.akira08.programmingtest.api.model.ContactOneModel
import com.example.akira08.programmingtest.api.service.ApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EditDataActivity : AppCompatActivity() {

    lateinit var editFirstNameText:EditText
    lateinit var editLastNameText:EditText
    lateinit var editAgeText:EditText
    lateinit var editPhotoText:EditText
    lateinit var id:String
    lateinit var editBtn:Button
    lateinit var deleteBtn:Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_data)

        supportActionBar?.setTitle("Edit Data")
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        editFirstNameText = findViewById(R.id.editFirstNameText)
        editLastNameText = findViewById(R.id.editLastNameText)
        editAgeText = findViewById(R.id.editAgeText)
        editPhotoText = findViewById(R.id.editPhotoText)

        deleteBtn = findViewById(R.id.deleteDataBtn)
        deleteBtn.setOnClickListener {
            val service = ApiService.retrofitInstance.create(ApiInterface::class.java)

            val call = service.deleteContact(id)
            call.enqueue(object : Callback<ContactDeleteModel> {
                override fun onResponse(call: Call<ContactDeleteModel>, response: Response<ContactDeleteModel>) {

                    Toast.makeText(this@EditDataActivity, ""+response.body()?.message, Toast.LENGTH_SHORT).show()


                }

                override fun onFailure(call: Call<ContactDeleteModel>, t: Throwable) {
                    Toast.makeText(this@EditDataActivity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
                }
            })
        }

        editBtn = findViewById(R.id.editDataBtn)
        editBtn.setOnClickListener {
            var firstName = editFirstNameText.text.toString()
            var lastName = editLastNameText.text.toString()
            var age = editAgeText.text.toString().toInt()
            var photo = editPhotoText.text.toString()

            val service = ApiService.retrofitInstance.create(ApiInterface::class.java)

            val call = service.editContact(id, firstName, lastName, age, photo)
            call.enqueue(object : Callback<ContactOneModel> {
                override fun onResponse(call: Call<ContactOneModel>, response: Response<ContactOneModel>) {

                        Toast.makeText(this@EditDataActivity, ""+response.body()?.message, Toast.LENGTH_SHORT).show()


                }

                override fun onFailure(call: Call<ContactOneModel>, t: Throwable) {
                    Toast.makeText(this@EditDataActivity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
                }
            })
        }

        var intent = intent
        id = intent.getStringExtra("id")

        val service = ApiService.retrofitInstance.create(ApiInterface::class.java)

        val call = service.getOneContact(id)
        call.enqueue(object : Callback<ContactOneModel> {
            override fun onResponse(call: Call<ContactOneModel>, response: Response<ContactOneModel>) {

                if(response.code() == 200){
                    editFirstNameText.setText(response.body()?.data?.firstName)
                    editLastNameText.setText(response.body()?.data?.lastName)
                    editAgeText.setText(response.body()?.data?.age.toString())
                    editPhotoText.setText(response.body()?.data?.photo)
                }

            }

            override fun onFailure(call: Call<ContactOneModel>, t: Throwable) {
                Toast.makeText(this@EditDataActivity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
            }
        })
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

}
