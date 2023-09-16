package com.example.todo.repository

import androidx.lifecycle.LiveData
import com.example.todo.database.ToDo
import com.example.todo.database.ToDosDao

class ToDoRepository(private val toDosDao: ToDosDao) {

    val allTasks:LiveData<List<ToDo>> = toDosDao.getTasks()


    suspend fun insert(toDo: ToDo){
        toDosDao.insertTask(toDo)
    }


    suspend fun delete(toDo: ToDo){
        toDosDao.deleteTask(toDo)
    }

    suspend fun deleteAll(){
        toDosDao.deleteAll()
    }

    suspend fun update(toDo: ToDo){
        toDosDao.updateTask(toDo)
    }
}