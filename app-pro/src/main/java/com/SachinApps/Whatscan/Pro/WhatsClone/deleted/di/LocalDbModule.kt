package com.SachinApps.Whatscan.Pro.WhatsClone.deleted.di

import android.app.Application
import androidx.room.Room
import com.SachinApps.Whatscan.Pro.WhatsClone.deleted.data.database.Database
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDbModule {

    @Provides
    @Singleton
    fun provideAppDatabase(application: Application): Database {
        return Room
            .databaseBuilder(application, Database::class.java, "database")
            .fallbackToDestructiveMigrationFrom()
            .build()
    }
}