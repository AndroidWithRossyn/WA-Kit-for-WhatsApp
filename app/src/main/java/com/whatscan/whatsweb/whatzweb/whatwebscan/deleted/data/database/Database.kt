package com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.data.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import com.whatscan.whatsweb.whatzweb.whatwebscan.deleted.data.model.Chat

@Database(
    entities = [Chat::class],
    version = 3,
    exportSchema = true,
    autoMigrations = [
        AutoMigration(from = 2, to = 3)
    ]
)

abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao
}