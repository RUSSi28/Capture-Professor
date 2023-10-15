package com.example.captureprofessor

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconToggleButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
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

//Bar("情報理論","南角先生","過去問そのままだが量がエグイ\n中間の復習は必ずやろう\n",R.drawable.information_theory)
//テンプレが合った方が書くべき情報が分かりやすいし、見る側にとっても役に立つかも
//とりあえず見た目作ったのでただの関数になっているが、クラスに直そう(それとも構造体？)

@Composable
fun Bar(className:String,teacherName : String,comment: String,imageId:Int){
Surface {
    var isExpanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth(),
//            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {//ボタンをおきたい
        Button(
            onClick = { isExpanded = !isExpanded },
            modifier = Modifier
                .fillMaxWidth(),
            shape = RoundedCornerShape(0)

        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row {

                    Icon(
                        imageVector = if (isExpanded) Icons.Default.KeyboardArrowDown else Icons.Default.KeyboardArrowRight,
                        contentDescription = null,
                    )
                    Text(
                        text = className,
                        color = Color.White
                    )
                    Text(
                        text = "($teacherName)",
                        color = Color.White
                    )
                }


                Spacer(modifier = Modifier.height(4.dp))

                Surface(
                    shape = MaterialTheme.shapes.medium,
                ) {
                }
            }
        }
        if(isExpanded){
            Text(
                text = comment,
                modifier = Modifier.padding(all = 4.dp),
            )

            Image(
                painter = painterResource(id = imageId),
                contentDescription = "情報理論",
                contentScale = ContentScale.Fit,
//                modifier = imageModifier
            )
        }



        // We toggle the isExpanded variable when we click on this Column

    }


}


}

@Composable
fun testReid(){
    Column(
            modifier = Modifier.fillMaxWidth()
        .verticalScroll(rememberScrollState()),
//    horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Bar("情報理論", "南角先生", "過去問そのままだが量がエグイ\n中間の復習は必ずやろう\n", R.drawable.information_theory)
        Bar("情報理論", "南角先生", "過去問そのままだが量がエグイ\n中間の復習は必ずやろう\n", R.drawable.information_theory)

    }
}