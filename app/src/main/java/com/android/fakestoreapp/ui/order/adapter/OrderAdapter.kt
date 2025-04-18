package com.android.fakestoreapp.ui.order.adapter

import android.view.View
import com.android.core.database.product.ProductEntity
import com.android.fakestoreapp.R
import com.android.fakestoreapp.base.BaseListAdapter
import com.android.fakestoreapp.databinding.ListOrderItemBinding
import com.bumptech.glide.Glide

class OrderAdapter() : BaseListAdapter<ProductEntity, ListOrderItemBinding>(
    ListOrderItemBinding::inflate
) {
    override fun onItemBind(): (ProductEntity, ListOrderItemBinding, View, Int) -> Unit =
        { item, binding, itemView, _ ->
            binding.apply {
                tvTitle.text = item.title
                tvCategory.text = item.category
                val priceTotal = item.price * item.piece
                val priceString = String.format("%.2f", priceTotal)
                price.text = itemView.context.getString(R.string.price, priceString )
                piece.text = item.piece.toString()
                Glide.with(itemView.context)
                    .load(item.image)
                    .into(imageView2)
            }
        }


}
