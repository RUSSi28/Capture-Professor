package com.example.captureprofessor.ui.themeimport

import android.net.Uri
import android.util.Log
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.example.captureprofessor.ui.getPastExamImagePass
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


suspend fun getImageUriFromFirebase(
    storage: FirebaseStorage,
    path: String
): Uri {
    var uri = Uri.EMPTY
    suspendCoroutine { continuation ->
        storage.reference.child(path).downloadUrl
            .addOnSuccessListener {
                uri = it
                Log.d("Out Uri", "getImageUri: $uri")
                continuation.resume(it)
            }
            .addOnFailureListener {
                Log.e("Get URI", "getImageUri: error")
                continuation.resumeWithException(it)
            }
    }
    return uri
}


//画像のアップロード機能の追加をする必要がある
@Composable
fun PastExamCollection(
    lectureName: String
) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
//        verticalArrangement = Arrangement.SpaceBetween
    ) {
        val db = Firebase.firestore
        val storage = Firebase.storage
        val coroutinescope = rememberCoroutineScope()


        var path by remember { mutableStateOf(mutableListOf<String>()) }

        // ここで授業情報を格納する
//        db.collection("lectures").document(lectureName)
//            .set(lecture1)


//        val array = remember { mutableStateListOf<KakomonData>() }
//        var reviews by remember { mutableStateOf(mutableListOf<ReviewData>()) }
//        var updatedReviews = mutableListOf<ReviewData>()


        // レビューを所得して、reviewsに格納する
        val docRef = db.collection("lectures").document(lectureName)
        LaunchedEffect(Unit) {
            docRef.get()
                .addOnSuccessListener { field ->
                    //lectureNameドキュメントの中にあるフィールドの画像パスのリストを取得
                    path = field.get("imagePath") as MutableList<String>

                }


            // pathの各要素に対してgetImageUriFromFirebaseを呼び出す
            coroutinescope.launch {
                withContext(Dispatchers.IO) {
//                        addPastExamCollection(lectureName, "pass2")
                    getPastExamImagePass(lectureName)
                    for (imagePath in path) {
                        var uri = getImageUriFromFirebase(storage, imagePath)
                        //画像を表示

                    }
                }
            }
        }


//        val a = KakomonData("南角",2019,"過去問だけでなんとかなる", )
//        val b = KakomonData("坂上",2023,"時間がない",R.drawable.ic_launcher_background)
//        val c = KakomonData("南角",2023,"aaa", R.drawable.informationtheory)
//        val d = KakomonData("南角",2023,"aaa", R.drawable.informationtheory)
//        val e = KakomonData("南角",2023,"aaa", R.drawable.informationtheory)
//        val f = KakomonData("坂上",2023,"時間がない",R.drawable.ic_launcher_background)
//        reviews.forEach{
//            //if that class isAttend
//            array.add(
//                KakomonData(
//
//                )
//            )
//        }

//        val subject1 = remember{ PastExams("$lectureName",array) }
//        subject1.MainUI()
//        for(i in 0..5){
//            subject1.Bar(i)
//        }
//        subject1.Bar(0)//バーを出す関数を直接呼び出している
    }
}
