package com.example.captureprofessor.ui.themeimport
import android.util.Log
import com.example.captureprofessor.ui.themeimport.KakomonData
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.captureprofessor.R

@Composable
fun testReid(){
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.SpaceBetween
            ){
        val a = KakomonData("Noname",2023,"aaa", R.drawable.tubaicon)
        val array = arrayOf(a)
        val subject1 = Kakomon("情報理論",array)

        Text(
            text = array.size.toString()
        )

        Text(
            text = array[0].year.toString(),//なにこれ？？？？　　　
            color = Color.White

        )
        Text(
            text = array[0].comment
        )//配列にアクセスはできるな、、、
        Image(
            painter = painterResource(id = array[0].imageId),
            contentDescription = "情報理論",
            contentScale = ContentScale.Fit,
        )
       subject1.Bar(0)
        subject1.Bar(0)
        subject1.Bar(0)
        subject1.Bar(0)
        subject1.Bar(0)
        subject1.Bar(0)
        subject1.Bar(0)
        subject1.Bar(0)
        subject1.Bar(0)
    }
}

@Preview
@Composable
fun testReidPreview(){
    Surface {
        testReid()
    }
}