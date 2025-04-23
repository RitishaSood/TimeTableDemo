package com.example.android.timetabledemo.timetabledemo

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.android.timetabledemo.R

class WeekActivity : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var listView: ListView

    companion object {
        lateinit var sharedPreferences: SharedPreferences
        const val SEL_DAY = "selected_day"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week)

        setupUIViews()
        initToolbar()
        setupListView()
    }

    private fun setupUIViews() {
        toolbar = findViewById(R.id.ToolbarWeek)
        listView = findViewById(R.id.lvWeek)
        sharedPreferences = getSharedPreferences("MY_DAY", MODE_PRIVATE)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Week"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupListView() {
        val week = resources.getStringArray(R.array.Week)
        val adapter = WeekAdapter(this, R.layout.activity_week_single_item, week)
        listView.adapter = adapter

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    startActivity(Intent(this@WeekActivity, DayDetail::class.java))
                    sharedPreferences.edit().putString(SEL_DAY, "Monday").apply()
                }
                1 -> {
                    startActivity(Intent(this@WeekActivity, DayDetail::class.java))
                    sharedPreferences.edit().putString(SEL_DAY, "Tuesday").apply()
                }
                2 -> {
                    startActivity(Intent(this@WeekActivity, DayDetail::class.java))
                    sharedPreferences.edit().putString(SEL_DAY, "Wednesday").apply()
                }
                3 -> {
                    startActivity(Intent(this@WeekActivity, DayDetail::class.java))
                    sharedPreferences.edit().putString(SEL_DAY, "Thursday").apply()
                }
                4 -> {
                    startActivity(Intent(this@WeekActivity, DayDetail::class.java))
                    sharedPreferences.edit().putString(SEL_DAY, "Friday").apply()
                }
                5 -> {
                    startActivity(Intent(this@WeekActivity, DayDetail::class.java))
                    sharedPreferences.edit().putString(SEL_DAY, "Saturday").apply()
                }
                else -> {}
            }
        }
    }

    inner class WeekAdapter(
        context: Context,
        private val resource: Int,
        private val week: Array<String>
    ) : ArrayAdapter<String>(context, resource, week) {

        private val layoutInflater = LayoutInflater.from(context)

        override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
            val holder: ViewHolder
            val view: View

            if (convertView == null) {
                holder = ViewHolder()
                view = layoutInflater.inflate(resource, parent, false)
                holder.ivLogo = view.findViewById(R.id.ivLetter)
                holder.tvWeek = view.findViewById(R.id.tvMain)
                view.tag = holder
            } else {
                view = convertView
                holder = view.tag as ViewHolder
            }

            holder.ivLogo?.apply {
                isOval = true
                letter = week[position][0]
            }
            holder.tvWeek?.text = week[position]

            return view
        }

        inner class ViewHolder {
            var ivLogo: LetterImageView? = null
            var tvWeek: TextView? = null
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            onBackPressed()
        }
        return super.onOptionsItemSelected(item)
    }
}