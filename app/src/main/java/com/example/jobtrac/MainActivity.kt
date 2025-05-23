package com.example.jobtrac

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jobtrac.viewmodel.JobViewModel
import com.example.jobtrac.model.JobItem
import com.example.jobtrac.ui.JobAdapter
import android.content.Intent
import android.widget.Button



class MainActivity : AppCompatActivity() {
    private val viewModel: JobViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)

        // 🔧 Use 1 column to show one job card per row
        recyclerView.layoutManager = GridLayoutManager(this, 1)

        // 🔧 Add padding to avoid edge contact
        recyclerView.setPadding(16, 16, 16, 16)
        recyclerView.clipToPadding = false

        viewModel.jobs.observe(this) { jobs: List<JobItem> ->
            recyclerView.adapter = JobAdapter(jobs)
        }

        viewModel.fetchJobs()

        findViewById<com.google.android.material.floatingactionbutton.FloatingActionButton>(R.id.fabAddApplication).setOnClickListener {
            val intent = Intent(this, FormActivity::class.java)
            startActivity(intent)
        }

        findViewById<Button>(R.id.btnViewSubmissions).setOnClickListener {
            startActivity(Intent(this, SubmissionListActivity::class.java))
        }


    }
}
