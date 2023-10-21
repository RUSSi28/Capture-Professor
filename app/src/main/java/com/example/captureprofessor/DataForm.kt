package com.example.captureprofessor

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.example.captureprofessor.ui.Lecture

private val TAG = "InputData"

// コメントとボタンの配置は要修正
@Composable
fun DataForm(lecture: Lecture) {

    var enrollmentYear by remember { mutableStateOf("") }
    var difficultyLevel by remember { mutableStateOf("") }
    var interestLevel by remember { mutableStateOf("") }
    var comment by remember { mutableStateOf("") }


    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        // タイトルを表示
        Text(
            text = lecture.lectureName,
            modifier = Modifier.padding(8.dp)
        )
        OutlinedTextField(
            value = enrollmentYear,
            onValueChange = {
                if (it.isDigitsOnly()) { // 数値のみを受け入れる
                    enrollmentYear = it
                }
            },
//            placeholder = { Text("2023") }
            singleLine = true,
            label = { Text("受講年度") },
            keyboardOptions = KeyboardOptions.Default.copy(
                keyboardType = KeyboardType.Number
            ),
        )
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(end = 4.dp),
                value = difficultyLevel,
                onValueChange = {
                    if (it.isDigitsOnly()) { // 数値のみを受け入れる
                        difficultyLevel = it
                    }
                },
                singleLine = true,

                label = { Text("難しさ") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
            )
            OutlinedTextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .padding(start = 4.dp),
                value = interestLevel,
                onValueChange = {
                    if (it.isDigitsOnly()) { // 数値のみを受け入れる
                        interestLevel = it
                    }
                },
                singleLine = true,
                label = { Text("面白さ") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number
                ),
            )
        }



        OutlinedTextField(
            modifier = Modifier.fillMaxWidth().height(200.dp),
            value = comment,
            onValueChange = { comment = it },
            label = { Text("コメント") }
        )
        Button(
//            modifier = Modifier.fillMaxHeight(1f),
            onClick = {
                if (enrollmentYear != ""
                    && difficultyLevel != ""
                    && interestLevel != ""
                    && comment != ""
                ) {
                    // データの保存を行う。
                    Log.i(TAG, "DataForm: click!")
                }
            },
            modifier = Modifier
                .align(Alignment.End) // ボタンを右に配置
//                .fillMaxHeight()
        ) {
            Text("保存")
        }
    }

}