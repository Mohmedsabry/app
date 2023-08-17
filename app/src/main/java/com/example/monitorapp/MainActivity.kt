package com.example.monitorapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.monitorapp.screens.DoNavigation
import com.example.monitorapp.ui.theme.MonitorAppTheme

class MainActivity : ComponentActivity() {
    //    private val viewModel:LoginVM by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MonitorAppTheme {
                DoNavigation()
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    MonitorAppTheme {

    }
}