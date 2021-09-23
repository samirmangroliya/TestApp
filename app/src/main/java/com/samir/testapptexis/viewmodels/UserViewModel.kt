package com.samir.testapptexis.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.samir.testapptexis.data.model.UserAccountModel
import com.samir.testapptexis.data.repo.UserRepository

class UserViewModel(private val userRepository: UserRepository) : ViewModel() {
    fun getAccounts(): LiveData<List<UserAccountModel>> {
        return userRepository.getAccounts()
    }
}