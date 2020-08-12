package com.pustovit.dls2020.presentation.feed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pustovit.dls2020.domain.GetFeedByIdUseCase

class FeedActivityViewModelFactory(private val getFeedByIdUseCase: GetFeedByIdUseCase) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FeedActivityViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FeedActivityViewModel(getFeedByIdUseCase) as T
        } else {
            throw Exception("Unknown viewModel ${modelClass::class.java.canonicalName}")
        }
    }
}