package com.single.core.data.di

import android.content.Context
import androidx.room.Room
import com.single.core.data.database.AppDatabase
import com.single.core.data.database.TodoDao
import com.single.core.data.repository.ToDoRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


// Created by Nagaraju on 15/05/24.

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {


    @Provides
    @Singleton
    fun providesToDoRepositoryImpl(todoDao:TodoDao): com.single.core.domine.repository.ToDoRepository {
        return ToDoRepositoryImpl(todoDao)
    }

    @Provides
    @Singleton
    fun provideDto(database: AppDatabase):TodoDao{
        return database.todoDao
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