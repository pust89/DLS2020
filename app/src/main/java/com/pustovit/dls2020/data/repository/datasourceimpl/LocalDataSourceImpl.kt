package com.pustovit.dls2020.data.repository.datasourceimpl

import android.content.Context
import com.pustovit.dls2020.R
import com.pustovit.dls2020.data.db.DatabaseFeed
import com.pustovit.dls2020.data.db.DatabaseFeedDao
import com.pustovit.dls2020.data.pref.AppPreferences
import com.pustovit.dls2020.data.repository.datasource.LocalDataSource
import com.pustovit.dls2020.data.utils.toItemsString

class LocalDataSourceImpl(private val databaseFeedDao: DatabaseFeedDao, private val context: Context,
                          private val appPreferences: AppPreferences) :
    LocalDataSource {

    override suspend fun getDatabaseFeeds(): List<DatabaseFeed>{
        lateinit var result:List<DatabaseFeed>
        if(appPreferences.checkLanguage()) {
            result = databaseFeedDao.getAllDatabaseFeeds()
            if(result.isNotEmpty()){
                return result
            } else{
                result = getDatabaseFeedFromResources()
                insertAllDatabaseFeeds(result)
                return databaseFeedDao.getAllDatabaseFeeds()
            }
        } else{
            clearTable()
            result = getDatabaseFeedFromResources()
            insertAllDatabaseFeeds(result)
            return databaseFeedDao.getAllDatabaseFeeds()
        }

    }

    override suspend fun getDatabaseFeedById(id: Int): DatabaseFeed {
        return databaseFeedDao.getFeedById(id)
    }

    override suspend fun insertAllDatabaseFeeds(list: List<DatabaseFeed>) {
        return databaseFeedDao.insertAllDatabaseFeeds(list)
    }

    override suspend fun clearTable() {
        databaseFeedDao.clearTable()
    }


    /**
     * Метод наполняет базу данных из ресурсов и возвращает список.
     *
     * @return
     */
    private suspend fun getDatabaseFeedFromResources(): List<DatabaseFeed> {
        val listToDatabase: MutableList<DatabaseFeed> = ArrayList<DatabaseFeed>()
        val resources = context.resources

        val df1 = DatabaseFeed(
            id = 1,
            title = resources.getString(R.string.feed_1_title),
            imgId = R.drawable.illustration_1,
            items = (resources.getStringArray(R.array.feed_1_items)).toItemsString()
        )
        listToDatabase.add(df1)

        val df2 = DatabaseFeed(
            id = 2,
            title = resources.getString(R.string.feed_2_title),
            imgId = R.drawable.illustration_1,
            items = (resources.getStringArray(R.array.feed_2_items)).toItemsString()
        )
        listToDatabase.add(df2)

        val df3 = DatabaseFeed(
            id = 3,
            title = resources.getString(R.string.feed_3_title),
            imgId = R.drawable.illustration_1,
            items = (resources.getStringArray(R.array.feed_3_items)).toItemsString()
        )
        listToDatabase.add(df3)

        val df4 = DatabaseFeed(
            id = 4,
            title = resources.getString(R.string.feed_4_title),
            imgId = R.drawable.illustration_2,
            items = (resources.getStringArray(R.array.feed_4_items)).toItemsString()
        )
        listToDatabase.add(df4)


        val df5 = DatabaseFeed(
            id = 5,
            title = resources.getString(R.string.feed_5_title),
            imgId = R.drawable.illustration_3,
            items = (resources.getStringArray(R.array.feed_5_items)).toItemsString()
        )
        listToDatabase.add(df5)

        val df6 = DatabaseFeed(
            id = 6,
            title = resources.getString(R.string.feed_6_title),
            imgId = R.drawable.illustration_4,
            items = (resources.getStringArray(R.array.feed_6_items)).toItemsString()
        )
        listToDatabase.add(df6)

        val df7 = DatabaseFeed(
            id = 7,
            title = resources.getString(R.string.feed_7_title),
            imgId = R.drawable.illustration_5,
            items = (resources.getStringArray(R.array.feed_7_items)).toItemsString()
        )
        listToDatabase.add(df7)

        val df8 = DatabaseFeed(
            id = 8,
            title = resources.getString(R.string.feed_8_title),
            imgId = R.drawable.illustration_6,
            items = (resources.getStringArray(R.array.feed_8_items)).toItemsString()
        )
        listToDatabase.add(df8)

        return listToDatabase
    }


}