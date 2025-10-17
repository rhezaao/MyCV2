package com.filkom.mycv2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class DaftarState(
    val nim: String = "",
    val nama: String = "",
    val email: String = "",
    val alamat: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isDaftarSuccess: Boolean = false
)

class DaftarViewModel : ViewModel() {

    var state by mutableStateOf(DaftarState())
        private set

    fun updateNim(newNim: String) { state = state.copy(nim = newNim, error = null) }
    fun updateNama(newNama: String) { state = state.copy(nama = newNama, error = null) }
    fun updateEmail(newEmail: String) { state = state.copy(email = newEmail, error = null) }
    fun updateAlamat(newAlamat: String) { state = state.copy(alamat = newAlamat, error = null) }

    fun simpan() {
        state = state.copy(isLoading = true, error = null, isDaftarSuccess = false)

        if (state.nim.isBlank() || state.nama.isBlank()) {
            state = state.copy(
                isLoading = false,
                error = "NIM dan Nama wajib diisi."
            )
            return
        }

        viewModelScope.launch {
            try {
                delay(1500)

                state = state.copy(isLoading = false, isDaftarSuccess = true)

            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    error = "Gagal menyimpan data: ${e.message}"
                )
            }
        }
    }

    fun resetDaftarSuccess() {
        state = state.copy(isDaftarSuccess = false)
    }
}