package com.pustovit.dls2020.data.repository.datasource

import com.pustovit.dls2020.data.db.DatabaseFeed


interface LocalDataSource {

    suspend fun getDatabaseFeeds():List<DatabaseFeed>

    suspend fun getDatabaseFeedById(id:Int):DatabaseFeed

    suspend fun insertAllDatabaseFeeds(list:List<DatabaseFeed>)

    suspend fun clearTable()

}