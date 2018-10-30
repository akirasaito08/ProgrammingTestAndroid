package com.example.akira08.programmingtest

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.ListView
import android.widget.Toast
import com.example.akira08.programmingtest.adapter.MainAdapter
import com.example.akira08.programmingtest.api.`interface`.ApiInterface
import com.example.akira08.programmingtest.api.model.ContactModel
import com.example.akira08.programmingtest.api.service.ApiService

import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.example.akira08.programmingtest.api.model.DataContactModel


class MainActivity : AppCompatActivity() {

    lateinit var mainList:ListView
    var mainAdapter: MainAdapter? = null
    var dataContact:List<DataContactModel>? = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        mainList = findViewById(R.id.mainList)

        val service = ApiService.retrofitInstance.create(ApiInterface::class.java)

        val call = service.allContact
        call.enqueue(object : Callback<ContactModel> {
            override fun onResponse(call: Call<ContactModel>, response: Response<ContactModel>) {

                if(response.code() == 200){
                    dataContact = response.body()?.data
                    mainAdapter = MainAdapter(this@MainActivity, dataContact)
                    mainList.adapter = mainAdapter
                }

            }

            override fun onFailure(call: Call<ContactModel>, t: Throwable) {
                Toast.makeText(this@MainActivity, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show()
            }
        })

        mainList.setOnItemClickListener { parent, view, position, id ->
            var intent = Intent(this, EditDataActivity::class.java)
            intent.putExtra("id", dataContact?.get(position)?.id)
            startActivity(intent)
        }

        fab.setOnClickListener { view ->
            var intent = Intent(this, AddDataActivity::class.java)
            startActivity(intent)
        }
    }

}
