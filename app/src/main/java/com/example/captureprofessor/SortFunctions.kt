package com.example.captureprofessor

import com.example.captureprofessor.ui.Review


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
