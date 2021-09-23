package com.samir.testapptexis.data.repo

import androidx.lifecycle.MutableLiveData
import com.samir.testapptexis.data.datasource.UserDataSource
import com.samir.testapptexis.data.model.UserAccountModel


class UserRepository(val dataSource: UserDataSource) {
    fun getAccounts(): MutableLiveData<List<UserAccountModel>> {
        return dataSource.getAccounts()
    }
}