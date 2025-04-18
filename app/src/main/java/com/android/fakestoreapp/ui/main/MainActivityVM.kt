package com.android.fakestoreapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.core.domain.model.ListProductDomainModel
import com.android.core.domain.repository.MainRepository
import com.android.core.util.resource.ResourceCore
import kotlinx.coroutines.launch
import setLoading
import setResult

class MainActivityVM(
    private val repository: MainRepository
) : ViewModel() {
    val listProduct = MutableLiveData<ResourceCore<ListProductDomainModel>>()
    fun fetchProduct(){
        viewModelScope.launch {
            listProduct.setLoading()
            val result = repository.getProducts()
            listProduct.setResult(result)
        }
    }
}