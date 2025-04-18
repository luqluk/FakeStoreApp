package com.android.fakestoreapp.ui.login

import android.content.SharedPreferences
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.core.data.model.LoginResponse
import com.android.core.domain.model.LoginBody
import com.android.core.domain.repository.MainRepository
import com.android.core.util.resource.ResourceCore
import kotlinx.coroutines.launch
import setLoading
import setResult

class LoginActivityVM(
    private val repository: MainRepository,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    val loginData = MutableLiveData<ResourceCore<LoginResponse>>()

    fun login(login: LoginBody){
        viewModelScope.launch {
            loginData.setLoading()
            val result = repository.login(login)
            loginData.setResult(result)
        }
    }

    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    fun logout() {
        sharedPreferences.edit().clear().apply()
    }

//    fun getToken(): String {
//        return localRepository.getToken()
//    }
//
//    fun setToken(token: String) {
//        localRepository.setToken(token)
//    }
//
//    fun getName(): String {
//        return localRepository.getUserName()
//    }
//
//    fun setName(name: String) {
//        localRepository.setUserName(name)
//    }
//
//    fun setEmail(name: String) {
//        localRepository.setEmail(name)
//    }
}