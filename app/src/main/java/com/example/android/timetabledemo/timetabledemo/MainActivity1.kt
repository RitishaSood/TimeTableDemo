package com.example.android.timetabledemo.timetabledemo

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.android.timetabledemo.R

class MainActivity1 : AppCompatActivity() {

    private lateinit var toolbar: Toolbar
    private lateinit var listView: ListView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main1)

        setupUIViews()
        initToolbar()
        setupListView()
    }

    private fun setupUIViews() {
        toolbar = findViewById(R.id.ToolbarMain)
        listView = findViewById(R.id.lvMain)
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Timetable App"
    }

    private fun setupListView() {
        val titles = resources.getStringArray(R.array.Main)
        val descriptions = resources.getStringArray(R.array.Description)

        listView.adapter = SimpleAdapter(this, titles, descriptions)

        listView.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                // Add cases later when WeekActivity is ready
                0 -> { /* Will implement later */ }
                1 -> { /* Will implement later */ }
                2 -> { /* Will implement later */ }
                3 -> { /* Will implement later */ }
            }
        }
    }

    private inner class SimpleAdapter(
        context: Context,
        private val titles: Array<String>,
        private val descriptions: Array<String>
    ) : BaseAdapter() {

        private val inflater: LayoutInflater = LayoutInflater.from(context)

        override fun getCount(): Int = titles.size

        override fun getItem(position: Int): Any = titles[position]

        override fun getItemId(position: Int): Long = position.toLong()

        override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
            val view = convertView ?: inflater.inflate(R.layout.main_activity_single_item, parent, false)

            view.findViewById<TextView>(R.id.tvMain).text = titles[position]
            view.findViewById<TextView>(R.id.tvDescription).text = descriptions[position]

            val imageRes = when (titles[position].lowercase()) {
                "timetable" -> R.drawable.timetable
                "subjects" -> R.drawable.book
                "faculty" -> R.drawable.contact
                else -> R.drawable.settings
            }
            view.findViewById<ImageView>(R.id.ivMain).setImageResource(imageRes)

            return view
        }
    }
}