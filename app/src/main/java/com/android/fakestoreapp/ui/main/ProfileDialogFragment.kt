package com.android.fakestoreapp.ui.main

import android.content.Intent
import android.os.Bundle
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.fakestoreapp.R
import com.android.fakestoreapp.databinding.FragmentProfileDialogBinding
import com.android.fakestoreapp.ui.login.LoginActivity
import com.android.fakestoreapp.ui.login.LoginActivityVM
import com.google.android.material.bottomsheet.BottomSheetBehavior
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileDialogFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentProfileDialogBinding? = null

    private val binding get() = _binding!!

    private val loginActivityVM: LoginActivityVM by viewModel()


    override fun getTheme(): Int = R.style.BottomSheetDialogTheme

    override fun onStart() {
        super.onStart()
        val bottomSheet = dialog?.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)
        bottomSheet?.let { sheet ->
            val behavior = BottomSheetBehavior.from(sheet)

            // Allow default initial behavior (peek or wrap)
            sheet.layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT
            sheet.requestLayout()

            // Listen to state changes to expand to fullscreen when expanded
            behavior.addBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {
                override fun onStateChanged(bottomSheet: View, newState: Int) {
                    if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                        // Make it fullscreen now
                        sheet.layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
                        sheet.requestLayout()
                    }
                }

                override fun onSlide(bottomSheet: View, slideOffset: Float) {}
            })
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileDialogBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.logout.setOnClickListener {
            loginActivityVM.logout()
            dismiss()
            startActivity(Intent(context, LoginActivity::class.java))
            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}