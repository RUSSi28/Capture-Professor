package com.example.captureprofessor


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.captureprofessor.classes.card.ClassCard
import com.example.captureprofessor.ui.AddClasses
import com.example.captureprofessor.ui.ButtonScreenViewModel
import com.example.captureprofessor.ui.DataForm
import com.example.captureprofessor.ui.ListOfClasses
import com.example.captureprofessor.ui.theme.CaptureProfessorTheme
import com.example.captureprofessor.ui.themeimport.PastExamCollection
import com.websarba.wings.android.detailofactivity.DetailOfClassUI

class MainActivity : ComponentActivity() {
    private val buttonScreenViewModel : ButtonScreenViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptureProfessorTheme {
                var navController = rememberNavController()

                Scaffold(
                    modifier = Modifier
                        .fillMaxSize(),
                    topBar = {
                        MyTopAppBar(
                            onAddClicked = {
                                navController.navigate(route = NavigationDestination.AddReviewClass.name)
                            },//ここにも追加画面の画面遷移を使用したい
                        )
                    }
                ) { paddingValues ->
                    Column(
                        modifier = Modifier
                            .padding(paddingValues)
                            .background(
                                Brush.verticalGradient(
                                    colors = listOf(
                                        Color(150, 243, 232, 255),
                                        Color(147, 204, 243, 255),
                                        Color(150, 147, 243, 255)
                                    )
                                )
                            )
                    ) {

                        var focusedClass by remember {
                            mutableStateOf<ClassCard>(
                                ClassCard(
                                    "",
                                    "",
                                    ""
                                )
                            )
                        }
                        //ここの値を渡したいんだけど階層深すぎてかくのめんどくさいよね多分

                        NavHost(
                            navController = navController,
                            startDestination = NavigationDestination.ListOfClass.name
                        ) {
                            composable(route = NavigationDestination.ListOfClass.name) {
                                //講義一覧→詳細画面
                                ListOfClasses(
                                    onClassClicked = {
                                        navController.navigate(route = NavigationDestination.DetailOfClass.name)
                                        focusedClass = it
                                    }
                                )
                            }
                            composable(route = NavigationDestination.DetailOfClass.name) {
                                //詳細画面→れびゅー
                                DetailOfClassUI(
                                    onClickEvaluationButton = {
                                        navController.navigate(route = NavigationDestination.ClassEvaluation.name)
                                    },
                                    onClickPastExamButton = {
                                        navController.navigate(route = NavigationDestination.PastExams.name)
                                    },
                                    onNavigateBack = { navController.popBackStack() },
                                    classCard = focusedClass
                                )
                            }
                            composable(route = NavigationDestination.ClassEvaluation.name) {
                                ReviewActivity(
                                    onClickAddReviewButton = {
                                        navController.navigate(route = NavigationDestination.AddReviewClass.name)
                                    },
                                    LectureName = focusedClass.name,
                                    buttonScreenViewModel = buttonScreenViewModel
                                )
                            }
                            composable(route = NavigationDestination.AddReviewClass.name) {
                                DataForm(focusedClass.name)
                            }
                            composable(route = NavigationDestination.PastExams.name) {
                                PastExamCollection()
                            }
                            composable(route = NavigationDestination.AddReviewClass.name) {
                                AddClasses()
                            }
                        }
                    }
                }
            }
        }
    }
}


enum class NavigationDestination {
    ListOfClass,
    ClassEvaluation,
    PastExams,
    DetailOfClass,
    AddReviewClass
}

@Composable
fun MyTopAppBar(
    modifier: Modifier = Modifier,
    onAddClicked: () -> Unit,
) {
    TopAppBar(
        modifier = modifier,
        title = { Text(text = "Lectures") },
        backgroundColor = Color(255, 255, 255),
        navigationIcon = {

        },
        actions = {
            IconButton(onClick = onAddClicked) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "ADD")
            }
        }
    )

}
