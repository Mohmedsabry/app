package com.example.monitorapp.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.monitorapp.AppContext
import com.example.monitorapp.R
import com.example.monitorapp.pojo.User
import com.example.monitorapp.screens.viewmodels.SingUpVM
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUp(navController: NavController) {
    val viewModel = SingUpVM()
    Column(
        Modifier
            .fillMaxSize()
            .padding(10.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by remember {
            mutableStateOf("")
        }
        var password by remember {
            mutableStateOf("")
        }
        var age by remember {
            mutableStateOf("")
        }
        var showPassword by remember {
            mutableStateOf(true)
        }
        var showErrorName by remember {
            mutableStateOf(false)
        }
        var showErrorPassword by remember {
            mutableStateOf(false)
        }
        var showErrorAge by remember {
            mutableStateOf(false)
        }

        OutlinedTextField(
            value = name,
            onValueChange = {
                name = it
            },
            label = {
                Text(text = "name")
            },
            isError = showErrorName and name.isEmpty()
        )
        OutlinedTextField(
            value = password,
            onValueChange = {
                password = it
            },
            label = {
                Text(text = "password")
            },
            visualTransformation = if (showPassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                Icon(painter = if (showPassword) painterResource(id = R.drawable.baseline_lock_24) else painterResource(
                    id = R.drawable.baseline_lock_open_24
                ),
                    contentDescription = "show password", Modifier.clickable {
                        showPassword = showPassword.not()
                    })
            },
            isError = showErrorPassword and password.isEmpty()
        )
        OutlinedTextField(
            value = age,
            onValueChange = {
                age = it
            },
            label = {
                Text(text = "age")
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            isError = showErrorAge and age.isEmpty()
        )
        Button(onClick = {
            if (name.isNotEmpty() && password.isNotEmpty() && age.isNotEmpty()) {
                populateDataAndNavigate(viewModel, name, password, age, navController)
            }
            if (name.isEmpty()) {
                showErrorName = true
            }
            if (password.isEmpty()) {
                showErrorPassword = true
            }
            if (age.isEmpty()) {
                showErrorAge = true
            }
        }) {
            Text(text = "singUp")
        }
    }
}


private fun populateDataAndNavigate(
    viewModel: SingUpVM,
    name: String,
    password: String,
    age: String,
    navController: NavController
) {
    viewModel.viewModelScope.launch {
        (viewModel).insertDB(
            User(
                name = name,
                password = password,
                age = age.toInt()
            )
        )
        viewModel.flow.collectLatest {
            println(it)
            it?.let {
                if (it) {
                    navController.navigate(Screens.LoginScreen) {
                        popUpTo(navController.graph.startDestinationId)
                        launchSingleTop
                    }
                } else {
                    Toast.makeText(
                        AppContext.contextApp,
                        "user already exit",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }
}