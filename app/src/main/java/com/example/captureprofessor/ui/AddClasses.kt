package com.example.captureprofessor.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.captureprofessor.classes.card.ClassCard
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

private val TAG = "AddClasses"

@Composable
fun AddClasses(
    modifier: Modifier = Modifier,
) {
    val db = Firebase.firestore

    var lectureData by remember { mutableStateOf(mutableListOf<ClassCard>()) }
    var updatedLectureData by remember { mutableStateOf(mutableListOf<ClassCard>()) }

    val docRef = db.collection("lectures")
    docRef.get()
        .addOnSuccessListener { documents ->
            for (document in documents) {
                updatedLectureData.add(
                    ClassCard(
                        id = document.data["id"].toString(),
                        name = document.data["name"].toString(),
                        description = document.data["description"].toString()
                    )
                )
            }
            lectureData = updatedLectureData
        }


    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(8.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Spacer(modifier = Modifier.padding(4.dp))
        lectureData.forEach { classCard ->
            ShowChoiceClasses(
                classCard = classCard,
                modifier = Modifier
            )
        }
    }
}

@Composable
fun ShowChoiceClasses(
    modifier: Modifier = Modifier,
    classCard: ClassCard //引数にとる値はクラスでまとめられるならできるだけまとめる
) {
    Row(
        modifier = modifier
//            .border(width = 0.5.dp, color = Color.Black)
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
            .background(
                shape = RoundedCornerShape(8.dp),
                color = Color(243, 243, 243, 255),
            ),

        ) {
        Checkbox(
            checked = false, // チェック状態（true: チェックあり、false: チェックなし）
            onCheckedChange = { isChecked -> /* チェック状態が変更されたときの処理 */ },
            modifier = modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(8.dp),
        )

        Text(
            text = classCard.id.toString(),
            modifier = modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(16.dp),
        )

        Column(
            modifier = Modifier.padding(start = 8.dp),
        ) {
            Text(
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold,
                text = classCard.name,
                color = Color.Gray
            )
            Text(
                text = classCard.description,
                color = Color.Gray
            )
        }
        Spacer(modifier = modifier)
    }
}