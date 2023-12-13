package com.example.captureprofessor.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface GradeDao {
    @Query("SELECT * FROM grade")
    fun getAll():List<Grade>
    @Insert
    suspend fun insertAll(grade: Grade)
    //成績評価を更新
    @Insert
    suspend fun insertEval(grade: Grade)
    //目標点数を更新
    @Insert
    suspend fun insertTarget(grade: Grade)
    //試験の点数(結果)を更新
    @Insert
    suspend fun insertResult(grade: Grade)
    @Delete
    fun deleteAll(grade: Grade)
}