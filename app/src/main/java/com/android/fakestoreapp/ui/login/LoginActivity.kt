package com.android.fakestoreapp.ui.login

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import com.android.core.domain.model.LoginBody
import com.android.core.util.resource.ResourceState
import com.android.fakestoreapp.R
import com.android.fakestoreapp.base.BaseActivity
import com.android.fakestoreapp.databinding.ActivityLoginBinding
import com.android.fakestoreapp.ui.main.MainActivity
import com.google.android.material.textfield.TextInputLayout
import com.android.core.util.extension.gone
import com.android.core.util.extension.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>() {
    private val loginActivityVM: LoginActivityVM by viewModel()

    override fun getViewBinding(): ActivityLoginBinding =
        ActivityLoginBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (loginActivityVM.getToken()?.isNotEmpty() == true) {
            navigateToMain()
        }
        initObserver()
        initUI()
    }

    private fun initListener() {

        binding.btnLogin.setOnClickListener {
            val inputMethodManager =
                getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(binding.root.windowToken, 0)

            resetErrors()

            val username = binding.username.text.toString().trim()
            val password = binding.password.text.toString().trim()

            when {
                username.isEmpty() -> {
                    showError(binding.etUsername, getString(R.string.username_should_filled))
                }

                password.isEmpty() -> {
                    showError(binding.etPassword, getString(R.string.password_cannot_be_empty))
                }

                else -> {
                    val loginBody = LoginBody(username, password)
                    loginActivityVM.login(loginBody)
                }
            }
        }
    }

    private fun initObserver() {
        loginActivityVM.loginData.observe(this) {
            when (it.resourceState) {
                is ResourceState.Loading -> {
                    showLoading(true)
                }

                is ResourceState.Success -> {
                    showLoading(false)
                    it.data?.let { data ->
                        data.token?.let { it1 -> loginActivityVM.saveToken(it1) }
                    }
                    navigateToMain()
                }

                is ResourceState.Error.UnknownHost -> {
                    showLoading(false)
                    snackBarError(getString(R.string.there_is_no_connection))
                }

                else -> {
                    showLoading(false)
                    snackBarError(getString(R.string.something_went_wrong))
                }
            }
        }
    }

    private fun initUI() {
//        if (loginActivityVM.getToken().isNotEmpty()) {
//            navigateToMain()
//        }
        initListener()
    }

    private fun navigateToMain() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun resetErrors() {
        binding.etUsername.isErrorEnabled = false
        binding.etPassword.isErrorEnabled = false
    }

    private fun showError(editText: TextInputLayout, errorMessage: String) {
        editText.isErrorEnabled = true
        editText.error = errorMessage
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            showBaseDialog(false)
            binding.btnLogin.gone()
        } else {
            dismissBaseDialog()
            binding.btnLogin.visible()
        }
    }
}