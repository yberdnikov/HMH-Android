package com.hmh.hamyeonham.core.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.hmh.hamyeonham.core.database.dao.SampleDao
import com.hmh.hamyeonham.core.database.model.Sample

@Database(entities = [Sample::class], version = 1, exportSchema = false)
abstract class HMHRoomDatabase : RoomDatabase() {
    abstract fun sampleDao(): SampleDao
}
