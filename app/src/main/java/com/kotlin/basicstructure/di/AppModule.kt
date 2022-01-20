package com.kotlin.basicstructure.di

import android.content.Context
import com.kotlin.basicstructure.util.PreferenceProvider
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Sajid.
 */

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun getPreferenceManager(@ApplicationContext appContext: Context) =
        PreferenceProvider(appContext)
}