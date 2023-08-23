package com.example.myapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material.TextField

import androidx.compose.material.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue

import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
//import com.example.myapplication.GitViewModel

class MyView {
    companion object {
       var data:List<gitUser> by mutableStateOf(listOf())
        fun TakingListFromViewModel(Data: List<gitUser>) {
            data=Data

        }


        @Composable
        fun NamesListScreen(navController: NavHostController) {
            val textVal = remember {
                mutableStateOf(TextFieldValue(""))

            }

            Column {
                searchNamesList(textVal)
                NamesOfGitList(textVal, nameList = data)

            }
        }


        @Composable
        fun searchNamesList(textVal: MutableState<TextFieldValue>) {
            TextField(
                value = textVal.value,
                onValueChange = { textVal.value = it },
                placeholder = { Text(text = "Search  Name", color = Color.Black) },
                modifier = Modifier
                    .fillMaxWidth(),
                textStyle = TextStyle(Color.Black, fontSize = 18.sp),
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Filled.Search,
                        contentDescription = "Search",
                        Modifier
                            .padding(15.dp)
                            .size(24.dp)
                    )
                },
                trailingIcon = {
                    if (textVal.value != TextFieldValue("")) {
                        IconButton(onClick = {
                            textVal.value = TextFieldValue("")
                        }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Close, contentDescription = "Close",
                                Modifier
                                    .padding(15.dp)
                                    .size(24.dp)
                            )
                        }
                    }
                },
                singleLine = true,
                shape = RectangleShape,
                colors = TextFieldDefaults.textFieldColors(
                    textColor = Color.Black,
                    cursorColor = Color.Black,
                    leadingIconColor = Color.Black,
                    trailingIconColor = Color.Black,
                    backgroundColor = Color.White,
                    focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent


                )
            )
        }


        @Composable
        fun NamesOfGitList(textVal: MutableState<TextFieldValue>, nameList: List<gitUser>) {

            val searchText = textVal.value.text




            LazyColumn {
                if (searchText.isEmpty()) {
                    itemsIndexed(items = nameList) { index, item ->
                        NamItem(names = item)
                    }
                } else {

                    val listis: List<gitUser> =
                        nameList.filter { s -> s.login.take(1) == searchText.take(1) }
                    itemsIndexed(items = listis)
                    { index, item ->
                        NamItem( names= item)

                    }

                }
            }


        }


        @Composable
        fun NamItem(names: gitUser) {
            Card(
                modifier = Modifier
                    .padding(8.dp, 4.dp)
                    .fillMaxWidth()
                    .height(110.dp), shape = RoundedCornerShape(8.dp), elevation = 4.dp
            ) {
                Surface() {

                    Row(
                        Modifier
                            .padding(4.dp)
                            .fillMaxSize()
                    ) {

                        AsyncImage(

                            model = names.avatar_url,
                            contentDescription = null
                        )




                        Column(
                            verticalArrangement = Arrangement.Center,
                            modifier = Modifier
                                .padding(4.dp)
                                .fillMaxHeight()
                                .weight(0.8f)
                        ) {
                            Text(
                                text = names.login,
                                style = MaterialTheme.typography.displayLarge,
                                fontWeight = FontWeight.Bold
                            )

                            Text(
                                text = names.login,
                                style = MaterialTheme.typography.displayMedium,
                                maxLines = 2,
                                overflow = TextOverflow.Ellipsis
                            )

                        }
                    }
                }
            }

        }


    }
}



