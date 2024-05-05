
package com.example.dogsapi.ui.theme.screen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Dog
import com.example.inventory.network.DogApi
import kotlinx.coroutines.launch
import java.io.IOException


sealed interface MarsUiState {
    data class Success(val photos: List<Dog>) : MarsUiState
    object Error : MarsUiState
    object Loading : MarsUiState
}

class DogViewModel : ViewModel() {

    var marsUiState: MarsUiState by mutableStateOf(MarsUiState.Loading)
        private set


    init {
        getDogPhotos()
    }

    fun getDogPhotos() {
        viewModelScope.launch {
            marsUiState = try {
                val listResult = DogApi.retrofitService.getPhotos()
                MarsUiState.Success(listResult)
            } catch (e: IOException) {
                MarsUiState.Error
            }
        }
    }
}
