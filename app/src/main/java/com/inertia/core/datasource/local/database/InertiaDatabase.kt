package com.inertia.core.datasource.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.inertia.core.datasource.local.dao.BencanaDao
import com.inertia.core.datasource.local.entity.BencanaEntity

@Database(
    entities = [BencanaEntity::class], version = 1)
abstract class InertiaDatabase : RoomDatabase(){

    abstract fun bencanaDao(): BencanaDao

}