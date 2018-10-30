package com.example.akira08.programmingtest.api.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class ContactModel {

    @SerializedName("message")
    @Expose
    var message: String? = null
    @SerializedName("data")
    @Expose
    var data: List<DataContactModel>? = null

}