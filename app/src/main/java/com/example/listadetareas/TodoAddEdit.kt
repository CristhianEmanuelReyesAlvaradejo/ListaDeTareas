package com.example.listadetareas

import TodoItems.TodoDB
import TodoItems.TodoItemData
import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main_todo_item.*
import kotlinx.android.synthetic.main.activity_todo_add_edit.*
import java.text.SimpleDateFormat
import java.util.*

class TodoAddEdit : AppCompatActivity() {
    var todoDBController:TodoDB.Controller? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo_add_edit)
        todoDBController = TodoDB.Controller(this)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        formSetUp()
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }

    fun formSetUp() {
        when(intent.getStringExtra("TYPE"))
        {
            "ADD" -> formSetUpAdd()
            "EDIT" -> formSetUpEdit()
        }

        agregarFecha.setOnClickListener{
            showDatePicker()
        }
        agregarImagen.setOnFocusChangeListener { view, isFocus ->
            val uri = agregarImagen.text.toString().trim()
            if(!isFocus && uri.isNotEmpty())
            {
                upDateImage(uri)
            }
        }

        botonCancelar.setOnClickListener {
            onBackPressed()
        }
    }

    fun formSetUpEdit() {
        setTitle("Editar Tarea")
        var id = intent?.getIntExtra("ID", -1)!!
        agregarTitulo.setText(intent.getStringExtra("TITLE"))
        editTextTextMultiLine.setText(intent.getStringExtra("MESSAGE"))
        agregarFecha.setText(intent.getStringExtra("DATE"))
        intent.getStringExtra("IMAGE")?.let {
            agregarImagen.setText(it)
            upDateImage(it) }
        botonGuardar.setOnClickListener {
            val intent = Intent().apply {
                putExtra("ID", intent.getIntExtra("ID", -1))
                putExtra("TITLE", agregarTitulo.text.toString())
                putExtra("MESSAGE", editTextTextMultiLine.text.toString())
                putExtra("DATE", agregarFecha.text.toString())
                putExtra("IMAGE", agregarImagen.text.toString())
            }
            todoDBController!!.actializar(TodoItemData(id, agregarTitulo.text.toString(), editTextTextMultiLine.text.toString(), agregarFecha.text.toString(), agregarImagen.text.toString()))
            setResult(Activity.RESULT_OK, intent)
            onBackPressed()
        }
    }

    fun formSetUpAdd() {
        setTitle("Agregar Tarea")
        botonGuardar.setOnClickListener {
            todoDBController!!.insertar(TodoItemData(0, agregarTitulo.text.toString(), editTextTextMultiLine.text.toString(), agregarFecha.text.toString(), agregarImagen.text.toString()))
            setResult(Activity.RESULT_OK, intent)
            onBackPressed()
        }
    }

    fun showDatePicker() {
        val formatDate = SimpleDateFormat("dd/MM/YYYY", Locale.US)
        val getDate = Calendar.getInstance()
        val datePicker = DatePickerDialog(this, android.R.style.Theme_Holo_Light_Dialog_MinWidth, DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
            val selectDate = Calendar.getInstance()
            selectDate.set(Calendar.YEAR, year)
            selectDate.set(Calendar.MONTH, month)
            selectDate.set(Calendar.DAY_OF_MONTH, day)
            val date = formatDate.format(selectDate.time)
            agregarFecha.setText(date)
        }, getDate.get((Calendar.YEAR)), getDate.get(Calendar.MONTH), getDate.get(Calendar.DAY_OF_MONTH))
        datePicker.show()
    }

    fun upDateImage(uri:String) {
        Picasso.get().load(uri).into(imagenPreview)
    }
}