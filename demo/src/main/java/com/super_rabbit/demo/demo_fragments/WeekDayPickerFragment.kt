package com.super_rabbit.demo.demo_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.super_rabbit.demo.databinding.FragmentWeekDayPickerBinding
import com.super_rabbit.demo.wheel_picker_adapters.WPWeekDaysPickerAdapter

class WeekDayPickerFragment : androidx.fragment.app.Fragment() {
    private lateinit var binding: FragmentWeekDayPickerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentWeekDayPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.weekDayPicker.setAdapter(WPWeekDaysPickerAdapter())
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            WeekDayPickerFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
