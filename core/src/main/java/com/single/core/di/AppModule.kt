package com.single.core.di

import android.content.Context
import androidx.room.Room
import com.single.core.data.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun providesTaskRepositoryImpl(database: AppDatabase): com.single.core.domain.repository.TaskRepository {
        return com.single.core.data.TaskRepositoryImpl(database)
    }
    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context, AppDatabase::class.java,
            "todo.db"
        ).build()
    }
}