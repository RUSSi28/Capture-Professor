package com.example.captureprofessor

import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date

// レビューを格納するデータクラス
data class Review(
    val enrollmentYear: Int, // 受講年度
    val date: Date, // コメント日付
    val interestLevel: Int, // 授業の面白さ
    val difficultyLevel: Int, // 授業の難しさ
    val comment: String, // コメント
)

// SimpleDateFormatを設定しておくよ！
val dateFormat = SimpleDateFormat("yyyy-MM-dd")

@Composable
fun ReviewActivity() {
    val TAG = "ReviewCard"

    // Reviewを大量に宣言（ChatGPT最強！！ChatGPT最強！！）
    val initialReviews = mutableListOf(
        Review(
            enrollmentYear = 2023,
            date = dateFormat.parse("2023-01-15"),
            interestLevel = 4,
            difficultyLevel = 3,
            comment = "非常に面白い講義で、アルゴリズムとデータ構造について深く学ぶことができました。"
        ),
        Review(
            enrollmentYear = 2022,
            date = dateFormat.parse("2022-04-20"),
            interestLevel = 3,
            difficultyLevel = 2,
            comment = "内容が濃い講義で、アルゴリズムの実装に挑戦しました。大変有益な経験でした。"
        ),
        Review(
            enrollmentYear = 2021,
            date = dateFormat.parse("2021-09-05"),
            interestLevel = 5,
            difficultyLevel = 1,
            comment = "この講義は私のプログラミングスキルを飛躍的に向上させました。情熱的に学べました。"
        ),
        Review(
            enrollmentYear = 2020,
            date = dateFormat.parse("2020-11-30"),
            interestLevel = 3,
            difficultyLevel = 3,
            comment = "授業内容は充実しており、アルゴリズムの理解が進みました。"
        ),
        Review(
            enrollmentYear = 2019,
            date = dateFormat.parse("2019-06-15"),
            interestLevel = 4,
            difficultyLevel = 2,
            comment = "実用的なアルゴリズムの知識が得られ、将来のプログラミングプロジェクトに活かせそうです。"
        ),
        Review(
            enrollmentYear = 2018,
            date = dateFormat.parse("2018-08-10"),
            interestLevel = 2,
            difficultyLevel = 4,
            comment = "内容は難しかったですが、挑戦的な授業でした。新しいスキルを獲得できました。"
        ),
        Review(
            enrollmentYear = 2017,
            date = dateFormat.parse("2017-02-25"),
            interestLevel = 4,
            difficultyLevel = 3,
            comment = "講義は非常に面白く、アルゴリズムの実装に興味を持つようになりました。"
        ),
        Review(
            enrollmentYear = 2016,
            date = dateFormat.parse("2016-07-08"),
            interestLevel = 3,
            difficultyLevel = 2,
            comment = "データ構造とアルゴリズムの基本を学び、プログラミングスキルが向上しました。"
        ),
        Review(
            enrollmentYear = 2015,
            date = dateFormat.parse("2015-12-20"),
            interestLevel = 5,
            difficultyLevel = 1,
            comment = "最高の講義で、高度なアルゴリズムの学習を通じて成長しました。"
        ),
        Review(
            enrollmentYear = 2014,
            date = dateFormat.parse("2014-03-12"),
            interestLevel = 3,
            difficultyLevel = 4,
            comment = "内容は難しかったが、問題解決スキルが向上しました。"
        )
    )
    // こう書くとreviewsが変更されたときに勝手に再描画してくれるらしいよ！
    var reviews by remember {
        mutableStateOf(initialReviews)
    }
    var test by remember {
        mutableStateOf(0)
    }

    // リスト内のデータを出力
    for (review in reviews) {
        Log.i(
            TAG,
            "Graduation Year: ${review.enrollmentYear}, Interest Level: ${review.interestLevel}, Difficulty Level: ${review.difficultyLevel}, Comment: ${review.comment}"
        )
    }

    Column {
        // タイトルを表示
        Text(
            text = "データ構造とアルゴリズム",
            modifier = Modifier.padding(16.dp)
        )
        Text(
            text = "並び替え",
            modifier = Modifier.padding(16.dp)
        )

        Column {
            Button(
                onClick = {
                    test++
                    reviews = sortReviewByYear(initialReviews)
                    Log.i(TAG, "ReviewActivity: ${test}")
                },
                modifier = Modifier
                    .padding(end = 8.dp)
//                    .wrapContentWidth(Alignment.End) // 右寄せにする
            ) {
                Text(text = "受講年度順")
            }
            Button(
                onClick = {
                    test++
                    reviews = sortReviewByInterest(initialReviews)
                    Log.i(TAG, "ReviewActivity: ${test}")
                },
                modifier = Modifier
                    .padding(end = 8.dp)
//                    .wrapContentWidth(Alignment.End) // 右寄せにする
            ) {
                Text(text = "面白さ順")
            }
            Button(
                onClick = {
                    test++
                    reviews = sortReviewByDifficulty(initialReviews)
                    Log.i(TAG, "ReviewActivity: ${test}")
                },
                modifier = Modifier
                    .padding(end = 8.dp)
//                    .wrapContentWidth(Alignment.End) // 右寄せにする
            ) {
                Text(text = "難しさ順")
            }
        }

        // ColumnじゃなくてLazyColumにすると勝手にスクロール出来るようにしてくれるらしい！便利！
        LazyColumn {
            // reviewsの要素をすべて描画する
            items(reviews) { review ->
//            Spacer(modifier = Modifier.width(16.dp))
                displayReview(review = review)
//            Spacer(modifier = Modifier.height(8.dp))
            }
        }
    }
}


// enrollmentYearが遅い順にソート
fun sortReviewByYear(initialReviews: MutableList<Review>): MutableList<Review> {
    val sortedReviews = initialReviews.sortedByDescending { it.enrollmentYear }
    val reviews = sortedReviews.toMutableList()
    return reviews
}

// interestLevelが大きい順にソート
fun sortReviewByInterest(initialReviews: MutableList<Review>): MutableList<Review> {
    val sortedReviews = initialReviews.sortedByDescending { it.interestLevel }
    val reviews = sortedReviews.toMutableList()
    return reviews
}

// difficultyLevelが大きい順にソート
fun sortReviewByDifficulty(initialReviews: MutableList<Review>): MutableList<Review> {
    val sortedReviews = initialReviews.sortedByDescending { it.difficultyLevel }
    val reviews = sortedReviews.toMutableList()
    return reviews
}

fun addReview(reviews: MutableList<Review>) {
    reviews.add(
        Review(
            enrollmentYear = 4000,
            date = dateFormat.parse("2023-01-15"),
            interestLevel = 4,
            difficultyLevel = 3,
            comment = "非常に面白い講義で、アルゴリズムとデータ構造について深く学ぶことができました。"
        )
    )
}

// 渡されたReviewを表示する
@Composable
fun displayReview(review: Review) {
    Surface(
        modifier = Modifier.padding(horizontal = 8.dp, vertical = 8.dp),
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
//                .padding(16.dp)
        ) {
            Row {
                Text(text = "${review.enrollmentYear}年度受講")
                Text(
                    text = dateFormat.format(review.date),
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.End) // 日付を右寄せにする
                )
            }
            Surface(
                color = MaterialTheme.colorScheme.primary,
                shape = RoundedCornerShape(8.dp),
            ) {
                // 面白さ、難しさ、コメントのグループ
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                ) {
                    Text(text = "面白さ：${review.interestLevel}　難しさ：${review.difficultyLevel}")
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = review.comment)
                }
            }
        }
    }
}