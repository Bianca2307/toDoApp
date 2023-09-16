package com.example.todo.toDo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.todo.R
import com.example.todo.database.ToDo
import com.example.todo.databinding.FragmentCreateCardBinding


class CreateCardFragment : Fragment() {

    private lateinit var binding:FragmentCreateCardBinding
    private lateinit var viewModel: ToDoViewModel

    private lateinit var taskTitle:EditText
    private lateinit var priorityTask:EditText


    var noteID = -1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_create_card, container, false)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(requireActivity().application)).get(ToDoViewModel::class.java)

        taskTitle = binding.createTitle
        priorityTask = binding.createPriority


        binding.saveButton.setOnClickListener {
            val title = taskTitle.text.toString()
            val priority = priorityTask.text.toString()


            if(title.isNotEmpty() && priority.isNotEmpty()){
                viewModel.addTask(ToDo(0,title,priority))
                Toast.makeText(requireContext(), "$title Added", Toast.LENGTH_LONG).show()
                findNavController().navigate(R.id.action_createCardFragment_to_toDoFragment2)
            }

        }



        return binding.root
    }

}