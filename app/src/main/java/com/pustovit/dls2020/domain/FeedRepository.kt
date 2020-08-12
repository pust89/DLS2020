package com.pustovit.dls2020.domain

interface FeedRepository {

    suspend fun getFeeds(): List<Feed>

    suspend fun getFeedById(id:Int): Feed?
}