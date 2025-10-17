package com.filkom.mycv2.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

data class LoginState(
    val nim: String = "",
    val nama: String = "",
    val isLoading: Boolean = false,
    val error: String? = null,
    val isLoginSuccess: Boolean = false
)

class LoginViewModel : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    fun updateNim(newNim: String) {
        state = state.copy(nim = newNim, error = null)
    }

    fun updateNama(newNama: String) {
        state = state.copy(nama = newNama, error = null)
    }

    fun login() {
        state = state.copy(isLoading = true, error = null, isLoginSuccess = false)

        if (state.nim.isBlank() || state.nama.isBlank()) {
            state = state.copy(
                isLoading = false,
                error = "NIM dan Nama tidak boleh kosong."
            )
            return
        }

        viewModelScope.launch {
            try {
                delay(1500)

                state = if (state.nim == "205150201111001" && state.nama.equals("Budi", ignoreCase = true)) {
                    state.copy(isLoading = false, isLoginSuccess = true)
                } else {
                    state.copy(
                        isLoading = false,
                        error = "NIM atau Nama salah/tidak terdaftar."
                    )
                }
            } catch (e: Exception) {
                state = state.copy(
                    isLoading = false,
                    error = "Terjadi kesalahan: ${e.message}"
                )
            }
        }
    }

    fun resetLoginSuccess() {
        state = state.copy(isLoginSuccess = false)
    }
}