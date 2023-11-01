package com.example.captureprofessor

import java.util.Date

// レビューを格納するデータクラス
data class ReviewData(
    val enrollmentYear: Int? = 2023, // 受講年度
    val date: Date? = null, // コメント日付
    val interestLevel: Int = 5, // 授業の面白さ
    val difficultyLevel: Int = 5, // 授業の難しさ
    val comment: String = "", // コメント
)