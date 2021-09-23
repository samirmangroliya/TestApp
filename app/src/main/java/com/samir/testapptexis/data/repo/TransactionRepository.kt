package com.samir.testapptexis.data.repo

import androidx.lifecycle.MutableLiveData
import com.samir.testapptexis.data.datasource.TransactionDataSource
import com.samir.testapptexis.data.model.TransactionModel


class TransactionRepository(val dataSource: TransactionDataSource) {
    fun getTransactions(userId: String): MutableLiveData<List<TransactionModel>> {
        return dataSource.getTransactions(userId)
    }
}