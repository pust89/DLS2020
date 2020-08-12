package com.pustovit.dls2020.presentation.home

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.ads.*
import com.pustovit.dls2020.R
import com.pustovit.dls2020.SHOW_AD
import com.pustovit.dls2020.databinding.ActivityHomeBinding
import com.pustovit.dls2020.domain.Feed
import com.pustovit.dls2020.presentation.di.Injector
import com.pustovit.dls2020.presentation.feed.FeedActivity
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {

    private var mShowAd: Boolean = true


    @Inject
    protected lateinit var homeActivityViewModelFactory: HomeActivityViewModelFactory

    private lateinit var viewModel: HomeActivityViewModel
    private lateinit var binding: ActivityHomeBinding

    private lateinit var feedAdapter: FeedAdapter
    private lateinit var mInterstitialAd: InterstitialAd


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        showAd(SHOW_AD)


        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        (application as Injector).createHomeActivitySubComponent().injectHomeActivity(this)
        viewModel = ViewModelProvider(this, homeActivityViewModelFactory).get(HomeActivityViewModel::class.java)
        binding.viewModel = viewModel
        setupRecyclerView(binding.recyclerView)


        viewModel.loadFeeds()
        observeLiveData()
    }

    private fun showAd(show:Boolean){
        mShowAd = show
    }

    private fun observeLiveData() {
        viewModel.titles.observe(this, Observer {
            if (it != null) {
                feedAdapter.submitList(it)
                viewModel.loadFeedsCompleted()
            }
        })
    }


    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, HomeActivity::class.java)
        }
    }

    override fun onBackPressed() {
        val intent = Intent(Intent.ACTION_MAIN)
        intent.addCategory(Intent.CATEGORY_HOME)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        startActivity(intent)
    }


    private fun setupRecyclerView(recyclerView: RecyclerView?) {

        feedAdapter = FeedAdapter(
            ItemClickListener<Feed> {
                val feedId = it.id
                if (mShowAd) {
                    mInterstitialAd.adListener = MyAdListener(feedId)

                    if (mInterstitialAd.isLoaded) {
                        mInterstitialAd.show()
                    } else {
                        startFeedActivity(feedId)
                    }
                } else {
                    startFeedActivity(feedId)
                }
            })
        recyclerView?.layoutManager = LinearLayoutManager(this)
        recyclerView.apply {
            this?.adapter = feedAdapter
        }
    }


    override fun onStart() {
        super.onStart()
        if (mShowAd) {
            initInterstitial()
        }
    }

    private fun initInterstitial() {
        MobileAds.initialize(this) {}
        mInterstitialAd = InterstitialAd(this)
        mInterstitialAd.adUnitId = "ca-app-pub-3940256099942544/1033173712"
        mInterstitialAd.loadAd(AdRequest.Builder().build())
    }

    private fun startFeedActivity(feedId: Int) {
        val intent = FeedActivity.getIntent(this@HomeActivity, feedId)
        startActivity(intent)
    }

    private inner class MyAdListener(val feedId: Int) : AdListener() {

        override fun onAdFailedToLoad(adError: LoadAdError) {
            startFeedActivity(feedId)
        }

        override fun onAdClosed() {
            startFeedActivity(feedId)
        }
    }
}