package com.hmh.hamyeonham.core.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sample(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val sample: String
)
