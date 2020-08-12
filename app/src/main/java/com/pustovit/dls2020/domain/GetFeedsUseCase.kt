package com.pustovit.dls2020.domain

class GetFeedsUseCase (private val feedRepository: FeedRepository) {
    suspend fun execute() = feedRepository.getFeeds()
}