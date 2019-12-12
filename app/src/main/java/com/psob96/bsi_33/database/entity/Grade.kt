package com.psob96.bsi_33.database.entity

import androidx.room.*

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = Student::class,
            parentColumns = arrayOf("index"),
            childColumns = arrayOf("studentID"),
            onDelete = ForeignKey.CASCADE
        )
    ],
    indices= [Index(value = ["studentID"], name = "idxGradeStudent")]
)

data class Grade(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var value: Float = 0f,
    var studentID: Int = 0
) {
    @Ignore
    constructor(grade: Float, studentID: Int) : this(0, grade, studentID)
}
