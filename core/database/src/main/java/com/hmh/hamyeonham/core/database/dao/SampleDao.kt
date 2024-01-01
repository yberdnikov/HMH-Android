package com.hmh.hamyeonham.core.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.hmh.hamyeonham.core.database.model.Sample

@Dao
interface SampleDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertSampleList(entities: List<Sample>): List<Long>
}
