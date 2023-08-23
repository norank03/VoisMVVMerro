package com.example.myapplication

import android.annotation.SuppressLint
import android.health.connect.datatypes.AppInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.ui.theme.Pink80
import  com.example.myapplication.Network.ApiCall
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import com.example.myapplication.MyView.Companion.NamesListScreen


import java.util.*
import com.example.myapplication.MyView
import com.example.myapplication.MyView.Companion.NamesOfGitList
import com.example.myapplication.MyView.Companion.data
import com.example.myapplication.ui.theme.MyApplicationTheme
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

import java.util.*

class MainActivity : ComponentActivity() {



    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {



                    Scaffold(topBar = { TopBar() }, backgroundColor = Pink80) {
                        githubNav()
                        //MovieList(movieList = data)


                    }
                }
            }



        }

    override fun onResume() {
        super.onResume()
        ApiCall.getsdata()
        GitViewModel.getNameList()

    }


    @Composable
    fun TopBar() {

        TopAppBar(
            title =
            {
                Text(
                    text = " User Name",
                    Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center
                )
            },
            backgroundColor = Color.Blue,
            contentColor = Color.White
        )


    }


    @Composable
    fun githubNav() {

        val navController = rememberNavController()
        NavHost(navController = navController, startDestination = "User Name") {
            composable("User Name") {
               NamesListScreen(navController)
            }
        }

    }




}