package com.websarba.wings.android.detailofactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonColors
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldColors
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.room.Dao
import androidx.room.Room
import com.example.captureprofessor.classes.card.ClassCard
import com.example.captureprofessor.ui.Grade
import com.example.captureprofessor.ui.GradeDao
import com.example.captureprofessor.ui.GradeDatabase
import com.example.captureprofessor.ui.GradeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun DetailOfClassUI(
    onClickPastExamButton: () -> Unit,
    onClickEvaluationButton: () -> Unit,
    onNavigateBack: () -> Unit,
    classCard: ClassCard,
    gradeviewmodel:GradeViewModel,
    gradeDao:GradeDao
){
    DetailPreview(
        onClickPastExamButton = onClickPastExamButton,
        onClickEvaluationButton = onClickEvaluationButton,
        onNavigateBack = onNavigateBack,
        classCard = classCard,
        gradeviewmodel = gradeviewmodel,
        gradeDao = gradeDao
    )
    ShowTitle("title")
}





@Composable
fun DetailPreview(
    onClickEvaluationButton: () -> Unit,
    onClickPastExamButton: () -> Unit,
    onNavigateBack: () -> Unit,
    classCard: ClassCard,
    gradeviewmodel:GradeViewModel,
    gradeDao:GradeDao
) {
    Column {
        ShowTitle("${classCard.name}")  //classCardクラスからname(授業名)を引っ張って来てる
        Spacer(modifier = Modifier.padding(16.dp))
        ClassDetailWithButton(
            onClickEvaluationButton = onClickEvaluationButton,  //ボタンの受け渡し
            onClickPastExamButton = onClickPastExamButton,
            onNavigateBack = onNavigateBack,
            classCard = classCard,
            gradeviewmodel = gradeviewmodel,
            gradeDao = gradeDao
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
    classCard: ClassCard,
    gradeviewmodel:GradeViewModel,
    gradeDao:GradeDao,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Row {
            //過去問ページへ飛ぶ
            Surface {
                Button(
                    onClick = onClickPastExamButton, colors = ButtonDefaults.textButtonColors(
                        backgroundColor = Color.White
                    ), modifier = Modifier.clip(RoundedCornerShape(30.dp))
                ) {//ここにもNavigationの処理渡したいんだけど階層が深い
                    Text("過去問一覧")
                }
            }
            Spacer(modifier = Modifier.padding(24.dp))
            //教授評価ページへ飛ぶ


            Button(
                onClick = onClickEvaluationButton, colors = ButtonDefaults.textButtonColors(
                    backgroundColor = Color.Blue
                ), modifier = Modifier
                    .width(120.dp)
                    .height(50.dp)
            ) {
                Text("授業の評価", color = Color.White) //授業の評価(攻略のさぶとしたほうがよさそう)
            }

        }
        Spacer(modifier = Modifier.padding(24.dp))
        Row {
            val coroutineScope= rememberCoroutineScope()
            Text("成績内訳", fontSize = 24.sp)
            Spacer(modifier = Modifier.padding(48.dp))
            //点数、成績評価を入力したものに更新する
            Button(
                //ボタンが押されたら変更されたデータを保存する
                onClick = {
                    coroutineScope.launch {
                        withContext(Dispatchers.IO){
                            gradeDao.insertAll(Grade("修正","修正",gradeviewmodel.eval,gradeviewmodel.target,gradeviewmodel.result))
                        }
                    }
                }, colors = ButtonDefaults.textButtonColors(
                    backgroundColor =Color.Green
                )
            ) {
                Text(text = "保存", color = Color.Black)
            }


        }
        //ここの部分に入力欄を実装
        Box(modifier = Modifier.padding(16.dp)) {
            TextField(value =gradeviewmodel.eval,
                onValueChange = {gradeviewmodel.eval=it},
                modifier = Modifier
                    .fillMaxWidth(),
                placeholder = { Text("成績評価の方法を入力してください", fontWeight = FontWeight.Bold) },
                colors=TextFieldDefaults.textFieldColors(backgroundColor= Color.LightGray))
        }
        Column {
            Text("テスト点数(目標)")
            TextField(value = gradeviewmodel.target.toString(),
                onValueChange = {gradeviewmodel.target=Integer.parseInt(it)},
                placeholder = { Text(text = "85") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors=TextFieldDefaults.textFieldColors(backgroundColor=Color.LightGray))
            Spacer(modifier = Modifier.padding(8.dp))
            Text("テスト点数(結果)")
            TextField(value = gradeviewmodel.result.toString(),
                onValueChange = {gradeviewmodel.result=Integer.parseInt(it)},
                placeholder = { Text(text = "90") },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                singleLine = true,
                colors = TextFieldDefaults.textFieldColors(backgroundColor = Color.LightGray)
                )
        }

        Spacer(modifier = Modifier.padding(64.dp))
        //授業一覧ページへ飛ぶボタン
        Button(onClick = onNavigateBack) {
            Text("授業一覧へ戻る")
        }
    }
}


