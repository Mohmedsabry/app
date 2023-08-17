package com.example.monitorapp.screens

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.monitorapp.AppContext
import com.example.monitorapp.screens.viewmodels.LoginVM
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Login(navController: NavController) {
    val viewModel = LoginVM()
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var name by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        OutlinedTextField(value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(text = "name")
            })
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "password")
            },
            visualTransformation = PasswordVisualTransformation()
        )
        Row {
            Button(onClick = {
                if (name.isNotEmpty() && password.isNotEmpty()) {
                    viewModel.viewModelScope.launch {
                        viewModel.flow.collectLatest {
                            println(it)
                            if (viewModel.checkLogin(name, password, it)) {
                                Toast.makeText(
                                    AppContext.contextApp,
                                    "login success",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        }
                    }
                }
            }) {
                Text(text = "login")
            }
            Spacer(modifier = Modifier.width(10.dp))
            Button(onClick = {
                navController.navigate(Screens.SingUpScreen) {
                    launchSingleTop
                }
            }) {
                Text(text = "signup")
            }
        }
    }
}