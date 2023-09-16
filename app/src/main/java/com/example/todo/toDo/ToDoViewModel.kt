package com.example.todo.toDo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todo.database.ToDo
import com.example.todo.database.ToDoDatabase
import com.example.todo.repository.ToDoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ToDoViewModel(application: Application):AndroidViewModel(application) {

     val allTasks:LiveData<List<ToDo>>
     val repository:ToDoRepository


    init{
        val dao = ToDoDatabase.getDatabase(application).getToDosDao()
        repository = ToDoRepository(dao)
        allTasks = repository.allTasks
    }

    fun delete(toDo: ToDo) = viewModelScope.launch (Dispatchers.IO ){
        repository.delete(toDo)
    }

    fun deleteAll() = viewModelScope.launch(Dispatchers.IO){
        repository.deleteAll()
    }

    fun update(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO){
        repository.update(toDo)
    }

    fun addTask(toDo: ToDo) = viewModelScope.launch(Dispatchers.IO){
        repository.insert(toDo)
    }
}