package com.example.captureprofessor

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
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
import com.example.captureprofessor.sample.ReviewData
import com.example.captureprofessor.sample.dateFormat
import com.example.captureprofessor.sample.lecture1
import com.example.captureprofessor.ui.ButtonScreenViewModel
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

private const val TAG = "ReviewActivity"


// 講座名を引数で渡してください
@Composable
fun ReviewActivity(
    onClickAddReviewButton: () -> Unit,
    LectureName: String,
    buttonScreenViewModel: ButtonScreenViewModel
) {
    val db = Firebase.firestore

    // ここで授業情報を格納する
    db.collection("lectures").document(LectureName)
        .set(lecture1)


    var selectedSortOption by remember { mutableStateOf("受講年度順") }
    var reviews by remember { mutableStateOf(mutableListOf<ReviewData>()) }
    var updatedReviews = mutableListOf<ReviewData>()

    // レビューを所得して、reviewsに格納する
    val docRef = db.collection(LectureName)
    docRef.get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                if (document.exists()) {
                    val enrollmentYear = document.getLong("enrollmentYear")
                    val date = document.getDate("date")
                    val interestLevel = document.getLong("interestLevel")
                    val difficultyLevel = document.getLong("difficultyLevel")
                    val comment = document.getString("comment")
                    val reviewData = ReviewData(
                        enrollmentYear = enrollmentYear!!.toInt(),
                        date = date,
                        interestLevel = interestLevel!!.toInt(),
                        difficultyLevel = difficultyLevel!!.toInt(),
                        comment = comment!!
                    )
                    reviews.add(reviewData)
                }

            }
        }


    // 画面に描画する
    Column(
        modifier = Modifier.fillMaxSize(),
    ) {
        Row {
            Text(
                text = LectureName,
                modifier = Modifier.padding(16.dp)
            )
            DropdownMenu(
                selectedSortOption = selectedSortOption,
                onSortOptionSelected = { option ->
                    selectedSortOption = option
                    reviews = when (option) {
                        "受講年度順" -> buttonScreenViewModel.sortReviewByYear(reviews)
                        "面白さ順" -> buttonScreenViewModel.sortReviewByInterest(reviews)
                        "難しさ順" -> buttonScreenViewModel.sortReviewByDifficulty(reviews)
                        else -> buttonScreenViewModel.sortReviewByYear(reviews) // デフォルトは年度順
                    }
                }
            )
        }
        displayReviews(reviews = reviews)

        Button(
            onClick = onClickAddReviewButton,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
                .padding(8.dp)
        ) {
            Text(text = "レビューを追加")
        }
    }
}


// 渡されたすべてのレビューを表示
@Composable
fun displayReviews(reviews: MutableList<ReviewData>) {
    // ColumnじゃなくてLazyColumにすると勝手にスクロール出来るようにしてくれるらしい！便利！
    LazyColumn(
        modifier = Modifier
            .height(600.dp),
    ) {
        Log.d(TAG, "displayReviews: ")
        // reviewsの要素をすべて描画する
        items(reviews) { review ->
            displayReview(review = review)
        }
    }
}

// 渡されたReviewを表示する
@Composable
fun displayReview(review: ReviewData) {

    Surface(
        modifier = Modifier
            .padding(horizontal = 8.dp, vertical = 8.dp),
        shape = RoundedCornerShape(8.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    color = Color(255, 255, 255, 255),
                    shape = RoundedCornerShape(8.dp)
                )

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
                color = Color.LightGray,
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
        Row {
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

@Composable
fun ButtonCompose(
    buttonScreenViewModel: ButtonScreenViewModel
) {
    val mytext = buttonScreenViewModel.myTextState
    val isTapped = buttonScreenViewModel.isTappedState

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally, // 横方向
        verticalArrangement = Arrangement.Center // 縦方向
    ) {
        Text(
            modifier = Modifier.padding(horizontal = 4.dp),
            text = mytext.value,
//            fontSize = 14.sp,
        )
        if (!isTapped.value) {  //isTapped = trueだった場合そもそもButtonの描画がされ_ないようになる
            Button(
                onClick = {
                    isTapped.value = true
                    mytext.value = "タップ不可"
                }
            ) {
                Text("Click!")
            }
        }
    }
}