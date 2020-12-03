package TodoItems

import android.content.Context
import helpers.DataDBHelper

class TodoDB {
    abstract class TodoTable() {
        companion object {
            val TABLE_NAME = "todos"
            val ID = "id"
            val TITLE = "title"
            val MESSAGE = "message"
            val DATE = "date"
            val IMAGE_LINK = "imageLink"
        }
    }

    class Controller(contexto:Context) {
        private val database:DataDBHelper
        init {
            database = DataDBHelper(contexto)
        }

        fun insertar(item:TodoItemData) {
            database.values.put(TodoTable.TITLE, item.tittle)
            database.values.put(TodoTable.MESSAGE, item.message)
            database.values.put(TodoTable.DATE, item.date)
            database.values.put(TodoTable.IMAGE_LINK, item.image)
            database.db.insert(TodoTable.TABLE_NAME, null, database.values)
        }

        fun actializar(item:TodoItemData) {
            database.values.put(TodoTable.TITLE, item.tittle)
            database.values.put(TodoTable.MESSAGE, item.message)
            database.values.put(TodoTable.DATE, item.date)
            database.values.put(TodoTable.IMAGE_LINK, item.image)
            val aux = "${TodoTable.ID} LIKE?"
            val id = arrayOf(item.id.toString())
            database.db.update(TodoTable.TABLE_NAME, database.values, aux, id)
        }

        fun delete(item:TodoItemData) {
            val aux1 = "${TodoTable.ID} LIKE?"
            val axu2 = arrayOf(item.id.toString())
            database.db.delete(TodoTable.TABLE_NAME, aux1, axu2)
        }

        fun getTodo():MutableList<TodoItemData> {
            var todoItem:MutableList<TodoItemData> = ArrayList()
            val cols = arrayOf(TodoTable.ID, TodoTable.TITLE, TodoTable.MESSAGE, TodoTable.DATE, TodoTable.IMAGE_LINK)
            val query = database.db.query(TodoTable.TABLE_NAME, cols, null, null, null, null, null)
            if(query.moveToFirst())
            {
                do {
                    todoItem.add(
                        TodoItemData(query.getInt(0), query.getString(1), query.getString(2), query.getString(3), query.getString(4))
                    )
                }while (query.moveToNext())
            }
            return todoItem
        }
    }
}