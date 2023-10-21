package com.websarba.wings.android.detailofactivity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
<<<<<<< HEAD
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
=======
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
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
>>>>>>> 737405af4b982d27411d35a33e370c8c47d545b4
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
<<<<<<< HEAD
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.captureprofessor.ui.ListOfClasses
import com.example.captureprofessor.ui.ReviewActivity
import com.example.captureprofessor.ui.theme.CaptureProfessorTheme
=======
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.websarba.wings.android.detailofactivity.ui.theme.DetailOfActivityTheme
>>>>>>> 737405af4b982d27411d35a33e370c8c47d545b4

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DetailOfActivityTheme {
                // A surface container using the 'background' color from the theme
<<<<<<< HEAD
                Test()
=======
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DetailPreview()
                }
>>>>>>> 737405af4b982d27411d35a33e370c8c47d545b4
            }
        }
    }
}

<<<<<<< HEAD
@Composable
fun Test(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MyTopAppBar(
                onAddClicked = {  }
            )
        }
    ) {paddingValues ->
        Column (
            modifier = Modifier
                .padding(paddingValues)
                .background(
                    Brush.verticalGradient(
                        colors = listOf(
                            Color(150, 243, 232, 255),
                            Color(147, 204, 243, 255),
                            Color(150, 147, 243, 255)
                        )
                    )
                )
        ){
            //実機またはエミュでテストするときはここに自身で作成した関数をおいてください
            //pushするときはaddの対象から外すかここから消しておいて
            var navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = NavigationDestination.ListOfClass.name
            ){
                composable(route = NavigationDestination.ListOfClass.name){
                    ListOfClasses(
                        onClassClicked = {
                            navController.navigate(route = NavigationDestination.ClassEvaluation.name)
                        }
                    )
                }
                composable(route = NavigationDestination.ClassEvaluation.name) {
                    ReviewActivity()
                }
            }
        }
    }
}

enum class NavigationDestination{
    ListOfClass,
    ClassEvaluation,
}


@Composable
fun MyTopAppBar(
    modifier: Modifier = Modifier,
    onAddClicked: () -> Unit
) {
    TopAppBar (
        modifier = modifier,
        title = { Text(text = "Lectures") },
        backgroundColor = Color(255,255,255),
        navigationIcon = {
//            if(currentScreen == Home) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "HOME")
//            }
        },
        actions = {
            IconButton(onClick = onAddClicked) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "ADD")
            }
        }
    )

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CaptureProfessorTheme {
        Test()
=======
@Preview(showBackground = true)
@Composable
fun DetailPreview() {
    Column {
        showTitle("授業名")
        Spacer(modifier = Modifier.padding(16.dp))
        ClassDetailWithButton()
    }
}

@Composable
fun showTitle(title:String){
    Surface(color=MaterialTheme.colorScheme.primary,modifier=Modifier.fillMaxWidth()) {
        Text(
            text = "$title" ,modifier=Modifier.padding(12.dp)
        )
    }
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
>>>>>>> 737405af4b982d27411d35a33e370c8c47d545b4
    }
