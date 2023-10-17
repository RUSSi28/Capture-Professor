package com.example.captureprofessor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.captureprofessor.ui.theme.CaptureProfessorTheme
import com.example.captureprofessor.ui.themeimport.testReid

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptureProfessorTheme {
                // A surface container using the 'background' color from the theme
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = { MyTopAppBar() }
                ) {paddingValues ->
                    //基本MainActivityはTest以外触らないようにしてね
                    Column (modifier = Modifier.padding(paddingValues)){
                        Test()
                    }
                }
            }
        }
    }
}

@Composable
fun Test(modifier: Modifier = Modifier) {
    //実機またはエミュでテストするときはここに自身で作成した関数をおいてください
    //pushするときはaddの対象から外すかここから消しておいて
    testReid()
}

@Composable
fun MyTopAppBar(modifier: Modifier = Modifier) {
    TopAppBar (
        modifier = modifier,
        title = { Text(text = "Test") },
        backgroundColor = Color(255,255,255),
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CaptureProfessorTheme {
        Test()
    }
}