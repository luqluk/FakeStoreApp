package com.android.fakestoreapp.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.core.database.product.ProductEntity
import com.android.core.domain.repository.LocalRepository
import com.android.core.util.resource.ResourceCore
import com.android.core.util.resource.ResourceState
import kotlinx.coroutines.launch
import setLoading
import setResult

class DetailProductActivityVM(
    private val localRepository: LocalRepository
) : ViewModel() {
    val result = MutableLiveData<ResourceCore<String>>()
    fun insertProduct(product: ProductEntity) {
        viewModelScope.launch {
            result.setLoading()
            localRepository.insert(product)
            result.setResult(ResourceCore(ResourceState.Success, "Insert Success"))
        }
    }


    val product = MutableLiveData<ResourceCore<ProductEntity>>()
    fun getById(id: Int) {
        viewModelScope.launch {
            product.setLoading()
            val data = localRepository.getById(id)
            product.setResult(ResourceCore(ResourceState.Success, data))
        }
    }

}