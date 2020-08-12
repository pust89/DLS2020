package com.pustovit.dls2020.domain

class GetFeedByIdUseCase(private val feedRepository: FeedRepository) {
    suspend fun execute(id: Int) = feedRepository.getFeedById(id)
}