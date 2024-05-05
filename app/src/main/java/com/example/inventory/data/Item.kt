
package com.example.inventory.data

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "items")
data class Item(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val image: String,
    val categoria: String,
    val ubicacion: String,
    val label: String,
    val note: String,
    val date: String,
    val price: Double,
    val quantity: Int
)
