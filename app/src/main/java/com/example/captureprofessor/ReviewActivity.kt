package com.example.captureprofessor

import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date

private const val TAG = "ReviewActivity"

// レビューを格納するデータクラス
data class Review(
    val enrollmentYear: Int, // 受講年度
    val date: Date, // コメント日付
    val interestLevel: Int, // 授業の面白さ
    val difficultyLevel: Int, // 授業の難しさ
    val comment: String, // コメント
)
// 授業情報を格納するデータクラス
data class Lecture(
    val lectureName: String,
    val professorName: String,
)

val lectureSample = Lecture(
    lectureName = "データ構造とアルゴリズム",
    professorName = "片山喜章"
)

// SimpleDateFormatを設定しておくよ！
private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

// Reviewを大量に宣言（ChatGPT最強！！ChatGPT最強！！）
private val initialReviews = mutableListOf(
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

@Composable
fun ReviewActivity() {
//    val TAG = "ReviewActivity"

    // こう書くとreviewsが変更されたときに勝手に再描画してくれるらしいよ！
    var reviews by remember {
        mutableStateOf(initialReviews)
    }
    var selectedSortOption by remember { mutableStateOf("受講年度順") }

    var test by remember {
        mutableStateOf(0)
    }

    Column {
        Row {
            // タイトルを表示
            Text(
                text = "データ構造とアルゴリズム",
                modifier = Modifier.padding(16.dp)
            )
            DropdownMenu(
                selectedSortOption = selectedSortOption,
                onSortOptionSelected = { option ->
                    selectedSortOption = option
                    reviews = when (option) {
                        "受講年度順" -> sortReviewByYear(initialReviews)
                        "面白さ順" -> sortReviewByInterest(initialReviews)
                        "難しさ順" -> sortReviewByDifficulty(initialReviews)
                        else -> initialReviews // デフォルトは元の順序
                    }
                }
            )
        }
        displayReviews(reviews = reviews)
    }
}

// 渡されたすべてのレビューを表示
@Composable
fun displayReviews(reviews: MutableList<Review>) {
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
//                color = MaterialTheme.colorScheme.primary, // テーマのプライマリカラーを使用
                color = Color.LightGray,
                shape = RoundedCornerShape(8.dp),
//                modifier = Modifier.border(1.dp, Color.Black) // 枠線の太さと色を設定
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

// ドロップダウンメニューを作成

@Composable
fun DropdownMenu(
    selectedSortOption: String,
    onSortOptionSelected: (String) -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.TopEnd
    ) {
        Row{
            IconButton(onClick = { expanded = true }) {
                Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription = null)
            }
            Text(text = "$selectedSortOption", modifier = Modifier.padding(16.dp))
        }
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(onClick = {
                onSortOptionSelected("受講年度順")
                expanded = false
            }) {
                Text(text = "受講年度順")
            }
            DropdownMenuItem(onClick = {
                onSortOptionSelected("面白さ順")
                expanded = false
            }) {
                Text(text = "面白さ順")
            }
            DropdownMenuItem(onClick = {
                onSortOptionSelected("難しさ順")
                expanded = false
            }) {
                Text(text = "難しさ順")
            }
        }
    }
}