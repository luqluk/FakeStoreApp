package com.android.fakestoreapp.ui.order

import android.content.Intent
import android.os.Bundle
import com.android.fakestoreapp.base.BaseActivity
import com.android.fakestoreapp.databinding.ActivitySuccessBinding

class SuccessActivity : BaseActivity<ActivitySuccessBinding>() {
    override fun getViewBinding(): ActivitySuccessBinding =
        ActivitySuccessBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnCheck.setOnClickListener {
            startActivity(Intent(this, OrderActivity::class.java))
            finish()
        }
    }
}