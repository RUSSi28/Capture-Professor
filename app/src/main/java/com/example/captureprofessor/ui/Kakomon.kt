package com.example.captureprofessor.ui.themeimport
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowRight
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import coil.compose.AsyncImage
import com.example.captureprofessor.R

class PastExams(val subjectName:String, val array:Array<KakomonData> ) {
//画像(後で複数にするがとりあえず一枚)
// アドバイスとかのテキスト
// この二つをプロパティとして持つ
// バーを押すと開く感じにする
// 引数には名前+1要素が1過去問の配列
@Composable
fun MainUI(){
    TopAppBar (
        title = { Text(text = subjectName) },
        backgroundColor = Color(100,80,180),
    )
}




@Composable
fun Bar(index: Int) {//配列にはアクセスできる
        Surface {//この中です。
            var isExpanded by remember { mutableStateOf(false) }
            Column(//ここの記述が問題だ！
                modifier = Modifier
                    .background(Color.White)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {//ボタンをおきたい
                Button(//ボタンの標準カラーが青
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
                        }
                        Spacer(modifier = Modifier.height(4.dp))

                    }
                }
//                if (isExpanded) {
//                    AsyncImage(
//                        //この部分画像のpathからuriを取得してmodelに代入する必要がある
////                        model = array[index].imageUri.toUri(),
//                        model = ,
//                        contentDescription = null,
//                        contentScale = ContentScale.Fit
//                    )
//                }
            }
        }
}
}


data class KakomonData(
    var imagePath: String = ""){
}