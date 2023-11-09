package com.websarba.wings.android.detailofactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.captureprofessor.classes.card.ClassCard

@Composable
fun DetailOfClassUI(
    onClickPastExamButton: () -> Unit,
    onClickEvaluationButton: () -> Unit,
    onNavigateBack: () -> Unit,
    classCard: ClassCard
){
    DetailPreview(
        onClickPastExamButton = onClickPastExamButton,
        onClickEvaluationButton = onClickEvaluationButton,
        onNavigateBack = onNavigateBack,
        classCard = classCard
    )
    ShowTitle("title")
}


@Composable
fun DetailPreview(
    onClickEvaluationButton: () -> Unit,
    onClickPastExamButton: () -> Unit,
    onNavigateBack: () -> Unit,
    classCard: ClassCard
) {
    Column {
        ShowTitle("${classCard.name}")  //classCardクラスからname(授業名)を引っ張って来てる
        Spacer(modifier = Modifier.padding(16.dp))
        ClassDetailWithButton(
            onClickEvaluationButton = onClickEvaluationButton,  //ボタンの受け渡し
            onClickPastExamButton = onClickPastExamButton,
            onNavigateBack = onNavigateBack,
            classCard = classCard
        )
    }
}

@Composable
fun ShowTitle(title:String){
    Surface(modifier=Modifier.fillMaxWidth()) {
        Text(
            text = "$title" ,modifier=Modifier.padding(12.dp)
        )
    }
}

@Composable
fun ClassDetailWithButton(
    modifier:Modifier= Modifier.fillMaxSize(),
    onClickPastExamButton: () -> Unit,  //ボタンの情報を渡している
    onClickEvaluationButton: () -> Unit,
    onNavigateBack: () -> Unit,
    classCard: ClassCard
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            //過去問ページへ飛ぶ
            Surface {
                Button(
                    onClick = onClickPastExamButton, colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.Cyan
                    ), modifier = Modifier.clip(RoundedCornerShape(30.dp))
                ) {//ここにもNavigationの処理渡したいんだけど階層が深い
                    Text("過去問一覧")

                }

            }
            Spacer(modifier = Modifier.padding(24.dp))
            //教授評価ページへ飛ぶ


            Button(
                onClick = onClickEvaluationButton, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Green
                ), modifier = Modifier.clip(RoundedCornerShape(30.dp))
            ) {
                Text("${classCard.description}") //授業の評価(攻略のさぶとしたほうがよさそう)
            }


        }
        Spacer(modifier = Modifier.padding(24.dp))
        Row {
            Text("成績内訳", fontSize = 24.sp)
            Spacer(modifier = Modifier.padding(48.dp))
            //編集処理に遷移
            Button(
                onClick = { /*TODO*/ }, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.LightGray
                )
            ) {
                Text(text = "編集", color = Color.Black)
            }


        }
        Spacer(modifier = Modifier.padding(128.dp))
        Row {
            Text("テスト点数(目標)")
            Spacer(modifier = Modifier.padding(16.dp))
            Text("テスト点数(結果)")
        }
        Spacer(modifier = Modifier.padding(64.dp))
        //授業一覧ページへ飛ぶボタン
        Button(onClick = onNavigateBack) {
            Text("授業一覧へ戻る")

        }
    }
}


