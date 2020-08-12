package com.pustovit.dls2020.presentation.splash

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.*
import com.pustovit.dls2020.SHOW_AD
import com.pustovit.dls2020.databinding.ActivitySplashBinding
import com.pustovit.dls2020.presentation.home.HomeActivity
import com.pustovit.dls2020.presentation.privacypolicy.PPActivity
import kotlinx.coroutines.*

class SplashActivity : AppCompatActivity() {

    private var mShowAd: Boolean = true

    private lateinit var binding: ActivitySplashBinding

    private lateinit var mInterstitialAd: InterstitialAd

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showAd(SHOW_AD)

        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBehaviorActivity()
    }

    private fun showAd(show: Boolean) {
        mShowAd = show
    }

    private fun startHomeActivity() {
        val intent = HomeActivity.getIntent(this@SplashActivity)
        startActivity(intent)
    }

    private fun setupBehaviorActivity() {

        binding.tvPp.setOnClickListener {
            startActivity(PPActivity.getIntent(this@SplashActivity))
            finish()
        }

        if (mShowAd) {
            initInterstitial()
            lifecycleScope.launch {
                var isInterstitialAdShowed:Boolean =false
                    withTimeout(5000) {
                        launch(Dispatchers.IO) {
                            delay(4000)
                            withContext(Dispatchers.Main) {
                                if (mInterstitialAd.isLoaded) {
                                    mInterstitialAd.show()
                                    isInterstitialAdShowed = true
                                }
                            }
                        }
                    }
                if(!isInterstitialAdShowed){startHomeActivity() }
            }
        } else {
            lifecycleScope.launch(Dispatchers.IO) {
                delay(1500)
                startHomeActivity()
            }
        }
    }

    private fun initInterstitial() {
        MobileAds.initialize(this) {}
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())

        mInterstitialAd.adListener = object : AdListener() {
            override fun onAdClosed() {
                startHomeActivity()
            }
        }
    }
}