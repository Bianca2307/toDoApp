package com.example.todo.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update


@Dao
interface ToDosDao {

    //method to insert to do
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTask(toDo: ToDo)

    //method to delete one to do
    @Delete
    suspend fun deleteTask(toDo: ToDo)

    @Query("SELECT * FROM toDoTable ORDER BY id ASC")
    fun getTasks():LiveData<List<ToDo>>

    //Delete all
    @Query("DELETE  FROM toDoTable")
    suspend fun deleteAll()

    @Update
    suspend fun updateTask(toDo: ToDo)


}