package helpers

import TodoItems.TodoItemData
import android.content.Context
import android.content.Intent
import com.example.listadetareas.TodoAddEdit

class ActivitiesHelper {
    val openAddTodoRID = 0
    val openEditTodoRID = 1
    fun openAddTodo(ctx:Context):Intent {
        val intent = Intent(ctx, TodoAddEdit::class.java).apply {
            putExtra("TYPE","ADD")
        }
        return intent
    }

    fun openEditTodo(ctx: Context, itemData: TodoItemData): Intent {
        val intent = Intent(ctx, TodoAddEdit::class.java).apply {
            putExtra("TYPE","EDIT")
            putExtra("ID",itemData.id)
            putExtra("TITLE",itemData.tittle)
            putExtra("MESSAGE",itemData.message)
            putExtra("DATE",itemData.date)
            putExtra("IMAGE",itemData.image)
        }
        return intent
    }
}