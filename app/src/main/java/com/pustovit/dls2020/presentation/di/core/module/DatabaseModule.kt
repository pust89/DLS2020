package com.pustovit.dls2020.presentation.di.core.module

import android.content.Context
import com.pustovit.dls2020.data.db.AppDatabase
import com.pustovit.dls2020.data.db.DatabaseFeedDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabaseFeedDao(appDatabase: AppDatabase): DatabaseFeedDao {
        return appDatabase.databaseFeedDao()
    }


    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return AppDatabase.getInstance(context.applicationContext)
    }

}