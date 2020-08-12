package com.pustovit.dls2020.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pustovit.dls2020.domain.Feed
import com.pustovit.dls2020.domain.GetFeedsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeActivityViewModel(private val getFeedsUseCase: GetFeedsUseCase) : ViewModel() {


    private val _titles: MutableLiveData<List<Feed>?> = MutableLiveData()
    val titles: LiveData<List<Feed>?>
        get() = _titles

    fun loadFeeds() {
        viewModelScope.launch(Dispatchers.IO) {
            _titles.postValue(getFeedsUseCase.execute())
        }
    }

    fun loadFeedsCompleted(){
        _titles.postValue(null)
    }
}