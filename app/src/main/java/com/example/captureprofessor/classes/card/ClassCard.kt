package com.example.captureprofessor.classes.card


data class ClassCard(
    val id: String, //講義番号
    val name: String, //講義名
    val description: String //説明
)

data class ClassCardWithImage(
    val id: String, //講義番号
    val name: String, //講義名
    val description: String, //説明
    val imagePass: MutableList<String> //画像のパス
)