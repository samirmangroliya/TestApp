package com.samir.testapptexis.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.ConnectivityManager.NetworkCallback
import android.net.Network
import android.os.Build
import android.util.Log

object NetworkUtil {
    private const val TAG = "NetworkUtil"
    fun checkNetworkInfo(context: Context, onConnectionStatusChange: OnConnectionStatusChange) {
        try {
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {

                connectivityManager.registerDefaultNetworkCallback(object : NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        onConnectionStatusChange.onChange(true)
                    }

                    override fun onLost(network: Network) {
                        Log.d(TAG, "onLost: $network")
                        onConnectionStatusChange.onChange(false)
                    }
                })
            } else {
                val networkInfo = connectivityManager.activeNetworkInfo
                Log.d(TAG, "checkNetworkInfo: " + networkInfo.toString())
                onConnectionStatusChange.onChange(networkInfo != null && networkInfo.isConnectedOrConnecting)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    interface OnConnectionStatusChange {
        fun onChange(isNetworkAvailable: Boolean)
    }
}