package com.pustovit.dls2020.presentation.di.homeactivity

import com.pustovit.dls2020.presentation.home.HomeActivity
import dagger.Subcomponent

@HomeActivityScope
@Subcomponent(modules = [HomeActivityModule::class])
interface HomeActivitySubComponent {

    fun injectHomeActivity(homeActivity: HomeActivity)

    @Subcomponent.Factory
    interface Factory {
        fun create(): HomeActivitySubComponent
    }
}