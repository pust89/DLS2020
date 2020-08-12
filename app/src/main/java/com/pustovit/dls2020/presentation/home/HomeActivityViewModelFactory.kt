package com.pustovit.dls2020.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pustovit.dls2020.domain.GetFeedByIdUseCase
import com.pustovit.dls2020.domain.GetFeedsUseCase
import com.pustovit.dls2020.presentation.feed.FeedActivityViewModel

class HomeActivityViewModelFactory(private val getFeedsUseCase: GetFeedsUseCase): ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(HomeActivityViewModel::class.java)){
            @Suppress("UNCHECKED_CAST")
            return HomeActivityViewModel(getFeedsUseCase) as T
        }
        else{
            throw Exception("Unknown viewModel ${modelClass::class.java.canonicalName}")
        }
    }
}