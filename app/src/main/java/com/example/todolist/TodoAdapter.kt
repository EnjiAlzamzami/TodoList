package com.example.todolist

import android.graphics.Paint.STRIKE_THRU_TEXT_FLAG
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.todo_items.view.*

class TodoAdapter (
    //TodoList
    private val todos: MutableList<Todo>
    ) : RecyclerView.Adapter<TodoAdapter.TodoView>(){

    class TodoView(itemView:View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoView {
      return TodoView(
          LayoutInflater.from(parent.context).inflate(R.layout.todo_items, parent, false)
      )
    }

    fun addTODO(todo: Todo){
        todos.add(todo)
        notifyItemInserted(todos.size-1)
    }

    fun deleteTODOS(){
        todos.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView , isChecked: Boolean){
       if(isChecked){
           tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or STRIKE_THRU_TEXT_FLAG
       } else{
           tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and STRIKE_THRU_TEXT_FLAG.inv()
       }
    }

    override fun onBindViewHolder(holder: TodoView, position: Int) {
       val currentTodo=todos[position]
       holder.itemView.apply {
           tvTodoTitle.text=currentTodo.title
           checkDone.isChecked=currentTodo.isChecked
           toggleStrikeThrough(tvTodoTitle,currentTodo.isChecked)
           checkDone.setOnCheckedChangeListener { _, isChecked ->
               toggleStrikeThrough(tvTodoTitle,isChecked)
               currentTodo.isChecked = !currentTodo.isChecked

           }
       }
    }

    override fun getItemCount(): Int {
      return todos.size
    }


}