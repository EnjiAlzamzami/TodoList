package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoAdapter = TodoAdapter(mutableListOf())

        TodoItems.adapter = todoAdapter
        TodoItems.layoutManager=LinearLayoutManager(this)

        add.setOnClickListener {
            val todoTitle= todotitle.text.toString()
            if(todoTitle.isNotEmpty()){
                val todo = Todo(todoTitle)
                todoAdapter.addTODO(todo)
                todotitle.text.clear()
            }
        }
        delete.setOnClickListener {
            todoAdapter.deleteTODOS()
        }
    }
}