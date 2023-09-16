package com.example.todo.toDo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.database.ToDo
import com.example.todo.databinding.FragmentToDoBinding


class ToDoFragment : Fragment(), TaskClickInterface {

    private lateinit var recyclerView: RecyclerView
    private lateinit var binding:FragmentToDoBinding
    private lateinit var viewModel: ToDoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_to_do, container, false)
        viewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(requireActivity().application)).get(ToDoViewModel::class.java)

        binding.lifecycleOwner = this

        recyclerView = binding.recyclerView
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val todoAdapter = Adapter(this,)
        recyclerView.adapter = todoAdapter

        viewModel.allTasks.observe(this, Observer{ list ->
           list?.let {
               todoAdapter.updateList(it)
           }
        })

        binding.deleteAll.setOnClickListener {
           viewModel.deleteAll()
        }

        binding.add.setOnClickListener { view:View ->
            view.findNavController().navigate(R.id.action_toDoFragment2_to_createCardFragment)
        }



        // Inflate the layout for this fragment
        return binding.root
    }



    override fun onTaskClick(toDo: ToDo) {
        var taskTitle = toDo.title
        var taskPriority = toDo.priority
        var taskId = toDo.id
        var taskType = "Edit"

        val action = ToDoFragmentDirections.actionToDoFragment2ToUpdateCardFragment(taskTitle,taskPriority,taskId, taskType)
        findNavController().navigate(action)
    }
}