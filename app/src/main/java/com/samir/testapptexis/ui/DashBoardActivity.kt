package com.samir.testapptexis.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.samir.testapptexis.R
import com.samir.testapptexis.adapters.AccountAdapter
import com.samir.testapptexis.data.model.UserAccountModel
import com.samir.testapptexis.databinding.ActivityDashboardBinding
import com.samir.testapptexis.global.PreferenceHelper
import com.samir.testapptexis.global.showToast
import com.samir.testapptexis.utils.NetworkUtil
import com.samir.testapptexis.viewmodels.AppViewModelFactory
import com.samir.testapptexis.viewmodels.UserViewModel

class DashBoardActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        binding.activityDashBoard = this
        setContentView(binding.root)

        userViewModel = ViewModelProvider(this, AppViewModelFactory())
            .get(UserViewModel::class.java)

        callGetAccounts()

    }

    private fun callGetAccounts() {
        try {
            binding.loading.visibility = View.VISIBLE

            userViewModel.getAccounts().observe(this@DashBoardActivity, Observer {
                val accountList = it ?: return@Observer

                binding.loading.visibility = View.GONE

                if (accountList.isEmpty()) {
                    showToast("No accounts found...")
                } else {
                    val accountAdapter = AccountAdapter(this, accountList)
                    binding.recyclerview.adapter = accountAdapter
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

    fun onClickAccount(userAccountModel: UserAccountModel?) {
        try {
            userAccountModel?.let {
                val intent = Intent(this@DashBoardActivity, TransactionListActivity::class.java)
                intent.putExtra("userid", it.id)
                intent.putExtra("name", it.name)
                startActivity(intent)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun onClickLogout() {
        try {
            AlertDialog.Builder(this).setTitle(getString(R.string.logout)).setMessage(getString(R.string.logout_msg))
                .setPositiveButton("Yes") { dialog, _ ->
                    dialog?.dismiss()
                    clearData()
                }.setNegativeButton("No") { dialog, _ -> dialog?.dismiss() }.create().show()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun clearData() {
        try {
            PreferenceHelper.clearAllData(this)
            val intent = Intent(this@DashBoardActivity, LoginActivity::class.java)
            startActivity(intent)
            this.finishAffinity()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}