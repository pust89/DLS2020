package com.pustovit.dls2020.data.repository

import com.pustovit.dls2020.data.db.asDomainModel
import com.pustovit.dls2020.data.repository.datasource.LocalDataSource
import com.pustovit.dls2020.domain.Feed
import com.pustovit.dls2020.domain.FeedRepository

class FeedRepositoryImpl(private val localDataSource: LocalDataSource) : FeedRepository {

    override suspend fun getFeeds(): List<Feed> {
        return localDataSource.getDatabaseFeeds().asDomainModel()
    }

    override suspend fun getFeedById(id: Int): Feed? {
        return localDataSource.getDatabaseFeedById(id).asDomainModel()
    }
}