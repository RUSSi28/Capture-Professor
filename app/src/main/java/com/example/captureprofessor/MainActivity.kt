package com.example.captureprofessor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.material.icons.filled.Home
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.captureprofessor.ui.ListOfClasses
import com.example.captureprofessor.ui.ReviewActivity
import com.example.captureprofessor.ui.theme.CaptureProfessorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CaptureProfessorTheme {
                // A surface container using the 'background' color from the theme
                Test()
            }
        }
    }
}

@Composable
fun Test(modifier: Modifier = Modifier) {
    Scaffold(
        modifier = Modifier
            .fillMaxSize(),
        topBar = {
            MyTopAppBar(
                onAddClicked = {  }
            )
        }
    ) {paddingValues ->
        Column (
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
        ){
            //実機またはエミュでテストするときはここに自身で作成した関数をおいてください
            //pushするときはaddの対象から外すかここから消しておいて
            var navController = rememberNavController()
            NavHost(
                navController = navController,
                startDestination = NavigationDestination.ListOfClass.name
            ){
                composable(route = NavigationDestination.ListOfClass.name){
                    ListOfClasses(
                        onClassClicked = {
                            navController.navigate(route = NavigationDestination.ClassEvaluation.name)
                        }
                    )
                }
                composable(route = NavigationDestination.ClassEvaluation.name) {
                    ReviewActivity()
                }
            }
        }
    }
}

enum class NavigationDestination{
    ListOfClass,
    ClassEvaluation,
}


@Composable
fun MyTopAppBar(
    modifier: Modifier = Modifier,
    onAddClicked: () -> Unit
) {
    TopAppBar (
        modifier = modifier,
        title = { Text(text = "Lectures") },
        backgroundColor = Color(255,255,255),
        navigationIcon = {
//            if(currentScreen == Home) {
                Icon(imageVector = Icons.Filled.Home, contentDescription = "HOME")
//            }
        },
        actions = {
            IconButton(onClick = onAddClicked) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "ADD")
            }
        }
    )

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CaptureProfessorTheme {
        Test()
    }
}