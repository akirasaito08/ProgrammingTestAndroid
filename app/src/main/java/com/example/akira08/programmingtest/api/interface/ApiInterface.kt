package com.example.akira08.programmingtest.api.`interface`

import com.example.akira08.programmingtest.api.model.ContactDeleteModel
import com.example.akira08.programmingtest.api.model.ContactModel
import com.example.akira08.programmingtest.api.model.ContactOneModel
import retrofit2.Call
import retrofit2.http.*
import retrofit2.http.GET



interface ApiInterface {

    @PUT("contact/{id}")
    @FormUrlEncoded
    fun editContact(@Path("id") id: String,
                    @Field("firstName") firstName: String,
                    @Field("lastName") lastName: String,
                    @Field("age") age: Int,
                    @Field("photo") photo: String): Call<ContactOneModel>

    @GET("contact/{id}")
    fun getOneContact(@Path("id") id: String): Call<ContactOneModel>

    @DELETE("contact/{id}")
    fun deleteContact(@Path("id") id: String): Call<ContactDeleteModel>

    @get:GET("contact")
    val allContact: Call<ContactModel>

    @POST("contact")
    @FormUrlEncoded
    fun savePost(@Field("firstName") firstName: String,
                 @Field("lastName") lastName: String,
                 @Field("age") age: Number,
                 @Field("photo") photo: String): Call<ContactModel>


}