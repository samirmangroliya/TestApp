package com.samir.testapptexis.data.datasource

import androidx.lifecycle.MutableLiveData
import com.samir.testapptexis.data.model.TransactionModel
import com.samir.testapptexis.data.repo.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class TransactionDataSource {
    fun getTransactions(userId: String): MutableLiveData<List<TransactionModel>> {
        val tlMutableLiveData = MutableLiveData<List<TransactionModel>>()
        try {
            val call = RetrofitClient.instance?.api?.getTransactions(userId)
            call?.enqueue(object : Callback<List<TransactionModel>?> {
                override fun onResponse(call: Call<List<TransactionModel>?>, response: Response<List<TransactionModel>?>) {
                    try {
                        if (response.isSuccessful && response.body() != null) {
                            tlMutableLiveData.value = response.body()
                        } else {
                            tlMutableLiveData.value = emptyList()
                        }
                    } catch (e: Exception) {
                        tlMutableLiveData.value = emptyList()
                        e.printStackTrace()
                    }
                }

                override fun onFailure(call: Call<List<TransactionModel>?>, t: Throwable) {
                    tlMutableLiveData.value = emptyList()
                }
            })

        } catch (e: Throwable) {
            e.printStackTrace()
        }

        return tlMutableLiveData
    }
}