package com.pustovit.dls2020.presentation.di.core.module

import android.content.Context
import com.pustovit.dls2020.data.db.DatabaseFeedDao
import com.pustovit.dls2020.data.pref.AppPreferences
import com.pustovit.dls2020.data.repository.datasource.LocalDataSource
import com.pustovit.dls2020.data.repository.datasourceimpl.LocalDataSourceImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class LocalDataModule {

    @Provides
    @Singleton
    fun providesLocalDataSource(
        databaseFeedDao: DatabaseFeedDao,
        context: Context,
        appPreferences: AppPreferences
    ): LocalDataSource {
        return LocalDataSourceImpl(databaseFeedDao, context, appPreferences)
    }
}