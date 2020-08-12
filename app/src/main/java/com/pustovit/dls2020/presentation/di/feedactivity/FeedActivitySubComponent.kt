package com.pustovit.dls2020.presentation.di.feedactivity

import com.pustovit.dls2020.presentation.feed.FeedActivity
import dagger.Subcomponent

@Subcomponent(modules = [FeedActivityModule::class])
@FeedActivityScope
interface FeedActivitySubComponent {

    fun injectFeedActivity(feedActivity: FeedActivity)

    @Subcomponent.Factory
    interface Factory{
        fun create(): FeedActivitySubComponent
    }
}