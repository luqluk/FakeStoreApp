package com.android.fakestoreapp.ui.main.adapter

import android.view.View
import com.android.core.domain.model.ProductDomainModel
import com.android.fakestoreapp.R
import com.android.fakestoreapp.base.BaseListAdapter
import com.android.fakestoreapp.databinding.GridItemProductBinding
import com.bumptech.glide.Glide

class ProductAdapter : BaseListAdapter<ProductDomainModel, GridItemProductBinding>(
    GridItemProductBinding::inflate
) {
    private var listener: ((ProductDomainModel) -> Unit)? = null
    override fun onItemBind(): (ProductDomainModel, GridItemProductBinding, View, Int) -> Unit =
        { item, binding, itemView, _ ->
            binding.apply {
                title.text = item.title
                category.text = item.category
                count.text = item.count.toString()
                price.text = itemView.context.getString(R.string.price, item.price.toString())
                rating.rating = item.rate?.toFloat() ?: 0f
                Glide.with(itemView.context)
                    .load(item.image)
                    .into(image)
                root.setOnClickListener {
                    listener?.invoke(item)
                }
            }
        }

    fun setOnItemClickListener(listener: (ProductDomainModel) -> Unit) {
        this.listener = listener
    }
}
