package com.pustovit.dls2020.presentation.feed

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.DisplayMetrics
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.gms.ads.*
import com.pustovit.dls2020.R
import com.pustovit.dls2020.SHOW_AD
import com.pustovit.dls2020.databinding.ActivityFeedBinding
import com.pustovit.dls2020.presentation.di.Injector
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.Duration
import javax.inject.Inject

class FeedActivity : AppCompatActivity() {

    private var mShowAd: Boolean = true

    @Inject
    protected lateinit var feedActivityViewModelFactory: FeedActivityViewModelFactory

    private lateinit var viewModel: FeedActivityViewModel
    private lateinit var binding: ActivityFeedBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_feed)
        binding.lifecycleOwner = this
        (application as Injector).createFeedActivitySubComponent().injectFeedActivity(this)
        viewModel = ViewModelProvider(
            this,
            feedActivityViewModelFactory
        ).get(FeedActivityViewModel::class.java)
        binding.viewModel = viewModel
        val selectedId = intent.getIntExtra(SELECTED_ID, 0)

        viewModel.loadFeedById(selectedId)
        showAd(SHOW_AD)
        observeLiveData()
    }

    private fun showAd(show: Boolean) {
        mShowAd = show
        viewModel.showAd = mShowAd
        if (show) MobileAds.initialize(this) {}
    }

    private fun observeLiveData() {
        viewModel.feedItems.observe(this, Observer {
            if (it != null) {
                binding.tvItem.text = it
                if (mShowAd) {
                    lifecycleScope.launch(Dispatchers.IO) {
                        showBanner()
                    }
                }
            }
        })

        viewModel.changeRightButtonAssignment.observe(this, Observer {
            if (it) {
                binding.btnRight.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_menu_24
                    )
                )
                binding.btnRight.setOnClickListener { finish() }
            } else {
                binding.btnRight.setImageDrawable(
                    ContextCompat.getDrawable(
                        this,
                        R.drawable.ic_arrow_right_24
                    )
                )
                binding.btnRight.setOnClickListener { viewModel.rightBtnClick() }
            }

        })

        viewModel.changeLeftButtonAssignment.observe(this, Observer {
            if (it) {
                binding.btnLeft.setOnClickListener { finish() }

            } else {
                binding.btnLeft.setOnClickListener { viewModel.leftBtnClick() }
            }
        })

        viewModel.feedIllustrationId.observe(this, Observer {
            Picasso.get()
                .load(it)
                .into(binding.ilustration)
        })

        if (mShowAd) {
            viewModel.showInterstitialAd.observe(this, Observer {
                if (it) {
                    if (mInterstitialAd?.isLoaded == true) {
                        mInterstitialAd?.show()
                    }
                } else {
                    initInterstitial()
                }
            })

        }
    }

    companion object {
        private const val SELECTED_ID = "SELECTED_ID"
        fun getIntent(context: Context, id: Int): Intent {
            val intent: Intent = Intent(context, FeedActivity::class.java)
            intent.putExtra(SELECTED_ID, id)
            return intent
        }
    }


    /*
      Вся реклама ниже
*/

    // Determine the screen width (less decorations) to use for the ad width.
    // If the ad hasn't been laid out, default to the full screen width.
    private suspend fun getAdSize(): AdSize {
        val display = this.windowManager.defaultDisplay
        val outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)

        val density = outMetrics.density

        var adWidthPixels = binding.adViewContainer.width.toFloat()
        if (adWidthPixels == 0f) {
            adWidthPixels = outMetrics.widthPixels.toFloat()
        }

        val adWidth = (adWidthPixels / density).toInt()
        return AdSize.getCurrentOrientationAnchoredAdaptiveBannerAdSize(this, adWidth)
    }

    private var initialLayoutComplete = false
    private var adView: AdView? = null

    private suspend fun showBanner() {
        // Initialize the Mobile Ads SDK.
        if (adView != null) {
            val newRequest = AdRequest.Builder().build()
            withContext(Dispatchers.Main) { adView!!.loadAd(newRequest) }
            return
        }

        // Create an ad request.
        val adRequest = AdRequest.Builder().build()

        adView = AdView(this)
        val AD_UNIT_ID = "ca-app-pub-3940256099942544/9214589741"
        adView?.adUnitId = AD_UNIT_ID
        adView?.adSize = getAdSize()

        // Since we're loading the banner based on the adContainerView size, we need to wait until this
        // view is laid out before we can get the width.
        withContext(Dispatchers.Main) {
            binding.adViewContainer.removeAllViews()
            binding.adViewContainer.addView(
                adView
            )
            binding.adViewContainer.viewTreeObserver.addOnGlobalLayoutListener {
                if (!initialLayoutComplete) {
                    initialLayoutComplete = true
                    // Start loading the ad in the background.
                    adView?.loadAd(adRequest)

                }
            }
        }


    }


    // Called when leaving the activity
    override fun onPause() {
        adView?.pause()
        super.onPause()
    }

    // Called when returning to the activity
    override fun onResume() {
        super.onResume()
        adView?.resume()
    }

    // Called before the activity is destroyed
    override fun onDestroy() {
        adView?.destroy()
        super.onDestroy()
    }

    private  var mInterstitialAd: InterstitialAd? = null

    private fun initInterstitial() {
        if(mInterstitialAd == null){
            mInterstitialAd = InterstitialAd(this)
            mInterstitialAd!!.adUnitId = "ca-app-pub-3940256099942544/1033173712"
//            mInterstitialAd!!.adListener = object:AdListener(){
//                override fun onAdFailedToLoad(adError: LoadAdError) {
//                    //Toast.makeText(this@FeedActivity,"FailedToLoad", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onAdClosed() {
//                    //Toast.makeText(this@FeedActivity,"AdClosed", Toast.LENGTH_SHORT).show()
//                }
//
//                override fun onAdLoaded() {
//                    //Toast.makeText(this@FeedActivity,"AdLoaded", Toast.LENGTH_SHORT).show()
//
//                }
//            }
            val adRequest = AdRequest.Builder().build()
            mInterstitialAd!!.loadAd(adRequest)
        } else{
            val adRequest = AdRequest.Builder().build()
            mInterstitialAd!!.loadAd(adRequest)
        }

    }

}