package com.android.fakestoreapp.ui.order

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.core.util.resource.ResourceState
import com.android.fakestoreapp.R
import com.android.fakestoreapp.base.BaseActivity
import com.android.fakestoreapp.databinding.ActivityOrderBinding
import com.android.fakestoreapp.ui.cart.CartActivityVM
import com.android.fakestoreapp.ui.order.adapter.OrderAdapter
import com.android.core.util.extension.gone
import com.android.core.util.extension.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class OrderActivity : BaseActivity<ActivityOrderBinding>() {
    override fun getViewBinding(): ActivityOrderBinding =
        ActivityOrderBinding.inflate(layoutInflater)

    private val cartActivityVM: CartActivityVM by viewModel()

    private val orderAdapter: OrderAdapter by lazy {
        OrderAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartActivityVM.getAllProduct()
        observeLivedata()
        initUI()
    }
    private fun initUI() {
        initRecyclerView()
    }

    private fun initRecyclerView() {
        with(binding){
            rvOrder.apply {
                layoutManager = LinearLayoutManager(this@OrderActivity)
                itemAnimator  = null
                adapter = orderAdapter

            }
        }
    }

    private fun observeLivedata() {
        cartActivityVM.products.observe(this) { resource ->
            when (resource.resourceState) {
                ResourceState.Loading -> {
                    binding.rvOrder.gone()
                }
                ResourceState.Success -> {
                    binding.rvOrder.visible()
                    resource.data?.let { data ->
                        binding.items.text = getString(R.string.items, data.size.toString())
                        val totalPrice = data.sumOf { it.price * it.piece }
                        val stringPrice = String.format("%.2f", totalPrice)
                        binding.totalAmount.text = getString(R.string.price, stringPrice)
                        orderAdapter.submitList(data)
                    }
                }
                else -> {
                    binding.rvOrder.gone()
                }
            }
        }
    }
}