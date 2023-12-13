package com.example.captureprofessor.ui

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("grade")
data class Grade(
    //授業コード
    @PrimaryKey var code: String,
    //授業名
    var name: String,
    //成績評価
    var eval: String,
    //目標点数
    var target:Int,
    //結果
    var result:Int
)
