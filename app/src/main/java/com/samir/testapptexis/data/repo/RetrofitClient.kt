package com.samir.testapptexis.data.repo

import com.google.gson.GsonBuilder
import com.samir.testapptexis.global.GlobalConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RetrofitClient private constructor() {
    private var mRetrofit: Retrofit? = null

    val api: Api?
        get() = mRetrofit?.create(Api::class.java)

    companion object {
        private var sInstance: RetrofitClient? = null

        /**
         * Synchronized retrofit instance
         *
         * @return = instance of retrofit class
         */
        @get:Synchronized
        val instance: RetrofitClient?
            get() {
                if (sInstance == null) {
                    sInstance = RetrofitClient()
                }
                return sInstance
            }
    }

    /**
     * constructor
     * init Retrofit
     */
    init {
        try {
            val gson = GsonBuilder()
                .setLenient()
                .create()


            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)

            val okHttpClient = OkHttpClient().newBuilder()
                .connectTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .addInterceptor(interceptor).build()
            mRetrofit = Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(GlobalConstants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}