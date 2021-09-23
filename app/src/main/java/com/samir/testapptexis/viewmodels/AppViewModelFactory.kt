package com.samir.testapptexis.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.samir.testapptexis.data.datasource.LoginDataSource
import com.samir.testapptexis.data.datasource.TransactionDataSource
import com.samir.testapptexis.data.datasource.UserDataSource
import com.samir.testapptexis.data.repo.LoginRepository
import com.samir.testapptexis.data.repo.TransactionRepository
import com.samir.testapptexis.data.repo.UserRepository

class AppViewModelFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                loginRepository = LoginRepository(
                    dataSource = LoginDataSource()
                )
            ) as T
        }

        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(
                userRepository = UserRepository(
                    dataSource = UserDataSource()
                )
            ) as T
        }

        if (modelClass.isAssignableFrom(TransactionViewModel::class.java)) {
            return TransactionViewModel(
                transactionRepository = TransactionRepository(
                    dataSource = TransactionDataSource()
                )
            ) as T
        }

        throw IllegalArgumentException("Unknown ViewModel class")
    }
}