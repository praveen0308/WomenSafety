package com.jmm.womensafety.local

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CacheModule {

    @Provides
    @Singleton
    fun provideLocalDb(@ApplicationContext context: Context):AppDatabase{
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "AppDatabase.db"
        ).fallbackToDestructiveMigration().build()
    }

    @Provides
    fun provideBannerDao(appDatabase: AppDatabase):ContactDao{
        return appDatabase.contactDao()
    }

}