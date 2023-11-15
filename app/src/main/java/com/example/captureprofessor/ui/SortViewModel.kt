package com.example.captureprofessor.ui

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.captureprofessor.sample.ReviewData

// ソート順を保存するViewModel
class SortViewModel() : ViewModel() {
    val selectedSortOption = mutableStateOf("受講年度順")

}
class ButtonScreenViewModel() : ViewModel() {
    val myTextState = mutableStateOf("タップ可能")
    val isTappedState = mutableStateOf(false)


    // enrollmentYearが遅い順にソート
    fun sortReviewByYear(initialReviews: MutableList<ReviewData>): MutableList<ReviewData> {
        val sortedReviews = initialReviews.sortedByDescending { it.enrollmentYear }
        val reviews = sortedReviews.toMutableList()
        return reviews
    }

    // interestLevelが大きい順にソート
    fun sortReviewByInterest(initialReviews: MutableList<ReviewData>): MutableList<ReviewData> {
        val sortedReviews = initialReviews.sortedByDescending { it.interestLevel }
        val reviews = sortedReviews.toMutableList()
        return reviews
    }

    // difficultyLevelが大きい順にソート
    fun sortReviewByDifficulty(initialReviews: MutableList<ReviewData>): MutableList<ReviewData> {
        val sortedReviews = initialReviews.sortedByDescending { it.difficultyLevel }
        val reviews = sortedReviews.toMutableList()
        return reviews
    }
}