package com.example.todo.toDo

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.todo.R
import com.example.todo.database.ToDo

class Adapter(
    private val taskClickInterface:TaskClickInterface
) : RecyclerView.Adapter<Adapter.ViewHolder>() {

    private val allTasks = ArrayList<ToDo>()


    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val titleTask: TextView = itemView.findViewById(R.id.title)
        val priorityTask:TextView = itemView.findViewById(R.id.priority)
        var layout:View = itemView.findViewById(R.id.my_layout) }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return allTasks.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val todo = allTasks[position]

        when(todo.priority.toLowerCase()){
            "high" -> holder.layout.setBackgroundColor(Color.parseColor("#F05454"))
            "medium" -> holder.layout.setBackgroundColor(Color.parseColor("#EDC988"))
            else -> holder.layout.setBackgroundColor(Color.parseColor("#00917C"))
        }



        holder.titleTask.text = todo.title
        holder.priorityTask.text = todo.priority


        holder.itemView.setOnClickListener{
            taskClickInterface.onTaskClick(todo)
        }


    }

    fun updateList(newList:List<ToDo>){

        allTasks.clear()
        allTasks.addAll(newList)
        notifyDataSetChanged()
    }
}



interface TaskClickInterface {

    fun onTaskClick(toDo: ToDo)
}
