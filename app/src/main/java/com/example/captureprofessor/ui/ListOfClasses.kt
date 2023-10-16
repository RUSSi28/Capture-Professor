package com.example.captureprofessor

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.captureprofessor.classes.card.ClassCard

@Composable
@Preview
fun ListOfClasses(modifier: Modifier = Modifier) {
    var classList: MutableState<MutableList<ClassCard>> = remember { mutableStateOf(mutableListOf<ClassCard>()) }
    classList.value = testLists
    Column (
        modifier = modifier
            .padding(4.dp)
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
    ){
        classList.value.forEach {
            ShowClasses(
                classCard = it,
                modifier = modifier,
            )
            Spacer(modifier = modifier.padding(4.dp))
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
            .border(width = 0.5.dp, color = Color.Black)
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(8.dp),
                color = Color.LightGray
            ),

    ){
        Text(
            text = classCard.id.toString(),
            modifier = modifier
                .align(alignment = Alignment.CenterVertically)
                .padding(12.dp),
        )

        Column (
            modifier = Modifier.padding(start = 8.dp)
        ){
            Text(text = classCard.name)
            Text(text = classCard.description)
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
        )
    )


