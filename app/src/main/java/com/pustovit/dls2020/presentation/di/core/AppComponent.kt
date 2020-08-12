package com.pustovit.dls2020.presentation.di.core

import com.pustovit.dls2020.presentation.di.core.module.*
import com.pustovit.dls2020.presentation.di.feedactivity.FeedActivitySubComponent
import com.pustovit.dls2020.presentation.di.homeactivity.HomeActivitySubComponent
import dagger.Component
import javax.inject.Singleton

@Component(
    modules = [AppModule::class,
        AppPreferencesModule::class,
        DatabaseModule::class,
        LocalDataModule::class,
        RepositoryModule::class,
        UseCaseModule::class]
)
@Singleton
interface AppComponent {

    fun homeActivitySubComponent(): HomeActivitySubComponent.Factory

    fun feedActivitySubComponent(): FeedActivitySubComponent.Factory
}