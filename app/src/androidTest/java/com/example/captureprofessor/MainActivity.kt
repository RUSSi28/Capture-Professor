package com.websarba.wings.android.detailofclass

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.websarba.wings.android.detailofclass.ui.theme.DetailofClassTheme


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailofClassTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ShowTitle("Android")
                }
            }
        }
    }
}

//授業タイトルを表示する処理
@Composable
fun ShowTitle(title: String) {
    Surface(color=MaterialTheme.colorScheme.primary,modifier=Modifier.fillMaxWidth()) {
        Text(
            text = "$title" ,modifier=Modifier.padding(12.dp)
        )
    }
}
//授業の成績評価、テストの点数を表示
@Composable
fun ShowDetail(){
    Column {
        //2つのボタンを中央揃えしたい
        Row(modifier=Modifier.fillMaxSize(),verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally){
            Button(onClick = { /*TODO*/ }) {Text("過去問")
                
            }
            Button(onClick = { /*TODO*/ }) {Text("教授の評価")
                
            }
        }
        Row {
            Text(text = "成績評価")
            Button(onClick = { /*TODO*/ },modifier=Modifier.align(Alignment.Bottom)) {Text("編集")
                
            }
        }
        Row {
            Column {
                Text(text = "テスト(目標点)")
                Text(text="      /")
            }
            Column {
                Text(text = "テスト(結果)")
                Text(text="      /")
            }
        }
        Button(onClick = { /*TODO*/ }) {
            Text("授業一覧に戻る")
            
        }
    }
}

//成績評価欄を編集する処理
@Composable
fun InputDetail(){}

//教授評価のチャット欄に移動する処理
@Composable
fun CheckReputation(){}

//過去問掲載ページに移動する処理
@Composable
fun CheckPastquestions(){}

//授業一覧ページに戻る処理
@Composable
fun BacktoClasslist(){}


@Preview(showBackground = true)
@Composable
fun Preview() {
    DetailofClassTheme {
        Column {
            ShowTitle("授業名")
            ShowDetail()
        }
    }
}