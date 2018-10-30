package com.example.akira08.programmingtest.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ContactDeleteModel {

    @SerializedName("message")
    @Expose
    var message: String? = null

}