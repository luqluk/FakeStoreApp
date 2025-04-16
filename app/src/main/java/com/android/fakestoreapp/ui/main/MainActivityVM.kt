package com.android.fakestoreapp.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.android.core.data.model.LaunchInfoResponse
import com.android.core.domain.repository.RocketRepository
import com.android.core.util.resource.ResourceCore
import kotlinx.coroutines.launch
import setLoading
import setResult

class MainActivityVM(
    private val repository: RocketRepository
) : ViewModel() {
    val listRocket = MutableLiveData<ResourceCore<List<LaunchInfoResponse>>>()
    fun fetchListRocket(){
        viewModelScope.launch {
            listRocket.setLoading()
            val result = repository.fetchAllLaunches()
            listRocket.setResult(result)
        }
    }
}