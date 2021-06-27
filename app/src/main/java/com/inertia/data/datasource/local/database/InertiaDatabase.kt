package com.inertia.data.datasource.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.inertia.data.datasource.local.dao.BencanaDao
import com.inertia.data.datasource.local.entity.BencanaEntity
import dagger.Provides
import javax.inject.Singleton

@Database(
    entities = [BencanaEntity::class], version = 1)
abstract class InertiaDatabase : RoomDatabase(){

    abstract fun bencanaDao(): BencanaDao

}