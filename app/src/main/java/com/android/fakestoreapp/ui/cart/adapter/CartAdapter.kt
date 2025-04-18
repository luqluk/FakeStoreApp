package com.android.fakestoreapp.ui.cart.adapter

import android.content.Context
import android.graphics.drawable.ColorDrawable
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.PopupWindow
import androidx.cardview.widget.CardView
import com.android.core.database.product.ProductEntity
import com.android.fakestoreapp.R
import com.android.fakestoreapp.base.BaseListAdapter
import com.android.fakestoreapp.databinding.ListCartItemBinding
import com.bumptech.glide.Glide

class CartAdapter(private val listener: OnItemClickListener) : BaseListAdapter<ProductEntity, ListCartItemBinding>(
    ListCartItemBinding::inflate
) {
    override fun onItemBind(): (ProductEntity, ListCartItemBinding, View, Int) -> Unit =
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
                val number = item.piece

                menu.setOnClickListener {
                    showCustomPopupMenu(it, itemView.context, item)
                }
                btnPlus.setOnClickListener {
                    val dataPlus = item.copy(
                        piece = number + 1
                    )

                    listener.onPlus(dataPlus)
                }
                btnMinus.setOnClickListener {
                    if (number > 1) {
                        val dataMinus = item.copy(
                            piece = number - 1
                        )
                        listener.onMinus(dataMinus)
                    } else {
                        listener.onDeleteClicked(item)
                    }
                }
            }
        }

    private fun showCustomPopupMenu(anchorView: View, context: Context, item: ProductEntity) {
        val popupView = LayoutInflater.from(context).inflate(R.layout.popup_menu, null)
        val popupWindow = PopupWindow(
            popupView,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,
            true // focusable
        )

        // Set elevation (shadow) for API 21+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            popupWindow.elevation = 8 * context.resources.displayMetrics.density // 8dp
        }

        // Optional: dismiss when touching outside
        popupWindow.setBackgroundDrawable(ColorDrawable())
        popupWindow.isOutsideTouchable = true

        // Handle item clicks
        popupView.findViewById<CardView>(R.id.popup_container).setOnClickListener {
            listener.onDeleteClicked(item)
            popupWindow.dismiss()
        }

        // --- Add margin at the end (right side of screen) ---
        val marginEndDp = 32
        val marginEndPx = (marginEndDp * context.resources.displayMetrics.density).toInt()

        // Get anchor's location on screen
        val anchorLocation = IntArray(2)
        anchorView.getLocationOnScreen(anchorLocation)
        val anchorX = anchorLocation[0]
        val anchorY = anchorLocation[1] + anchorView.height

        // Measure popup width
        popupView.measure(
            View.MeasureSpec.UNSPECIFIED,
            View.MeasureSpec.UNSPECIFIED
        )
        val popupWidth = popupView.measuredWidth

        // Get screen width
        val screenWidth = context.resources.displayMetrics.widthPixels

        // Calculate X offset so popup has margin from screen end
        val xOffset = screenWidth - anchorX - popupWidth - marginEndPx

        // Show popup with calculated offset
        popupWindow.showAsDropDown(anchorView, xOffset, 0)
    }


    interface OnItemClickListener : AdapterView.OnItemClickListener {
        fun onDeleteClicked(data: ProductEntity)
        fun onMinus(data: ProductEntity)
        fun onPlus(data: ProductEntity)
    }

}
