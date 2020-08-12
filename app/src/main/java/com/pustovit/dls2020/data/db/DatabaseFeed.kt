package com.pustovit.dls2020.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.pustovit.dls2020.domain.Feed

@Entity(tableName = "feed_table")
data class DatabaseFeed(
    @PrimaryKey(autoGenerate = false) var id: Int,
    var imgId: Int,
    var title: String,
    var items: String
) {
}

fun DatabaseFeed.asDomainModel(): Feed {
    return Feed(
        id = this.id,
        imgId = this.imgId,
        title = this.title,
        items = this.items.split("\n")
    )
}

fun List<DatabaseFeed>.asDomainModel(): List<Feed> {
    return map {
        Feed(
            id = it.id,
            imgId = it.imgId,
            title = it.title,
            items = it.items.split("\n")
        )
    }
}