package com.example.captureprofessor.sample

import java.text.SimpleDateFormat
import java.util.Date

// レビューを格納するデータクラス
data class ReviewData(
    val enrollmentYear: Int? = 2023, // 受講年度
    val date: Date? = null, // コメント日付
    val interestLevel: Int = 5, // 授業の面白さ
    val difficultyLevel: Int = 5, // 授業の難しさ
    val comment: String = "", // コメント
)
data class LectureData(
    val lectureName: String = "", // 授業名
    val professorName: String = "", // 教授名
    val year: Int, // 開講年度
    val semester: String = "", // 開講学期
    val department: String = "", // 所属学科
    val reviews: MutableList<ReviewData> = mutableListOf(), // レビュー
)

// SimpleDateFormatを設定しておくよ！
val dateFormat = SimpleDateFormat("yyyy-MM-dd")


// Reviewを大量に宣言（ChatGPT最強！！ChatGPT最強！！）
val initialReviews = mutableListOf(
    ReviewData(
        enrollmentYear = 2023,
        date = dateFormat.parse("2023-01-15"),
        interestLevel = 4,
        difficultyLevel = 3,
        comment = "非常に面白い講義で、アルゴリズムとデータ構造について深く学ぶことができました。"
    ),
    ReviewData(
        enrollmentYear = 2022,
        date = dateFormat.parse("2022-04-20"),
        interestLevel = 3,
        difficultyLevel = 2,
        comment = "内容が濃い講義で、アルゴリズムの実装に挑戦しました。大変有益な経験でした。"
    ),
    ReviewData(
        enrollmentYear = 2021,
        date = dateFormat.parse("2021-09-05"),
        interestLevel = 5,
        difficultyLevel = 1,
        comment = "この講義は私のプログラミングスキルを飛躍的に向上させました。情熱的に学べました。"
    ),
    ReviewData(
        enrollmentYear = 2020,
        date = dateFormat.parse("2020-11-30"),
        interestLevel = 3,
        difficultyLevel = 3,
        comment = "授業内容は充実しており、アルゴリズムの理解が進みました。"
    ),
    ReviewData(
        enrollmentYear = 2019,
        date = dateFormat.parse("2019-06-15"),
        interestLevel = 4,
        difficultyLevel = 2,
        comment = "実用的なアルゴリズムの知識が得られ、将来のプログラミングプロジェクトに活かせそうです。"
    ),
    ReviewData(
        enrollmentYear = 2018,
        date = dateFormat.parse("2018-08-10"),
        interestLevel = 2,
        difficultyLevel = 4,
        comment = "内容は難しかったですが、挑戦的な授業でした。新しいスキルを獲得できました。"
    ),
    ReviewData(
        enrollmentYear = 2017,
        date = dateFormat.parse("2017-02-25"),
        interestLevel = 4,
        difficultyLevel = 3,
        comment = "講義は非常に面白く、アルゴリズムの実装に興味を持つようになりました。"
    ),
    ReviewData(
        enrollmentYear = 2016,
        date = dateFormat.parse("2016-07-08"),
        interestLevel = 3,
        difficultyLevel = 2,
        comment = "データ構造とアルゴリズムの基本を学び、プログラミングスキルが向上しました。"
    ),
    ReviewData(
        enrollmentYear = 2015,
        date = dateFormat.parse("2015-12-20"),
        interestLevel = 5,
        difficultyLevel = 1,
        comment = "最高の講義で、高度なアルゴリズムの学習を通じて成長しました。"
    ),
    ReviewData(
        enrollmentYear = 2014,
        date = dateFormat.parse("2014-03-12"),
        interestLevel = 3,
        difficultyLevel = 4,
        comment = "内容は難しかったが、問題解決スキルが向上しました。"
    )
)