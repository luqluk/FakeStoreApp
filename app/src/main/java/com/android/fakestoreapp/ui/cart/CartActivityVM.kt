package com.android.fakestoreapp.ui.cart

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

class CartActivityVM(
    private val localRepository: LocalRepository
) : ViewModel() {
    val products = MutableLiveData<ResourceCore<List<ProductEntity>>>()
    fun getAllProduct() {
        viewModelScope.launch {
            products.setLoading()
            val data = localRepository.getAll()
            products.setResult(ResourceCore(ResourceState.Success, data))
        }
    }

    fun updateProduct(productEntity: ProductEntity) {
        viewModelScope.launch {
//            products.setLoading()
            localRepository.update(productEntity)
            getAllProduct()
//            products.setResult(ResourceCore(ResourceState.Success, data))
        }
    }

    fun deleteProduct(productEntity: ProductEntity) {
        viewModelScope.launch {
            localRepository.delete(productEntity)
            getAllProduct()
        }
    }

}