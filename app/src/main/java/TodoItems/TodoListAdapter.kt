package TodoItems

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.listadetareas.R
import com.squareup.picasso.Picasso
import helpers.ActivitiesHelper
import kotlinx.android.synthetic.main.activity_main_todo_item.view.*
import javax.xml.transform.Templates

class TodoListAdapter (var items: MutableList<TodoItemData>, val ctx:Context, val onClickEdit:(item:TodoItemData)->Unit, val onClickDelete:(item: TodoItemData) -> Unit): RecyclerView.Adapter<TodoListAdapter.TodoHolder>(){
    class TodoHolder(val itemTemplates:View): RecyclerView.ViewHolder(itemTemplates){
        fun render(iten:TodoItemData, ctx:Context, onClickEdit:(item:TodoItemData)->Unit, onClickDelete:(item: TodoItemData) -> Unit){
            itemTemplates.titulo.text = iten.tittle
            itemTemplates.mensaje.text = iten.message
            itemTemplates.fecha.text = iten.date
            Picasso.get().load(iten.image).into(itemTemplates.Imagen)
            itemTemplates.botonEditar.setOnClickListener {
               onClickEdit(iten)
            }
            itemTemplates.botonListo.setOnClickListener {
                onClickDelete(iten)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TodoHolder(layoutInflater.inflate(R.layout.activity_main_todo_item, parent, false))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TodoHolder, position: Int) {
        holder.render(items[position], ctx, onClickEdit, onClickDelete)
    }
}