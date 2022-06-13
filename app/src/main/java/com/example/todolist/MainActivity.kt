package com.example.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
//import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var todoAdapter: TodoAdapter
    private lateinit var rec: RecyclerView
    private lateinit var add: Button
    private lateinit var delete: Button
    private lateinit var todotitle: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rec=findViewById(R.id.TodoItems)
        add=findViewById(R.id.add)
        delete=findViewById(R.id.delete)
        todotitle=findViewById(R.id.todotitle)

        todoAdapter = TodoAdapter(mutableListOf())

        rec.adapter=todoAdapter
        rec.layoutManager=LinearLayoutManager(this)

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