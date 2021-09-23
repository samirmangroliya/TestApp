package com.samir.testapptexis.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.samir.testapptexis.R
import com.samir.testapptexis.databinding.ActivityLoginBinding
import com.samir.testapptexis.global.PreferenceHelper
import com.samir.testapptexis.global.afterTextChanged
import com.samir.testapptexis.global.hideKeyboard
import com.samir.testapptexis.utils.NetworkUtil
import com.samir.testapptexis.viewmodels.AppViewModelFactory
import com.samir.testapptexis.viewmodels.LoginViewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var loginViewModel: LoginViewModel
    public lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val username = binding.username
        val password = binding.password
        val login = binding.login
        val loading = binding.loading

        loginViewModel = ViewModelProvider(this, AppViewModelFactory())
            .get(LoginViewModel::class.java)

        loginViewModel.loginFormState.observe(this@LoginActivity, Observer {
            val loginState = it ?: return@Observer

            // disable login button unless both username / password is valid
            login.isEnabled = loginState.isDataValid

            if (loginState.usernameError != null) {
                username.error = getString(loginState.usernameError)
            }
            if (loginState.passwordError != null) {
                password.error = getString(loginState.passwordError)
            }
        })


        username.afterTextChanged {
            loginViewModel.loginDataChanged(
                username.text.toString(),
                password.text.toString()
            )
        }

        password.apply {
            afterTextChanged {
                loginViewModel.loginDataChanged(
                    username.text.toString(),
                    password.text.toString()
                )
            }

            login.setOnClickListener {
                loading.visibility = View.VISIBLE
                loginViewModel.login(username.text.toString(), password.text.toString()).observe(this@LoginActivity, Observer {
                    val loginResult = it ?: return@Observer

                    loading.visibility = View.GONE
                    loginResult.msg?.let { msg -> showToast(msg) }

                    if (loginResult.success) {
                        PreferenceHelper.saveValue(this@LoginActivity, "isloggedin", true)
                        moveToDashBoard()
                    }
                })
            }
        }

        binding.container.setOnClickListener { hideKeyboard() }

        NetworkUtil.checkNetworkInfo(this, object : NetworkUtil.OnConnectionStatusChange {
            override fun onChange(isNetworkAvailable: Boolean) {
                if (!isNetworkAvailable) {
                    showToast(getString(R.string.internet_error))
                }
            }
        })
    }

    private fun moveToDashBoard() {
        try {
            val intent = Intent(this@LoginActivity, DashBoardActivity::class.java)
            startActivity(intent)
            this.finish()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun showToast(errorString: String) {
        Toast.makeText(applicationContext, errorString, Toast.LENGTH_SHORT).show()
    }
}