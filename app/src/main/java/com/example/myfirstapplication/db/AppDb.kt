package com.example.myfirstapplication.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase

class AppDb private constructor(db: SQLiteDatabase) {
    val postDao: PostDao = PostDaoImpl(db)

    companion object {
        @Volatile
        private var instance: AppDb? = null

        fun getInstance(context: Context): AppDb {
            return instance ?: synchronized(this) {
                instance ?: AppDb(buildDatabase(context, arrayOf(PostDaoImpl.DDL)))
            }
        }

        private fun buildDatabase(context: Context, DDLs: Array<String>) = DbHelper(
            context, 1, "KotlinAndroidDB.db", DDLs
        ).writableDatabase
    }
}