package com.samir.testapptexis.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.samir.testapptexis.data.model.TransactionModel
import com.samir.testapptexis.data.repo.TransactionRepository

class TransactionViewModel(private val transactionRepository: TransactionRepository) : ViewModel() {
    fun getTransactions(userId: String): LiveData<List<TransactionModel>> {
        return transactionRepository.getTransactions(userId)
    }
}