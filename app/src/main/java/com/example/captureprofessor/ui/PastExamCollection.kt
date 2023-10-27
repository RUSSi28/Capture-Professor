package com.example.captureprofessor.ui.themeimport
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.captureprofessor.R

@Composable
fun PastExamCollection(){
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
        val subject1 = PastExams("情報理論",array)
        subject1.MainUI()
        for(i in 0..5){
            subject1.Bar(i)
        }
        subject1.Bar(0)//バーを出す関数を直接呼び出している
    }
}



