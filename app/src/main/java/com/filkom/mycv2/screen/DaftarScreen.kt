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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.filkom.mycv2.viewmodel.DaftarViewModel

@Composable
fun DaftarScreen(
    onSimpan: (nim: String, nama: String, email: String, alamat: String) -> Unit,
    viewModel: DaftarViewModel = viewModel()
) {
    val state = viewModel.state

    LaunchedEffect(state.isDaftarSuccess) {
        if (state.isDaftarSuccess) {
            onSimpan(state.nim, state.nama, state.email, state.alamat)
            viewModel.resetDaftarSuccess()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(text = "Form Daftar")

        OutlinedTextField(
            value = state.nim,
            onValueChange = { viewModel.updateNim(it) },
            label = { Text("NIM") },
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            enabled = !state.isLoading
        )
        OutlinedTextField(
            value = state.nama,
            onValueChange = { viewModel.updateNama(it) },
            label = { Text("Nama") },
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            enabled = !state.isLoading
        )
        OutlinedTextField(
            value = state.email,
            onValueChange = { viewModel.updateEmail(it) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
            enabled = !state.isLoading
        )
        OutlinedTextField(
            value = state.alamat,
            onValueChange = { viewModel.updateAlamat(it) },
            label = { Text("Alamat") },
            modifier = Modifier.fillMaxWidth().padding(top = 12.dp),
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
            onClick = { viewModel.simpan() },
            modifier = Modifier.padding(top = 20.dp),
            enabled = !state.isLoading
        ) {
            Text(if (state.isLoading) "MENYIMPAN..." else "SIMPAN")
        }
    }
}