package com.example.captureprofessor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.captureprofessor.ui.theme.CaptureProfessorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptureProfessorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    //基本MainActivityはTest以外触らないようにしてね
                    Test()
                }
            }
        }
    }
}

@Composable
fun Test(modifier: Modifier = Modifier) {
    //実機またはエミュでテストするときはここに自身で作成した関数をおいてください
    //pushするときはaddの対象から外すかここから消しておいて
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CaptureProfessorTheme {
        Test()
    }
}