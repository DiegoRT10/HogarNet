
package com.example.inventory.ui.ItemRoom

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.inventory.data.Item
import com.example.inventory.data.ItemsRepository
import com.example.inventory.ui.item.ItemDetailsDestination
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ItemRoomViewModel(
    savedStateHandle: SavedStateHandle,
    itemsRepository: ItemsRepository) : ViewModel() {

    private val itemUbicacion: String = checkNotNull(savedStateHandle[ItemRoomDestination.itemUbicacionArg])

    val itemRoomUiState: StateFlow<ItemRoomUiState> =
        itemsRepository.getAllItemsRoom(itemUbicacion).map { ItemRoomUiState(it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = ItemRoomUiState()
            )

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class ItemRoomUiState(val itemList: List<Item> = listOf())
