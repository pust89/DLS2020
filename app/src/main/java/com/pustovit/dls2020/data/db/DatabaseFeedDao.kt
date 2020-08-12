package com.pustovit.dls2020.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface DatabaseFeedDao {

    @Query("SELECT * FROM feed_table;")
    suspend fun getAllDatabaseFeeds(): List<DatabaseFeed>

    @Query("SELECT * FROM feed_table WHERE id =:feedId;")
    suspend fun getFeedById(feedId: Int): DatabaseFeed

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAllDatabaseFeeds(list: List<DatabaseFeed>)

    @Query("DELETE FROM feed_table")
    suspend fun clearTable()

}