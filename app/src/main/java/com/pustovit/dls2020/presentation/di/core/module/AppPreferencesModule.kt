package com.pustovit.dls2020.presentation.di.core.module

import android.content.Context
import com.pustovit.dls2020.data.pref.AppPreferences
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppPreferencesModule {

    @Provides
    @Singleton
    fun providesAppPreferences(context: Context):AppPreferences{
        return AppPreferences(context)
    }
}