package com.psob96.bsi_33.database

import android.content.Context
import androidx.room.*
import com.psob96.bsi_33.database.dao.GradeDAO
import com.psob96.bsi_33.database.dao.LessonDAO
import com.psob96.bsi_33.database.dao.PresenceDAO

import com.psob96.bsi_33.database.dao.StudentDAO
import com.psob96.bsi_33.database.entity.Grade
import com.psob96.bsi_33.database.entity.Lesson
import com.psob96.bsi_33.database.entity.Presence
import com.psob96.bsi_33.database.entity.Student

@Database(entities = [Student::class, Presence::class, Lesson::class, Grade::class],
    version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class DatabaseAccess : RoomDatabase() {

    companion object {

        var TEST_MODE = true

        private val DB_NAME = "db"

        private var instance: DatabaseAccess? = null

        fun getInstance(context: Context): DatabaseAccess {

            if (TEST_MODE) {
                instance = Room.inMemoryDatabaseBuilder(context.applicationContext, DatabaseAccess::class.java)
                    .allowMainThreadQueries()
                    .build()
            } else if (instance == null) {

                instance = Room.databaseBuilder(context.applicationContext, DatabaseAccess::class.java, DB_NAME)
                    .allowMainThreadQueries()
                    .build()
            }

            return instance as DatabaseAccess
        }
    }

    abstract fun studentDAO(): StudentDAO
    abstract fun gradeDAO(): GradeDAO
    abstract fun lessonDAO(): LessonDAO
    abstract fun presenceDAO(): PresenceDAO

}