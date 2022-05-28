package com.jmm.womensafety.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.jmm.womensafety.models.ContactModel

@Database(
    entities = [ContactModel::class], version = 3,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun contactDao(): ContactDao

}