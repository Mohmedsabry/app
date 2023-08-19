package com.example.monitorapp.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.monitorapp.pojo.Post
import com.example.monitorapp.screens.viewmodels.HomeVM
import kotlinx.coroutines.flow.collectLatest

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    val viewModel = HomeVM()
    var list by remember {
        mutableStateOf(emptyList<Post>())
    }
    LaunchedEffect(key1 = "") {
        viewModel.flow.collectLatest {
            list = it
        }
    }
    LazyColumn {
        items(list) {
            Card(
                shape = CardDefaults.outlinedShape,
                elevation = CardDefaults.cardElevation(10.dp),
                colors = CardDefaults.cardColors(Color.LightGray),
                onClick = {
                    println("$it hi")
                },
                modifier = Modifier.padding(10.dp)
            ) {
                Text(text = it.userId.toString())
                Text(text = it.id.toString())
                Text(text = it.body)
                Text(text = it.title)
            }
        }
    }
}