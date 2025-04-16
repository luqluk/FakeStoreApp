package com.android.core.database.rocket

import androidx.room.Database
import androidx.room.RoomDatabase
import com.android.core.database.rocket.LaunchInfoDao
import com.android.core.database.rocket.LaunchInfoEntity

@Database(
    entities = [LaunchInfoEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SpaceXLaunchesDatabase : RoomDatabase() {
    abstract val dao: LaunchInfoDao

    companion object {
        const val DATABASE_NAME = "spacex_launches_db"
    }
}
