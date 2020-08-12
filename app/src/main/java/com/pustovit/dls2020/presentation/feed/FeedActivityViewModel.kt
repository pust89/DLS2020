package com.pustovit.dls2020.presentation.feed

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pustovit.dls2020.domain.Feed
import com.pustovit.dls2020.domain.GetFeedByIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FeedActivityViewModel(private val getFeedByIdUseCase: GetFeedByIdUseCase) : ViewModel() {

    var showAd: Boolean = true

    private lateinit var feed: Feed

    private var countItems = 0
    private var currentItems = 0;


    private val _feedItems: MutableLiveData<String?> = MutableLiveData()
    val feedItems: LiveData<String?>
        get() = _feedItems

    private val _feedIllustrationId: MutableLiveData<Int> = MutableLiveData()
    val feedIllustrationId: LiveData<Int>
        get() = _feedIllustrationId

    fun loadFeedById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            feed = getFeedByIdUseCase.execute(id)!!
            countItems = feed.items.size
            _feedIllustrationId.postValue(feed.imgId)
            _feedItems.postValue(feed.items[currentItems])
            needToShowInterstitialAd(currentItems)
            if (currentItems == 0) {
                _changeLeftButtonAssignment.postValue(true)
            }
        }
    }

    fun rightBtnClick() {
        viewModelScope.launch(Dispatchers.IO) {
            if (currentItems < countItems - 1) {
                ++currentItems
            }

            if (currentItems < countItems) {
                _feedItems.postValue(feed.items[currentItems])
                if (showAd) needToShowInterstitialAd(currentItems)
            }


            if (currentItems == countItems - 1) {
                _changeRightButtonAssignment.postValue(true)
            }

            if (currentItems == 1) {
                _changeLeftButtonAssignment.postValue(false)
            }
        }
    }

    fun leftBtnClick() {
        viewModelScope.launch(Dispatchers.IO) {
            if (currentItems > 0) {
                --currentItems
            }

            if (currentItems < countItems) {
                _feedItems.postValue(feed.items[currentItems])
                if (showAd) needToShowInterstitialAd(currentItems)
            }

            if (currentItems == countItems - 2) {
                _changeRightButtonAssignment.postValue(false)
            }

            if (currentItems == 0) {
                _changeLeftButtonAssignment.postValue(true)
            }
        }
    }

    private val _changeRightButtonAssignment: MutableLiveData<Boolean> = MutableLiveData()
    val changeRightButtonAssignment: LiveData<Boolean>
        get() = _changeRightButtonAssignment


    private val _changeLeftButtonAssignment: MutableLiveData<Boolean> = MutableLiveData()
    val changeLeftButtonAssignment: LiveData<Boolean>
        get() = _changeLeftButtonAssignment


    private suspend fun needToShowInterstitialAd(current: Int) {
        if (current >1 && current % 2 == 0) {
            _showInterstitialAd.postValue(true)
        } else {
            _showInterstitialAd.postValue(false)
        }
    }

    private val _showInterstitialAd: MutableLiveData<Boolean> = MutableLiveData()
    val showInterstitialAd: LiveData<Boolean>
        get() = _showInterstitialAd

}