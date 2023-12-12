package com.example.captureprofessor.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.google.firebase.Firebase
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.storage
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.util.UUID
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine


// Firebaseに画像をアップロードするメソッド、アップロード時に画像のパスを取得
fun uploadImageToFirebase(
    imageUri: Uri,
    lectureName: String,
    firebaseStorage: FirebaseStorage,
) : String {

    val storageRef = firebaseStorage.reference
    val fileName = UUID.randomUUID().toString()
    val imageRef = storageRef.child("$lectureName/$fileName.jpg")
    imageRef.putFile(imageUri)
        .addOnSuccessListener { }
        .addOnFailureListener { }

    return "$lectureName/$fileName.jpg"
}

// firestoreに画像のパスを追加
fun addPastExamImagePass(
    lectureName: String,
    imagePath: String
) {
    val db = Firebase.firestore
    db.collection("lectures").document(lectureName)
        .update("imagePath", FieldValue.arrayUnion(imagePath))
}


@RequiresApi(Build.VERSION_CODES.P)
@Composable
fun UploadImage(
    lectureName: String,
    context: Context,
    onResult: (uri: Uri) -> Unit
) {
    val storage = Firebase.storage

    var imageUri: Uri? by remember {
        mutableStateOf(null)
    }
    var bitmap: Bitmap? by remember {
        mutableStateOf(null)
    }

    val launcher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri ->
            if (uri == null) return@rememberLauncherForActivityResult
            imageUri = uri
            onResult(uri)
        }

    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(256.dp)
                    .background(Color(0xFF000000)),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                imageUri?.let {
                    val source = ImageDecoder
                        .createSource(
                            context.contentResolver,
                            it,
                        )
                    bitmap = ImageDecoder.decodeBitmap(source)

                    bitmap?.let { bm ->
                        Image(
                            bitmap = bm.asImageBitmap(),
                            contentDescription = null,
                            modifier = Modifier.fillMaxWidth()
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))

                if (imageUri == null) {
                    Text(
                        color = Color(0xFFFFFFFF),
                        text = "test"
                    )
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            OutlinedButton(
                modifier = Modifier
                    .testTag("AddButton")
                    .fillMaxWidth(),
                onClick = {
                    launcher.launch("image/*")
                },
            ) {
                Text(
                    text = "pick up"
                )
            }
            OutlinedButton(
                modifier = Modifier
                    .testTag("UploadButton")
                    .fillMaxWidth(),
                enabled = imageUri != Uri.EMPTY,
                onClick = {
                    if(imageUri != null) {
                        uploadImageToFirebase(
                            imageUri = imageUri!!,
                            lectureName = lectureName,
                            firebaseStorage = storage
                        )
                    }
                    imageUri = null
                },
            ) {
                Text(
                    text = "upload"
                )
            }
        }
    }
}



