package com.samir.testapptexis.data.datasource

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.samir.testapptexis.data.model.LoginResult
import com.samir.testapptexis.data.repo.RetrofitClient
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    fun login(username: String, password: String): MutableLiveData<LoginResult> {
        val loginMutableData = MutableLiveData<LoginResult>()

        try {
            val loginBody = "username=$username&password=$password"
            Log.d("loginBody", loginBody)
            val call = RetrofitClient.instance?.api?.login(loginBody)
            call?.enqueue(object : Callback<ResponseBody?> {
                override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                    val loginResult = try {
                        Log.d("response code::", "${response.code()}")
                        if (response.code() == 200) {
                            LoginResult(true, "successfully logged in...")
                        } else {
                            LoginResult(false, "Invalid username or password...")
                        }
                    } catch (e: Exception) {
                        e.printStackTrace()
                        LoginResult(false, "failed to login...${e.message}")
                    }
                    Log.d("LoginResult:: ",">> $loginResult")
                    loginMutableData.postValue(loginResult)
                }

                override fun onFailure(call: Call<ResponseBody?>, throwable: Throwable) {
                    Log.d("false code::", "${throwable.message}")
                }
            })

        } catch (e: Throwable) {
            e.printStackTrace()
        }

        return loginMutableData
    }
}