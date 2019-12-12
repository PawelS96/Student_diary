package com.psob96.bsi_33

import android.content.Context
import androidx.test.InstrumentationRegistry
import com.psob96.bsi_33.database.DatabaseAccess
import org.junit.After
import org.junit.Before
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach

abstract class DatabaseTest {

    private lateinit var DB: DatabaseAccess

    protected fun getDB(): DatabaseAccess {
        return DB
    }

    private val appContext: Context = InstrumentationRegistry.getTargetContext()

    @Before
    fun initDatabase() {
        DB = DatabaseAccess.getInstance(appContext)
        beforeEach()
    }

    @After
    fun after() {
        DB.close()
    }

    abstract fun beforeEach()
}