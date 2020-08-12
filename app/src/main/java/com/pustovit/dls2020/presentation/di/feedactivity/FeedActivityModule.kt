package com.pustovit.dls2020.presentation.di.feedactivity

import com.pustovit.dls2020.domain.GetFeedByIdUseCase
import com.pustovit.dls2020.presentation.feed.FeedActivityViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class FeedActivityModule {

    @Provides
    @FeedActivityScope
    fun provideFeedActivityViewModelFactory(getFeedByIdUseCase: GetFeedByIdUseCase): FeedActivityViewModelFactory {
        return FeedActivityViewModelFactory(getFeedByIdUseCase)
    }
}