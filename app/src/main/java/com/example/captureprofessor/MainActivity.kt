package com.example.captureprofessor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.captureprofessor.components.KakomonData
import com.example.captureprofessor.ui.theme.CaptureProfessorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptureProfessorTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Greeting("Android")
                }
            }
        }
        val kakomon1 = KakomonData(R.drawable.uzqw8zad__1_,"情報理論");
        @Composable
        fun displayImage() {
            if (kakomon1.imageData != null) {
                // 画像リソースIDが指定されている場合、ImageViewに画像を設定
                Image(
                    painter = painterResource(id = kakomon1.imageData),//KakomonDataクラスの引数をvar➡valにしたらいけた
                    contentDescription = null,
                    modifier = Modifier
                        .size(110.dp)
                        .clip(RoundedCornerShape(10.dp))
                )
            }
        }
    }




@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CaptureProfessorTheme {
        Greeting("Android")
    }
}