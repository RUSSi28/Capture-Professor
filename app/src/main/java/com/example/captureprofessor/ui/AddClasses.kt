package com.example.captureprofessor.ui

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
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.captureprofessor.classes.card.ClassCard
import com.example.captureprofessor.data.Grade
import com.example.captureprofessor.data.GradeDao
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import kotlinx.coroutines.launch

private val TAG = "AddClasses"

@Composable
fun AddClasses(
    modifier: Modifier = Modifier,
    gradeDao: GradeDao
) {
    val db = Firebase.firestore

    var lectureData by remember { mutableStateOf(mutableListOf<ClassCard>()) }
    var updatedLectureData by remember { mutableStateOf(mutableListOf<ClassCard>()) }

//    lectureData =
//        mutableListOf(
//            ClassCard(
//                id = "0030",
//                name = "微分積分Ⅰ及び演習",
//                description = "足立"
//            ),
//            ClassCard(
//                id = "0131",
//                name = "理系基礎演習",
//                description = "船瀬、白松、田中"
//            ),
//            ClassCard(
//                id = "0061",
//                name = "力学",
//                description = "橋本"
//            ),
//            ClassCard(
//                id = "0604",
//                name = "ディジタル回路",
//                description = "橋本"
//            ),
//            ClassCard(
//                id = "5608",
//                name = "プログラミングⅠ",
//                description = "松尾"
//            ),
//            ClassCard(
//                id = "5602",
//                name = "確率",
//                description = "中島"
//            ),
//            ClassCard(
//                id = "5057",
//                name = "電磁気学",
//                description = "二ラウラ"
//            ),
//            ClassCard(
//                id = "5611",
//                name = "情報数学Ⅰ",
//                description = "犬塚"
//            )
//        )
//
//
//    // ここで授業情報を格納する
//    lectureData.forEach {
//        db.collection("lectures").document(it.name)
//            .set(it)
//        Log.d(TAG, "AddClasses: " + it.name + " => " + it.description)
//    }

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
                modifier = Modifier,
                gradeDao = gradeDao
            )
        }
    }
}

@Composable
fun ShowChoiceClasses(
    modifier: Modifier = Modifier,
    classCard: ClassCard,//引数にとる値はクラスでまとめられるならできるだけまとめる
    gradeDao: GradeDao
) {
    var isFavorite by remember { mutableStateOf(favoriteClassMap[classCard.id] ?: false) }
    var coroutineScope= rememberCoroutineScope()
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

            checked = isFavorite,
            onCheckedChange = {
                if(isFavorite) {
                    //ここでdeleteする
                    gradeDao.deleteAll(Grade(classCard.id,classCard.name,"",0,0))
                    isFavorite = false
                }
                else {
                    //ここでinsertする
                    val grade= Grade(classCard.id,classCard.name,"",0,0)
                    coroutineScope.launch {
                        gradeDao.insertAll(grade)
                    }
                    isFavorite = true
                }

            },
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

var favoriteClassMap : MutableMap<String,Boolean> = mutableMapOf(
    "0604" to true,
    "5608" to false,
    "0061" to false,
    "0030" to false,
    "5608" to false,
    "5602" to false,
    "5057" to false,
    "5611" to false,
)
