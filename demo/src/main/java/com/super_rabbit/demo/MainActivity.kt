package com.super_rabbit.demo

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.super_rabbit.demo.demo_fragments.BirthdayPickerFragment
import com.super_rabbit.demo.demo_fragments.DateAndTimePickerFragment
import com.super_rabbit.demo.demo_fragments.UnlimitedNumberPickerDemo
import com.super_rabbit.demo.demo_fragments.WeekDayPickerFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(BirthdayPickerFragment.newInstance())
    }

    fun onClick(v: View) {
        val intent: Intent
        when (v.id) {
            R.id.show_style_demo -> {
                intent = Intent(this@MainActivity, DemoActivity::class.java)
                startActivity(intent)
            }
            R.id.show_unlimited_number_picker -> replaceFragment(UnlimitedNumberPickerDemo.newInstance())
            R.id.show_week_day_picker -> replaceFragment(WeekDayPickerFragment.newInstance())
            R.id.show_day_picker -> replaceFragment(DateAndTimePickerFragment.newInstance())
            R.id.show_birthday_day_picker -> replaceFragment(BirthdayPickerFragment.newInstance())
            else -> {}
        }
    }

    private fun replaceFragment(fragment: Fragment) {
        val fm = supportFragmentManager
        val ft = fm.beginTransaction()
        ft.replace(R.id.fragment_container, fragment)
        ft.commit()
    }
}
