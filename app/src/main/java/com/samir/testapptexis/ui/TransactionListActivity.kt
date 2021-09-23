package com.samir.testapptexis.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.samir.testapptexis.R
import com.samir.testapptexis.adapters.TransActionAdapter
import com.samir.testapptexis.databinding.ActivityTransactionListBinding
import com.samir.testapptexis.global.showToast
import com.samir.testapptexis.utils.NetworkUtil
import com.samir.testapptexis.viewmodels.AppViewModelFactory
import com.samir.testapptexis.viewmodels.TransactionViewModel

class TransactionListActivity : AppCompatActivity() {

    private lateinit var transactionViewModel: TransactionViewModel
    private lateinit var binding: ActivityTransactionListBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTransactionListBinding.inflate(layoutInflater)
        setContentView(binding.root)


        transactionViewModel = ViewModelProvider(this, AppViewModelFactory())
            .get(TransactionViewModel::class.java)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)

        val name = intent.extras?.getString("name", "") ?: ""
        if(name.isNotBlank()){
            setTitle(name)
        }

        callTransactionList()
    }

    private fun callTransactionList() {
        try {
            binding.loading.visibility = View.VISIBLE
            val userId = intent.extras?.getString("userid", "0") ?: "0"
            transactionViewModel.getTransactions(userId).observe(this@TransactionListActivity, Observer {
                val transactionList = it ?: return@Observer

                binding.loading.visibility = View.GONE

                if (transactionList.isEmpty()) {
                    showToast("No Transaction found...")
                } else {
                    val transActionAdapter = TransActionAdapter(ArrayList(transactionList))
                    binding.recyclerview.adapter = transActionAdapter
                }
            })

            NetworkUtil.checkNetworkInfo(this, object : NetworkUtil.OnConnectionStatusChange {
                override fun onChange(isNetworkAvailable: Boolean) {

                    if (!isNetworkAvailable) {
                        showToast(getString(R.string.internet_error))
                    } else {
                        binding.loading.visibility = View.GONE
                    }
                }
            })
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
        }
        return super.onOptionsItemSelected(item)
    }

}