package com.super_rabbit.demo

import android.graphics.Typeface
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.super_rabbit.demo.databinding.ActivityNormalNumberPickerBinding
import com.super_rabbit.demo.wheel_picker_adapters.WPDayPickerAdapter

class DemoActivity : AppCompatActivity() {
    private var mIsRoundedWrapPreferred = false
    private var mWheelItemCount = 5
    private var mCurSelectedTextColor = R.color.color_4_blue
    private var mIsDayPicker = false
    private var currentTypeFace = Typeface.SERIF

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNormalNumberPickerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //Set rounded wrap enable
        binding.numberPicker.setWrapSelectorWheel(true)
        //Set wheel item count
        binding.numberPicker.setWheelItemCount(5)
        //Set wheel max index
        binding.numberPicker.setMaxValue(1000)
        //Set wheel min index
        binding.numberPicker.setMinValue(-1000)
        //Set selected text color
        binding.numberPicker.setSelectedTextColor(R.color.color_4_blue)
        //Set unselected text color
        binding.numberPicker.setUnselectedTextColor(R.color.color_3_dark_blue)
        //Set user defined adapter
        binding.numberPicker.setAdapter(WPDayPickerAdapter())

        // OnValueChangeListener
        val context = this
//        binding.numberPicker.setOnValueChangeListener(object : OnValueChangeListener{
//            override fun onValueChange(picker: WheelPicker, oldVal: String, newVal: String) {
//                val out = String.format("Current: %s", newVal)
//                Toast.makeText(context, out, Toast.LENGTH_SHORT).show()
//            }
//        })

        binding.setWrap.setOnClickListener {
            mIsRoundedWrapPreferred = !mIsRoundedWrapPreferred
            binding.numberPicker.setWrapSelectorWheel(mIsRoundedWrapPreferred)
            binding.setWrap.text =
                String.format("Set wrap (current = %s)", mIsRoundedWrapPreferred.toString())
            binding.numberPicker.reset()
        }

        binding.setWheelItemCount.setOnClickListener {
            mWheelItemCount = if (mWheelItemCount == 5) {
                3
            } else {
                5
            }
            binding.numberPicker.setWheelItemCount(mWheelItemCount)
            binding.setWheelItemCount.text =
                String.format("Set wheel item count (current = %s)", mWheelItemCount.toString())
        }

        binding.setSelectedColor.setOnClickListener {
            if (mCurSelectedTextColor == R.color.color_4_blue) {
                mCurSelectedTextColor = R.color.color_7_yellow
                binding.setSelectedColor.text =
                    String.format("set selected color (current = yellow)")
            } else {
                mCurSelectedTextColor = R.color.color_4_blue
                binding.setSelectedColor.text = String.format("set selected color (current = blue)")
            }
            binding.numberPicker.setSelectedTextColor(mCurSelectedTextColor)
        }
        binding.setStyle.setOnClickListener {
            mIsDayPicker = !mIsDayPicker
            if (mIsDayPicker) {
                binding.setStyle.text = "set picker style (current = day picker)"
                binding.numberPicker.setAdapter(WPDayPickerAdapter())
                binding.numberPicker.requestLayout()
            } else {
                binding.setStyle.text = "set picker style (current = normal number picker)"
                binding.numberPicker.setAdapter(null)
                binding.numberPicker.setMaxValue(9)
                binding.numberPicker.setMinValue(0)
                binding.numberPicker.reset()
            }
        }
        binding.setFont.setOnClickListener {
            currentTypeFace = if (currentTypeFace == Typeface.SERIF) {
                binding.setFont.text = "set picker typeface (current = aguafina)"
                ResourcesCompat.getFont(this@DemoActivity, R.font.aguafina_script)
            } else {
                binding.setFont.text = "set picker typeface (current = serif)"
                Typeface.SERIF
            }

            binding.numberPicker.setTypeface(currentTypeFace)
            binding.numberPicker.reset()
        }
    }
}
