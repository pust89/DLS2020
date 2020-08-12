package com.pustovit.dls2020.presentation.di.core.module

import com.pustovit.dls2020.domain.FeedRepository
import com.pustovit.dls2020.domain.GetFeedByIdUseCase
import com.pustovit.dls2020.domain.GetFeedsUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class UseCaseModule {

    @Provides
    @Singleton
    fun providesGetFeedsUseCase(feedRepository: FeedRepository):GetFeedsUseCase{
        return GetFeedsUseCase(feedRepository)
    }

    @Provides
    @Singleton
    fun providesGetFeedByIdUseCase(feedRepository: FeedRepository):GetFeedByIdUseCase{
        return GetFeedByIdUseCase(feedRepository)
    }
}