package com.pustovit.dls2020.presentation.di.core.module

import android.content.Context
import com.pustovit.dls2020.presentation.di.feedactivity.FeedActivitySubComponent
import com.pustovit.dls2020.presentation.di.homeactivity.HomeActivitySubComponent
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(subcomponents = [HomeActivitySubComponent::class, FeedActivitySubComponent::class])
class AppModule(private val context: Context) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Context {
        return context.applicationContext
    }
}