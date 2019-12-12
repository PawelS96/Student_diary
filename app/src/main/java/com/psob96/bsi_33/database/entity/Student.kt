package com.psob96.bsi_33.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Student(

    @PrimaryKey
    var index: Int = 0,
    var firstName: String = "",
    var lastName: String = ""
)


