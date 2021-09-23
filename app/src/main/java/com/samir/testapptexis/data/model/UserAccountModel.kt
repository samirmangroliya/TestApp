package com.samir.testapptexis.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserAccountModel(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("name")
    @Expose
    var name: String? = null,

    @SerializedName("balance")
    @Expose
    var balance: Double? = null
)