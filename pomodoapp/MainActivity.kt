package com.bignerdranch.android.pomodoapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.bignerdranch.android.pomodoapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var taskViewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the view binding layout
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialize ViewModel
        taskViewModel = ViewModelProvider(this).get(TaskViewModel::class.java)

        // Button click listener to show new task dialog
        binding.newTaskButton.setOnClickListener {
            NewTaskSheet().show(supportFragmentManager, "newTaskTag")
        }

        // Observe LiveData for task name
        taskViewModel.name.observe(this) { name ->
            binding.taskName.text = String.format("Task Name: %s", name)
        }

        // Observe LiveData for task description
        taskViewModel.desc.observe(this) { desc ->
            binding.taskDesc.text = String.format("Task Desc: %s", desc)
        }
    }
}
