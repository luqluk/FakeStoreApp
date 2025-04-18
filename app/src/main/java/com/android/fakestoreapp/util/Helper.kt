package com.android.fakestoreapp.util

import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.viewbinding.ViewBinding
import com.android.fakestoreapp.R
import com.google.android.material.snackbar.Snackbar

fun baseSnackBar(message: String, color: Int, binding: ViewBinding) {
    val snackBar = Snackbar.make(
        binding.root,
        message,
        Snackbar.LENGTH_LONG
    ).apply {
        setBackgroundTint(ContextCompat.getColor(context, color))
        setTextColor(ContextCompat.getColor(context, R.color.white))
        val view = this.view
        val textView = view.findViewById<TextView>(com.google.android.material.R.id.snackbar_text)
        textView.apply {
            typeface = ResourcesCompat.getFont(context, R.font.metropolis_medium)
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
    }
    snackBar.show()
}
