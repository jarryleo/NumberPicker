package com.super_rabbit.demo.demo_fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.super_rabbit.demo.databinding.FragmentDateAndTimePikcerBinding
import com.super_rabbit.demo.wheel_picker_adapters.WPAMPMPickerAdapter
import com.super_rabbit.demo.wheel_picker_adapters.WPDayPickerAdapter
import com.super_rabbit.demo.wheel_picker_adapters.WPHoursPickerAdapter
import com.super_rabbit.demo.wheel_picker_adapters.WPMinutesPickerAdapter

class DateAndTimePickerFragment : androidx.fragment.app.Fragment() {

    private lateinit var binding: FragmentDateAndTimePikcerBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentDateAndTimePikcerBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.datePicker.setAdapter(WPDayPickerAdapter())
        binding.hourPicker.setAdapter(WPHoursPickerAdapter())
        binding.minutesPicker.setAdapter(WPMinutesPickerAdapter())
        binding.amPmPicker.setAdapter(WPAMPMPickerAdapter())
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            DateAndTimePickerFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}
