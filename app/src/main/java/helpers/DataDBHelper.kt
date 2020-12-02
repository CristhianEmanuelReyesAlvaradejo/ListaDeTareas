package helpers

import TodoItems.TodoDB
import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DataDBHelper(ctx:Context):SQLiteOpenHelper(ctx, DATABASE_NAME, null, DATABASE_VERSION) {
    val db:SQLiteDatabase
    val values:ContentValues
    companion object{
        private val DATABASE_NAME = "todo_app"
        private val DATABASE_VERSION = 1
    }

    init {
        db = writableDatabase
        values = ContentValues()
    }

    override fun onCreate(database: SQLiteDatabase?) {
        database!!.execSQL("CREATE TABLE ${TodoDB.TodoTable.TABLE_NAME} " +
                "(" +
                    "${TodoDB.TodoTable.ID} INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    "${TodoDB.TodoTable.TITLE} TEXT NOT NULL, " +
                    "${TodoDB.TodoTable.MESSAGE} TEXT NOT NULL, " +
                    "${TodoDB.TodoTable.DATE} TEXT NOT NULL, " +
                    "${TodoDB.TodoTable.IMAGE_LINK} TEXT NOT NULL" +
                ")")
    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {
        TODO("Not yet implemented")
    }
}