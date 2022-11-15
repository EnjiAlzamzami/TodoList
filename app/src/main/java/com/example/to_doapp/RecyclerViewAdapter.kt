package com.example.to_doapp

import android.graphics.Color
import android.graphics.Paint
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import android.view.ViewGroup
import android.view.LayoutInflater
import android.widget.CheckBox
import android.widget.TextView
import kotlinx.android.synthetic.main.todo_items.view.*

class RecyclerViewAdapter(private val items: ArrayList<Todo>
) : RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder>(){

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.todo_items,
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentTodo=items[position]
        holder.itemView.apply {
            tvTodoTitle.text=currentTodo.title
            checkDone.isChecked=currentTodo.isChecked
            //I prefer it in this that :)
            toggleStrikeThrough(tvTodoTitle,currentTodo.isChecked)

            checkDone.setOnCheckedChangeListener { _, isChecked ->
             //toggleStrikeThrough(tvTodoTitle,isChecked)
                    currentTodo.isChecked = !currentTodo.isChecked
            }
        }
    }
    fun addTODO(todo: Todo){
        items.add(todo)
        notifyItemInserted(items.size-1)
    }

    fun deleteTODOS(){
        items.removeAll { todo ->
            todo.isChecked
        }
        notifyDataSetChanged()
    }

    private fun toggleStrikeThrough(tvTodoTitle: TextView , isChecked: Boolean){
        if(isChecked){
            tvTodoTitle.setTextColor(Color.GRAY)
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        } else{
            tvTodoTitle.setTextColor(Color.BLACK)
            tvTodoTitle.paintFlags = tvTodoTitle.paintFlags and Paint.STRIKE_THRU_TEXT_FLAG.inv()
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}


