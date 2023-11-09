package com.example.captureprofessor

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

// ソート順を保存するViewModel
class SortViewModel() : ViewModel() {
    val selectedSortOption = mutableStateOf("受講年度順")

}
class ButtonScreenViewModel() : ViewModel() {
    val myTextState = mutableStateOf("タップ可能")
    val isTappedState = mutableStateOf(false)
}