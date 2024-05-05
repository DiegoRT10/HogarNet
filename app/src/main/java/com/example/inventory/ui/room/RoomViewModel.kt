
package com.example.inventory.ui.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class RoomViewModel(itemsRepository: ItemsRepository) : ViewModel() {

    val roomUiState: StateFlow<RoomUiState> =
        itemsRepository.getUniqueLocations().map { locations ->
            RoomUiState(locations.mapIndexed { index, location ->
                Item(
                    id = index, // Genera un id único para cada ubicación
                    name = "",
                    description = "",
                    image = "",
                    categoria = "",
                    ubicacion = location,
                    label = "",
                    note = "",
                    date = "",
                    price = 0.0,
                    quantity = 0
                )
            })
        }.stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
            initialValue = RoomUiState()
        )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class RoomUiState(val itemList: List<Item> = listOf())
