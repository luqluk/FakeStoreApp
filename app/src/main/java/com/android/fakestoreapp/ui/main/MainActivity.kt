package com.android.fakestoreapp.ui.main

import android.content.Intent
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.android.core.domain.model.ProductDomainModel
import com.android.core.util.resource.ResourceState
import com.android.fakestoreapp.R
import com.android.fakestoreapp.base.BaseActivity
import com.android.fakestoreapp.databinding.ActivityMainBinding
import com.android.fakestoreapp.ui.cart.CartActivity
import com.android.fakestoreapp.ui.detail.DetailProductActivity
import com.android.fakestoreapp.ui.main.adapter.ProductAdapter
import com.google.android.material.chip.Chip
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override fun getViewBinding(): ActivityMainBinding =
        ActivityMainBinding.inflate(layoutInflater)

    // Use Koin's viewModel delegate to inject the ViewModel
    private val mainActivityVM: MainActivityVM by viewModel()

    private val productAdapter: ProductAdapter by lazy {
        ProductAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityVM.fetchProduct()
        observeLivedata()
        initUI()
    }

    private fun initUI() {
        initRecyclerView()
        initListener()
    }

    private fun initListener() {
        with(binding) {
            cart.setOnClickListener {
                startActivity(Intent(this@MainActivity, CartActivity::class.java))
            }
            profile.setOnClickListener {
                ProfileDialogFragment().show(supportFragmentManager, "ProfileDialogFragment")
            }
        }
    }

    private fun initRecyclerView() {
        with(binding){
            rvTodo.apply {
                layoutManager = GridLayoutManager(context, 2)
                adapter = productAdapter.apply {
                    setOnItemClickListener {
                        navigateToDetail(it)
                    }
                }

            }
        }
    }

    private fun navigateToDetail(product: ProductDomainModel) {
        val intent = Intent(this, DetailProductActivity::class.java)
        intent.putExtra("data", product)
        startActivity(intent)
    }

    private fun observeLivedata(){
        mainActivityVM.listProduct.observe(this){ product ->
            when(product.resourceState) {
                is ResourceState.Loading -> {
                    this.showBaseDialog(false)
                }
                is ResourceState.Success -> {
                    this.dismissBaseDialog()
                    if (product.data != null) {
                        productAdapter.submitList(product.data?.product)
                        val category = product.data?.product?.map {
                            it.category
                        }?.distinct()

                        binding.chipGroupFilter.removeAllViews()

                        category?.forEach { text ->
                            addChip(text, false).apply {
                                setOnCheckedChangeListener { buttonView, isChecked ->
                                    updateChipColors(buttonView as Chip, isChecked)

                                    if (isChecked) {
                                        // Filter data by selected category
                                        productAdapter.submitList(
                                            product.data?.product?.filter { productItem ->
                                                productItem.category == text
                                            }
                                        )
                                    } else {
                                        // Show all data when unchecked
                                        productAdapter.submitList(product.data?.product) {
                                            // Scroll after list is updated (submitList with callback)
                                            binding.rvTodo.scrollToPosition(0)
                                        }
                                    }
                                }
                            }
                        }

                    }
                }
                is ResourceState.Error.UnknownHost -> {
                    this.dismissBaseDialog()
                    showToast("There is no connection")
                }
                else -> {
                    this.dismissBaseDialog()
                    showToast("Something went wrong")
                }
            }
        }

    }

    private fun addChip(text: String?, isChecked: Boolean): Chip {
        val chip = Chip(this)
        if (!text.isNullOrEmpty()) {
            chip.apply {
                this.text = text
                isCheckable = true
                chipCornerRadius = 50f
                chipStrokeWidth = 0f
                typeface = ResourcesCompat.getFont(context, R.font.metropolis_medium)
                isCheckedIconVisible = false

                // Initial state
                updateChipColors(this, isChecked)

                // Set check state and listener
                this.isChecked = isChecked
            }
            binding.chipGroupFilter.addView(chip)
        }
        return chip
    }

    private fun updateChipColors(chip: Chip, isChecked: Boolean) {
        if (isChecked) {
            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.primary))
            chip.setTextColor(ContextCompat.getColor(this, R.color.black))
        } else {
            chip.chipBackgroundColor = ColorStateList.valueOf(ContextCompat.getColor(this, R.color.black))
            chip.setTextColor(ContextCompat.getColor(this, R.color.white))
        }
    }
}