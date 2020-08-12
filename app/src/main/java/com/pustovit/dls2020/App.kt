package com.pustovit.dls2020

import androidx.multidex.MultiDexApplication
import com.pustovit.dls2020.presentation.di.Injector
import com.pustovit.dls2020.presentation.di.core.AppComponent
import com.pustovit.dls2020.presentation.di.core.DaggerAppComponent
import com.pustovit.dls2020.presentation.di.core.module.AppModule
import com.pustovit.dls2020.presentation.di.feedactivity.FeedActivitySubComponent
import com.pustovit.dls2020.presentation.di.homeactivity.HomeActivitySubComponent
import com.yandex.metrica.YandexMetrica
import com.yandex.metrica.YandexMetricaConfig
import timber.log.Timber


const val SHOW_AD: Boolean = true

class App : MultiDexApplication(), Injector {

    private val APIkey: String = "65c7fc7a-6c6b-4cce-b15c-dfcfd5cb1902"
    private lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        setupTimber()
        appComponent = DaggerAppComponent
            .builder()
            .appModule(AppModule(this))
            .build()

        setupAppMetrica()

    }


    private fun setupAppMetrica() {
        // Creating an extended library configuration.

        // Creating an extended library configuration.
        val config = YandexMetricaConfig.newConfigBuilder(APIkey).build()
        // Initializing the AppMetrica SDK.
        // Initializing the AppMetrica SDK.
        YandexMetrica.activate(applicationContext, config)
        // Automatic tracking of user activity.
        // Automatic tracking of user activity.
        YandexMetrica.enableActivityAutoTracking(this)
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    override fun createHomeActivitySubComponent(): HomeActivitySubComponent {
        return appComponent.homeActivitySubComponent().create()
    }

    override fun createFeedActivitySubComponent(): FeedActivitySubComponent {
        return appComponent.feedActivitySubComponent().create()
    }
}