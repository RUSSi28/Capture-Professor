package com.example.captureprofessor.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Grade::class],version=1)
abstract class GradeDatabase:RoomDatabase() {
    abstract fun gradeDao(): GradeDao

    companion object{
        private const val DB_NAME = "grade_db"
        @Volatile
        private var INSTANCE: GradeDatabase?=null
            fun getDatabase(context: Context): GradeDatabase {
                return INSTANCE ?: synchronized(this) {
                    val instance = Room.databaseBuilder(
                        context,
                        GradeDatabase::class.java,
                        DB_NAME
                    )
                        .build()
                    INSTANCE = instance
                    instance

            }
        }
    }
}