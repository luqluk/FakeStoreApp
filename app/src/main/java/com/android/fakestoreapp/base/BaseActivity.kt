package com.android.fakestoreapp.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.Window
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.viewbinding.ViewBinding
import com.android.fakestoreapp.R

abstract class BaseActivity<VB : ViewBinding> : AppCompatActivity() {

    protected lateinit var binding: VB
    protected val className: String get() = this.javaClass.simpleName
    protected abstract fun getViewBinding(): VB

    private var alertLoading: AlertDialog? = null

    @SuppressLint("InflateParams")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Set content view from the binding root
        binding = getViewBinding()
        setContentView(binding.root)
        setup()
    }

    /**
     * A method to perform additional setup in child classes, optional override.
     */
    protected open fun setup() {}

    @SuppressLint("InflateParams")
    fun showBaseDialog(isCancelAble: Boolean) {
        alertLoading = AlertDialog.Builder(this).create()
        alertLoading?.requestWindowFeature(Window.FEATURE_NO_TITLE)
        alertLoading?.setView(layoutInflater.inflate(R.layout.base_loading_layout, null))
        alertLoading?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        alertLoading?.setCancelable(isCancelAble)
        val lp = alertLoading?.window?.attributes
        lp?.dimAmount = 0.0f
        alertLoading?.window?.attributes = lp
        alertLoading?.show()
    }

    fun dismissBaseDialog(){
        if (alertLoading != null && alertLoading?.isShowing == true) {
            alertLoading?.dismiss()
        }
    }

    fun showToast(msg: String, length: Int = Toast.LENGTH_SHORT){
        Toast.makeText(baseContext, msg, length).show()
    }
}
