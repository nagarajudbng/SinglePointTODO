package com.single.point.di

import android.content.Context
import androidx.room.Room
import com.single.point.core.data.database.AppDatabase
import com.single.point.feature_taskcreate.data.TaskRepositoryImpl
import com.single.point.feature_taskcreate.domine.repository.TaskRepository
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
    fun providesTaskRepositoryImpl(database:AppDatabase): TaskRepository {
        return TaskRepositoryImpl(database)
    }
    @Provides
    @Singleton
    fun providesAppDatabase(@ApplicationContext context: Context):AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "todo.db"
        ).build()
    }
}