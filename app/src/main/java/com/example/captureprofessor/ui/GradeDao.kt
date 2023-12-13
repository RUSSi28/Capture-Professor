package com.example.captureprofessor.ui

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface GradeDao {
    @Query("SELECT * FROM grade")
    fun getAll():List<Grade>
    @Insert
    suspend fun insertAll(grade:Grade)
    //成績評価を更新
    @Insert
    suspend fun inserteval(grade: Grade)
    //目標点数を更新
    @Insert
    suspend fun inserttarget(grade: Grade)
    //試験の点数(結果)を更新
    @Insert
    suspend fun insertresult(grade:Grade)
    @Delete
    fun deleteAll(grade:Grade)
}