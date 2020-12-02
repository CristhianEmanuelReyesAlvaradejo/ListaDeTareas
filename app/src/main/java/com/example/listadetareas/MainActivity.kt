package com.example.listadetareas

import TodoItems.TodoDB
import TodoItems.TodoItemData
import TodoItems.TodoListAdapter
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import helpers.ActivitiesHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var todoItems:MutableList<TodoItemData> = ArrayList()
    var adapter:TodoListAdapter? = null
    var todoDBController:TodoDB.Controller? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        todoDBController = TodoDB.Controller(this)
        this.setTitle("Lista de tareas")
        Agregar.setOnClickListener {
            startActivityForResult(ActivitiesHelper().openAddTodo(this), ActivitiesHelper().openAddTodoRID)
        }
        getTodos()
        initTodoRecycler()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == Activity.RESULT_OK)
        {
            getTodos()
            initTodoRecycler()
            adapter?.notifyDataSetChanged()
            super.onActivityResult(requestCode, resultCode, data)
        }else {

        }
    }

    fun editar(item: TodoItemData){
        startActivityForResult(ActivitiesHelper().openEditTodo(this, item), ActivitiesHelper().openEditTodoRID)
    }

    fun delete(item: TodoItemData) {
        todoDBController!!.delete(item)
        getTodos()
        initTodoRecycler()
    }

    fun getTodos() {
        todoItems = todoDBController!!.getTodo()
    }

    fun initTodoRecycler() {
        adapter = TodoListAdapter(todoItems, this, ::editar, ::delete)
        Lista.layoutManager = LinearLayoutManager(this)
        Lista.adapter =  adapter
    }
}