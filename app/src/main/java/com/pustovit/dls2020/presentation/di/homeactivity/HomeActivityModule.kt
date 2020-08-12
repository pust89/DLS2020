package com.pustovit.dls2020.presentation.di.homeactivity

import com.pustovit.dls2020.domain.GetFeedsUseCase
import com.pustovit.dls2020.presentation.home.HomeActivityViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class HomeActivityModule {

    @Provides
    @HomeActivityScope
    fun providesHomeActivityViewModelFactory(
        getFeedsUseCase: GetFeedsUseCase
    ): HomeActivityViewModelFactory {
        return HomeActivityViewModelFactory(
            getFeedsUseCase
        )
    }

}