package com.android.fakestoreapp.ui.cart

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.core.database.product.ProductEntity
import com.android.core.util.resource.ResourceState
import com.android.fakestoreapp.R
import com.android.fakestoreapp.base.BaseActivity
import com.android.fakestoreapp.databinding.ActivityCartBinding
import com.android.fakestoreapp.ui.cart.adapter.CartAdapter
import com.android.fakestoreapp.ui.order.SuccessActivity
import com.android.core.util.extension.gone
import com.android.core.util.extension.visible
import org.koin.androidx.viewmodel.ext.android.viewModel

class CartActivity : BaseActivity<ActivityCartBinding>() {
    override fun getViewBinding(): ActivityCartBinding =
        ActivityCartBinding.inflate(layoutInflater)

    private val cartActivityVM: CartActivityVM by viewModel()

    private val cartAdapter: CartAdapter by lazy {
        CartAdapter(
            object : CartAdapter.OnItemClickListener {
                override fun onDeleteClicked(data: ProductEntity) {
                    cartActivityVM.deleteProduct(data)
                }

                override fun onMinus(data: ProductEntity) {
                    cartActivityVM.updateProduct(data)
                }

                override fun onPlus(data: ProductEntity) {
                    cartActivityVM.updateProduct(data)
                }

                override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                }

            })

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        cartActivityVM.getAllProduct()
        observeLivedata()
        initUI()
    }

    private fun initUI() {
        initRecyclerView()
        initListener()
    }

    private fun initListener() {
        with(binding) {
            btnCart.setOnClickListener {
                startActivity(Intent(this@CartActivity, SuccessActivity::class.java))
                finish()
            }
        }
    }

    private fun initRecyclerView() {
        with(binding){
            rvCart.apply {
                layoutManager = LinearLayoutManager(this@CartActivity)
                itemAnimator  = null
                adapter = cartAdapter

            }
        }
    }

    private fun observeLivedata() {
        cartActivityVM.products.observe(this) { resource ->
            when (resource.resourceState) {
                ResourceState.Loading -> {
                    binding.rvCart.gone()
                }
                ResourceState.Success -> {
                    binding.rvCart.visible()
                    resource.data?.let { data ->
                        val totalPrice = data.sumOf { it.price * it.piece }
                        val stringPrice = String.format("%.2f", totalPrice)
                        binding.amount.text = getString(R.string.price, stringPrice)
                        cartAdapter.submitList(data)
                    }
                }
                else -> {
                    binding.rvCart.gone()
                }
            }
        }
    }
}