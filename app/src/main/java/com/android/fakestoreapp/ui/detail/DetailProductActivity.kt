package com.android.fakestoreapp.ui.detail

import android.os.Bundle
import com.android.core.domain.model.ProductDomainModel
import com.android.core.util.mapper.asProductEntity
import com.android.core.util.resource.ResourceState
import com.android.fakestoreapp.R
import com.android.fakestoreapp.base.BaseActivity
import com.android.fakestoreapp.databinding.ActivityDetailProductBinding
import com.bumptech.glide.Glide
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailProductActivity : BaseActivity<ActivityDetailProductBinding>() {
    override fun getViewBinding(): ActivityDetailProductBinding =
        ActivityDetailProductBinding.inflate(layoutInflater)
    private var counts = 0

    private val detailProductActivityVM: DetailProductActivityVM by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initView()
        initObserver()
    }

    private fun initView() {
        binding.topAppBar.setNavigationOnClickListener {
            finish()
        }
        val product = intent.extras?.getParcelable<ProductDomainModel>("data")
        product?.id?.let { detailProductActivityVM.getById(it) }

        with(binding){
            Glide.with(this@DetailProductActivity)
                .load(product?.image)
                .into(image)
            title.text = product?.category
            productName.text = product?.title
            productPrice.text = getString(R.string.price, product?.price.toString())
            category.text = product?.category
            productDescription.text = product?.description
            rating.rating = product?.rate?.toFloat() ?: 0f
            count.text = product?.count.toString()



            btnCart.setOnClickListener {
                val data = product?.copy(piece = counts)
                data?.asProductEntity()
                    ?.let { detailProductActivityVM.insertProduct(it) }
            }
        }
    }

    private fun initObserver() {
        detailProductActivityVM.product.observe(this) { result ->
            if (result.resourceState == ResourceState.Success) {
                val data = result.data
                counts = if (data != null) {
                    data.piece + 1
                } else {
                    1
                }
            }
        }
        detailProductActivityVM.result.observe(this) { result ->
            if (result.resourceState == ResourceState.Success) {
                showToast(result.data ?: "Success")
            }
        }
    }
}