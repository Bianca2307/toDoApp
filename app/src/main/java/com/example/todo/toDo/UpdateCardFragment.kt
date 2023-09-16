package com.example.todo.toDo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.database.ToDo
import com.example.todo.databinding.FragmentUpdateCardBinding

class UpdateCardFragment : Fragment() {

    private lateinit var binding: FragmentUpdateCardBinding
    private lateinit var viewModel: ToDoViewModel

    private lateinit var taskTitle: EditText
    private lateinit var priorityTask: EditText

    var taskID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_update_card, container, false)

        viewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
        ).get(ToDoViewModel::class.java)

        taskTitle = binding.updateTitle
        priorityTask = binding.updatePriority

        val args = arguments?.let { UpdateCardFragmentArgs.fromBundle(it) }
        val taskType = args!!.taskType

        Toast.makeText(
            context,
            "Title:${args!!.taskTitle}, Priority:${args!!.taskPriority}, ID:${args!!.taskId}",
            Toast.LENGTH_LONG
        ).show()



        if (taskType == "Edit") {

            val title = args.taskTitle
            val priority = args.taskPriority
            taskID = args.taskId
            taskTitle.setText(title)
            priorityTask.setText(priority)

        }
        binding.updateButton.setOnClickListener {
            val title = taskTitle.text.toString()
            val priority = priorityTask.text.toString()


            viewModel.update(ToDo(taskID, title, priority))
            Toast.makeText(requireContext(), "Task Updated..", Toast.LENGTH_LONG).show()

            findNavController().navigateUp()
        }

        binding.deleteButton.setOnClickListener {
            val title = taskTitle.text.toString()
            val priority = priorityTask.text.toString()
            viewModel.delete(ToDo(taskID, title, priority))
            Toast.makeText(requireContext(), "Task Deleted!", Toast.LENGTH_LONG).show()
            findNavController().navigateUp()

        }

        return binding.root
    }

}