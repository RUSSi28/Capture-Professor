package com.example.captureprofessor

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
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview(showBackground = true)
@Composable
fun DetailofClassUI(){
    ClassDetailWithButton()
}



@Composable
fun ClassDetailWithButton(modifier:Modifier= Modifier
    .fillMaxSize()){
    Column(modifier=modifier,horizontalAlignment=Alignment.CenterHorizontally) {
            Row {
                //過去問ページへ飛ぶ
                Button(onClick = { /*TODO*/ }) {
                    Text("過去問を見る")

                }
                Spacer(modifier = Modifier.padding(16.dp))
                //教授評価ページへ飛ぶ
                Button(onClick = { /*TODO*/ }) {
                    Text("教授の評価")

                }

            }
        Spacer(modifier = Modifier.padding(16.dp))
        Row {
            Text("成績内訳", fontSize = 24.sp)
            Spacer(modifier = Modifier.padding(32.dp))
            //編集処理に遷移
            Button(onClick = { /*TODO*/ }) {
                Text(text = "編集")
            }

        }
        //UI上でこの空白に成績評価について書き込めるようにする予定
        Spacer(modifier = Modifier.padding(128.dp))
        Row {
            Text("テスト点数(目標)")
            Spacer(modifier = Modifier.padding(16.dp))
            Text("テスト点数(結果)")
        }
        Spacer(modifier = Modifier.padding(64.dp))
        //授業一覧ページへ飛ぶボタン
        Button(onClick = { /*TODO*/ }) {
                Text("授業一覧へ戻る")

            }
        }
    }
