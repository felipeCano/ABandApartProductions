package com.aband.apart.productions.control.model.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "serie")
data class SerieLocal(
    @PrimaryKey
    val id: String,
    val title: String
)