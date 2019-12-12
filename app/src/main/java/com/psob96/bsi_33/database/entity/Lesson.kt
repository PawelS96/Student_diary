package com.psob96.bsi_33.database.entity

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class Lesson(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var date: Long = 0
) {
    @Ignore
    constructor(date: Long) : this(0, date)
}
