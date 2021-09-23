package com.samir.testapptexis.data.repo

import androidx.lifecycle.MutableLiveData
import com.samir.testapptexis.data.datasource.LoginDataSource
import com.samir.testapptexis.data.model.LoginResult

class LoginRepository(val dataSource: LoginDataSource) {
    fun login(username: String, password: String): MutableLiveData<LoginResult> {
        return dataSource.login(username, password)
    }
}