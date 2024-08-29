package com.super_rabbit.demo.demo_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.super_rabbit.demo.databinding.FragmentUnlimitedNumberPickerDemoBinding

class UnlimitedNumberPickerDemo : androidx.fragment.app.Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        val binding = FragmentUnlimitedNumberPickerDemoBinding.inflate(inflater, container, false)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UnlimitedNumberPickerDemo().apply {
                arguments = Bundle().apply {}
            }
    }
}
