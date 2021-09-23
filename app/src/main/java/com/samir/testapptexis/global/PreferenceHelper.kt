package com.samir.testapptexis.global

import android.content.Context
import android.content.SharedPreferences


object PreferenceHelper {
    private var sharedPreferences: SharedPreferences? = null

    private fun getInstance(context: Context): SharedPreferences? {
        try {
            sharedPreferences = context.getSharedPreferences(
                GlobalConstants.PREF_NAME, Context.MODE_PRIVATE
            )
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return sharedPreferences
    }

    // Save String ************************************
    fun saveValue(context: Context, key: String, value: Boolean) {
        try {
            val mEditor = getInstance(context)?.edit()
            mEditor?.putBoolean(key, value)
            mEditor?.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getValueFromKey(context: Context, key: String): Boolean? {
        try {
            val userLoggedIn = getInstance(context)?.getBoolean(key, false)
            return userLoggedIn
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return false
    }

    fun clearAllData(context: Context) {
        try {
            val mEditor = getInstance(context)?.edit()
            mEditor?.clear()
            mEditor?.apply()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}