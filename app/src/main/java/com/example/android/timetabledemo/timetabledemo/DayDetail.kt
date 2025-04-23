package com.example.android.timetabledemo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.android.timetabledemo.Utils.LetterImageView
import com.example.android.timetabledemo.databinding.ActivityDayDetailBinding

class DayDetail : AppCompatActivity() {

    private lateinit var binding: ActivityDayDetailBinding
    private lateinit var preferredDay: Array<String>
    private lateinit var preferredTime: Array<String>

    companion object {
        var Monday: Array<String> = emptyArray()
        var Tuesday: Array<String> = emptyArray()
        var Wednesday: Array<String> = emptyArray()
        var Thursday: Array<String> = emptyArray()
        var Friday: Array<String> = emptyArray()
        var Saturday: Array<String> = emptyArray()
        var Time1: Array<String> = emptyArray()
        var Time2: Array<String> = emptyArray()
        var Time3: Array<String> = emptyArray()
        var Time4: Array<String> = emptyArray()
        var Time5: Array<String> = emptyArray()
        var Time6: Array<String> = emptyArray()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDayDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupUIViews()
        initToolbar()
        setupListView()
    }

    private fun setupUIViews() {
        // View binding handles view references
    }

    private fun initToolbar() {
        setSupportActionBar(binding.toolbarDayDetail)
        supportActionBar?.apply {
            title = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null)
            setDisplayHomeAsUpEnabled(true)
        }
    }

    private fun setupListView() {
        // Initialize arrays
        Monday = resources.getStringArray(R.array.Monday)
        Tuesday = resources.getStringArray(R.array.Tuesday)
        Wednesday = resources.getStringArray(R.array.Wednesday)
        Thursday = resources.getStringArray(R.array.Thursday)
        Friday = resources.getStringArray(R.array.Friday)
        Saturday = resources.getStringArray(R.array.Saturday)

        Time1 = resources.getStringArray(R.array.time1)
        Time2 = resources.getStringArray(R.array.time2)
        Time3 = resources.getStringArray(R.array.time3)
        Time4 = resources.getStringArray(R.array.time4)
        Time5 = resources.getStringArray(R.array.time5)
        Time6 = resources.getStringArray(R.array.time6)

        // Determine which day to show
        val selectedDay = WeekActivity.sharedPreferences.getString(WeekActivity.SEL_DAY, null) ?: "Monday"

        preferredDay = when (selectedDay.lowercase()) {
            "monday" -> Monday
            "tuesday" -> Tuesday
            "wednesday" -> Wednesday
            "thursday" -> Thursday
            "friday" -> Friday
            else -> Saturday
        }

        preferredTime = when (selectedDay.lowercase()) {
            "monday" -> Time1
            "tuesday" -> Time2
            "wednesday" -> Time3
            "thursday" -> Time4
            "friday" -> Time5
            else -> Time6
        }

        binding.lvDayDetail.adapter = DayDetailAdapter(this, preferredDay, preferredTime)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }

    private inner class DayDetailAdapter(
        context: Context,
        private val subjectArray: Array<String>,
        private val timeArray: Array<String>
    ) : BaseAdapter() {

        private val inflater: LayoutInflater = LayoutInflater.from(context)

        override fun getCount(): Int = subjectArray.size

        override fun getItem(position: Int): Any = subjectArray[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view: View = convertView ?: inflater.inflate(R.layout.day_detail_single_item, parent, false)

            view.findViewById<TextView>(R.id.tvSubjectDayDetails).text = subjectArray[position]
            view.findViewById<TextView>(R.id.tvTimeDayDetail).text = timeArray[position]

            view.findViewById<LetterImageView>(R.id.ivDayDetails).apply {
                isOval = true
                setLetter(subjectArray[position].first())
            }

            return view
        }
    }
}