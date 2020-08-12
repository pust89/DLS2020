package com.pustovit.dls2020.presentation.di.core.module

import com.pustovit.dls2020.data.repository.FeedRepositoryImpl
import com.pustovit.dls2020.data.repository.datasource.LocalDataSource
import com.pustovit.dls2020.domain.FeedRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providesFeedRepository(localDataSource: LocalDataSource): FeedRepository{
        return FeedRepositoryImpl(localDataSource)
    }

}