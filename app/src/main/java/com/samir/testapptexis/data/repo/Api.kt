package com.samir.testapptexis.data.repo

import com.samir.testapptexis.data.model.LoginResult
import com.samir.testapptexis.data.model.TransactionModel
import com.samir.testapptexis.data.model.UserAccountModel
import com.samir.testapptexis.global.GlobalConstants
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface Api {
    @POST(GlobalConstants.LOGIN)
    fun login(@Body userNamePassword: String): Call<ResponseBody?>?

    @GET(GlobalConstants.ACCOUNTS)
    fun getAccounts(): Call<List<UserAccountModel>?>?

    @GET(GlobalConstants.TRANSACTIONS)
    fun getTransactions(@Query(GlobalConstants.PARAM_ACCOUNT_ID) accountId: String): Call<List<TransactionModel>?>?
}