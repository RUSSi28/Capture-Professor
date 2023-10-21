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
//        verticalArrangement = Arrangement.SpaceBetween
            ){
        val a = KakomonData("南角",2019,"過去問だけでなんとかなる", R.drawable.informationtheory)
        val b = KakomonData("坂上",2023,"時間がない",R.drawable.ic_launcher_background)
        val c = KakomonData("南角",2023,"aaa", R.drawable.informationtheory)
        val d = KakomonData("南角",2023,"aaa", R.drawable.informationtheory)
        val e = KakomonData("南角",2023,"aaa", R.drawable.informationtheory)
        val f = KakomonData("坂上",2023,"時間がない",R.drawable.ic_launcher_background)

        val array = arrayOf(a,b,c,d,e,f)
        val subject1 = Kakomon("情報理論",array)
        subject1.MainUI()
        for(i in 0..5){
            subject1.Bar(i)
    }
       subject1.Bar(0)//バーを出す関数を直接呼び出している
    }
}

@Preview
@Composable
fun testReidPreview(){
    Surface {
        testReid()
    }
}