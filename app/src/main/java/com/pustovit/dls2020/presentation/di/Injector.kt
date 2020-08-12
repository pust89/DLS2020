package com.pustovit.dls2020.presentation.di

import com.pustovit.dls2020.presentation.di.feedactivity.FeedActivitySubComponent
import com.pustovit.dls2020.presentation.di.homeactivity.HomeActivitySubComponent

interface Injector {

    fun createHomeActivitySubComponent(): HomeActivitySubComponent

    fun createFeedActivitySubComponent(): FeedActivitySubComponent
}