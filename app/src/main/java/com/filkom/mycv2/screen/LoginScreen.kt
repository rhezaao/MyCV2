package com.filkom.mycv2.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.filkom.mycv2.viewmodel.LoginViewModel

@Composable
fun LoginScreen(
    onLogin: (nim: String, nama: String) -> Unit,
    onDaftar: () -> Unit,
    viewModel: LoginViewModel = viewModel()
) {
    val state = viewModel.state

    LaunchedEffect(state.isLoginSuccess) {
        if (state.isLoginSuccess) {
            onLogin(state.nim, state.nama)
            viewModel.resetLoginSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Login")

        OutlinedTextField(
            value = state.nim,
            onValueChange = { viewModel.updateNim(it) },
            label = { Text("NIM") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            enabled = !state.isLoading
        )

        OutlinedTextField(
            value = state.nama,
            onValueChange = { viewModel.updateNama(it) },
            label = { Text("Nama") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 12.dp),
            enabled = !state.isLoading
        )

        if (state.error != null) {
            Text(
                text = state.error,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.padding(top = 8.dp)
            )
        }

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 20.dp),
            onClick = { viewModel.login() },
            enabled = !state.isLoading
        ) {
            Text(if (state.isLoading) "LOADING..." else "LOGIN")
        }

        Button(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .padding(top = 8.dp),
            onClick = onDaftar,
            enabled = !state.isLoading
        ) { Text("DAFTAR") }
    }
}
