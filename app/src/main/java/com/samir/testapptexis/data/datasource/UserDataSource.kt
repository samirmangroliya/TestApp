package com.samir.testapptexis.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.samir.testapptexis.data.model.UserAccountModel
import com.samir.testapptexis.data.repo.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class UserDataSource {
    fun getAccounts(): MutableLiveData<List<UserAccountModel>> {
        val tlMutableLiveData = MutableLiveData<List<UserAccountModel>>()
        try {
            val call = RetrofitClient.instance?.api?.getAccounts()
            call?.enqueue(object : Callback<List<UserAccountModel>?> {
                override fun onResponse(call: Call<List<UserAccountModel>?>, response: Response<List<UserAccountModel>?>) {
                    try {
                        if (response.isSuccessful && response.body() != null) {
                            Log.d("size of accounts...", "${response.body()?.size}")
                            tlMutableLiveData.value = response.body()
                        } else {
                            tlMutableLiveData.value = emptyList()
                        }
                    } catch (e: Exception) {
                        tlMutableLiveData.value = emptyList()
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<List<UserAccountModel>?>, t: Throwable) {
                    tlMutableLiveData.value = emptyList()
                }
            })

        } catch (e: Throwable) {
            e.printStackTrace()
        }

        return tlMutableLiveData
    }
}