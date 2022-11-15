package com.example.to_doapp

import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var items:ArrayList<Todo>
    private lateinit var todoAdapter: RecyclerViewAdapter
    private lateinit var myRV:RecyclerView
    private lateinit var add:FloatingActionButton
    private lateinit var myLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        add=findViewById(R.id.addbutton)
        myRV=findViewById(R.id.myrv)
       // myLayout=findViewById(R.id.mylayout)

        items= arrayListOf()
        todoAdapter = RecyclerViewAdapter(items)

        myRV.adapter=todoAdapter
        myRV.layoutManager=LinearLayoutManager(this)

        add.setOnClickListener{
            customDialog()
        }


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        var itemsDeleted = 0
        for(i in items){
            if(i.isChecked){
                itemsDeleted++
            }
        }
        if(itemsDeleted > 0){
            Toast.makeText(this, "$itemsDeleted items deleted", Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this, "No items selected", Toast.LENGTH_LONG).show()
        }
        todoAdapter.deleteTODOS()
        return super.onOptionsItemSelected(item)
    }

    private fun customDialog(){
        val dialogBuilder = AlertDialog.Builder(this)

        val layout = LinearLayout(this)
        layout.orientation = LinearLayout.VERTICAL

        val todotxt = EditText(this)
        todotxt.hint = "Enter to-do item"
        layout.addView(todotxt)

        dialogBuilder.setPositiveButton("Add", DialogInterface.OnClickListener {
                dialog, id ->
            items.add(Todo(todotxt.text.toString()))
        })
            .setNegativeButton("Cancel", DialogInterface.OnClickListener {
                    dialog, id ->
                dialog.cancel()
            })

        val alert = dialogBuilder.create()
        alert.setTitle("New Item")
        alert.setView(layout)
        alert.show()
    }
}
