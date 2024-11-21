package com.bignerdranch.android.pomodoapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bignerdranch.android.pomodoapp.databinding.FragmentNewTaskSheetBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlin.properties.ReadOnlyProperty

class NewTaskSheet : BottomSheetDialogFragment() {

    // Declare binding as nullable to handle view cleanup
    private var _binding: FragmentNewTaskSheetBinding? = null
    // Create a non-nullable property for binding
    private val binding get() = _binding!!

    // Use activityViewModels to share the ViewModel instance with the parent activity
    private val taskViewModel: TaskViewModel by activityViewModels()

    private fun activityViewModels(): ReadOnlyProperty<NewTaskSheet, TaskViewModel> {

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout and bind it
        _binding = FragmentNewTaskSheetBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Set up the save button click listener
        binding.saveButton.setOnClickListener {
            saveAction()
        }
    }

    private fun saveAction() {
        // Get the task name and description entered by the user
        val name = binding.name.text.toString().trim()
        val desc = binding.desc.text.toString().trim()

        // If name and description are not empty, update the ViewModel
        if (name.isNotEmpty() && desc.isNotEmpty()) {
            taskViewModel.updateName(name)
            taskViewModel.updateDesc(desc)
            // Clear the input fields after saving
            binding.name.setText("")
            binding.desc.setText("")
            dismiss() // Close the bottom sheet dialog
        } else {
            // Optionally, show a validation message
            binding.name.error = "Name cannot be empty"
            binding.desc.error = "Description cannot be empty"
        }
    }

    // Clean up the binding to avoid memory leaks
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
