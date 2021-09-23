package com.samir.testapptexis.data.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class TransactionModel(
    @SerializedName("id")
    @Expose
    var id: String? = null,

    @SerializedName("title")
    @Expose
    var title: String? = null,

    @SerializedName("balance")
    @Expose
    var balance: Double? = null
)