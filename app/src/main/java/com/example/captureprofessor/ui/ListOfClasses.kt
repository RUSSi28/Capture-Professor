package com.example.captureprofessor.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.border
<<<<<<< HEAD
=======
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
>>>>>>> 667ec8cfc49dfdf771b83205860cc66ea65895bd
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.captureprofessor.classes.card.ClassCard

@Composable
fun ListOfClasses(
    modifier: Modifier = Modifier,
    onClassClicked: () -> Unit,
) {
    var classList: MutableState<MutableList<ClassCard>> = remember { mutableStateOf(mutableListOf<ClassCard>()) }
    classList.value = testLists
    Column (
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        classList.value.forEach {
            Spacer(modifier = modifier.padding(4.dp))
            ShowClasses(
                classCard = it,
                modifier = modifier.clickable { onClassClicked() },
            )
        }
    }
}

@Composable
fun ShowClasses(
    modifier: Modifier = Modifier,
    classCard: ClassCard //引数にとる値はクラスでまとめられるならできるだけまとめる
) {
    Row (
        modifier = modifier
//            .border(width = 0.5.dp, color = Color.Black)
            .fillMaxWidth()
            .padding(8.dp)
            .shadow(elevation = 4.dp, shape = RoundedCornerShape(8.dp))
            .background(
                shape = RoundedCornerShape(8.dp),
                color = Color(243, 243, 243, 255),
            ),

    ){
        Text(
            text = classCard.id.toString(),
            modifier = modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(16.dp),
        )

        Column (
            modifier = Modifier.padding(start = 8.dp),
        ){
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

val testLists : MutableList<ClassCard> =
    mutableListOf(
        ClassCard(
            id = 5241,
            name = "sample",
            description = "this is a sample"
        ),
        ClassCard(
            id = 6311,
            name = "test",
            description = "this is a test"
        ),
        ClassCard(
            id = 5200,
            name = "liner hoge",
            description = "hoge hoge hoge hoge hoge"
        ),
        ClassCard(
            id = 5200,
            name = "liner hoge",
            description = "hoge hoge hoge hoge hoge"
        ),
        ClassCard(
            id = 5200,
            name = "liner hoge",
            description = "hoge hoge hoge hoge hoge"
        ),
        ClassCard(
            id = 5200,
            name = "liner hoge",
            description = "hoge hoge hoge hoge hoge"
        ),
        ClassCard(
            id = 5200,
            name = "liner hoge",
            description = "hoge hoge hoge hoge hoge"
        ),
        ClassCard(
            id = 5200,
            name = "liner hoge",
            description = "hoge hoge hoge hoge hoge"
        )
    )


