package com.example.captureprofessor.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class GradeViewModel:ViewModel() {
    //roomから持ってくるように修正
    var name by mutableStateOf("")
    var eval by mutableStateOf("")
    var target by mutableStateOf(0)
    var result by mutableStateOf(0)
}