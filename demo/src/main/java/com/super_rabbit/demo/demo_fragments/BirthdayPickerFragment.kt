package com.super_rabbit.demo.demo_fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.super_rabbit.demo.databinding.FragmentBirthdayPickerBinding
import com.super_rabbit.wheel_picker.DayAdapter
import com.super_rabbit.wheel_picker.MonthAdapter
import com.super_rabbit.wheel_picker.OnValueChangeListener
import com.super_rabbit.wheel_picker.WheelPicker
import java.util.Calendar
import java.util.GregorianCalendar

class BirthdayPickerFragment : androidx.fragment.app.Fragment() {

    val TAG = BirthdayPickerFragment::class.java.simpleName

    private lateinit var binding: FragmentBirthdayPickerBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = FragmentBirthdayPickerBinding.inflate(inflater, container, false)
        return binding.root
    }

    private val monthAdapter = MonthAdapter()
    private var dayAdapter = DayAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.day.setAdapter(dayAdapter)
        binding.month.setAdapter(monthAdapter)

        val birthday = with(Calendar.getInstance()) {
            Birthday(get(Calendar.DAY_OF_MONTH), get(Calendar.MONTH), get(Calendar.YEAR).minus(18))
        }

        binding.day.scrollToValue(birthday.day.toString())
        binding.month.scrollToValue(monthAdapter.getValue(birthday.month))
        binding.year.scrollToValue(birthday.year.toString())

        Log.v(
            TAG,
            "0 = ${monthAdapter.getValue(0)}  11 = ${monthAdapter.getValue(11)} January = ${
                monthAdapter.getPosition("January")
            }  December = ${monthAdapter.getPosition("December")} "
        )

        binding.day.onValueChange { _, _, newValue ->
            Log.v(TAG, "day=$newValue")
        }

        binding.month.onValueChange { _, _, newValue ->
            clampDaysByMonth()
            Log.v(TAG, "month=$newValue")
        }

        binding.year.onValueChange { _, _, newValue ->
            clampDaysByMonth()
            Log.v(TAG, "year=$newValue")
        }
    }

    private fun clampDaysByMonth() {
        val selectedDay = binding.day.getCurrentItem()

        val daysInMonth = GregorianCalendar(
            binding.year.getCurrentItem().toInt(),
            monthAdapter.getPosition(binding.month.getCurrentItem()),
            1
        )
            .getActualMaximum(Calendar.DAY_OF_MONTH)

        dayAdapter.days.clear()
        dayAdapter.days.addAll((1..daysInMonth).toMutableList())
        dayAdapter.notifyDataSetChanged()

        binding.day.setMaxValue(dayAdapter.getMaxIndex())

        Log.v(
            TAG,
            "scroll to selectedDay=$selectedDay daysInMonth=$daysInMonth dayAdapter=${dayAdapter.getMaxIndex()}"
        )

        binding.day.setValue(selectedDay)
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            BirthdayPickerFragment().apply {
                arguments = Bundle().apply {}
            }
    }
}


fun Int.clamp(min: Int, max: Int): Int {
    if (this < min) return min
    return if (this > max) max else this
}

data class Birthday(
    val day: Int,
    /**
     * Note: starts at 1, e.g. January is 1, December is 12
     */
    val month: Int,
    val year: Int
)


open class SimpleOnValueChangeListener : OnValueChangeListener {
    override fun onValueChange(picker: WheelPicker, oldVal: String, newVal: String) {
    }
}

inline fun WheelPicker.onValueChange(crossinline block: (picker: WheelPicker, oldValue: String, newValue: String) -> Unit) {
    setOnValueChangedListener(object : SimpleOnValueChangeListener() {
        override fun onValueChange(picker: WheelPicker, oldVal: String, newVal: String) =
            block(picker, oldVal, newVal)
    })
}