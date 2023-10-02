package com.raju.unlimit.di

import com.raju.unlimit.data.remote.JokesApi
import com.raju.unlimit.data.repository.JokeRepositoryImpl
import com.raju.unlimit.domain.repository.JokeRepository
import com.raju.unlimit.common.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideJokesApi(): JokesApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(JokesApi::class.java)
    }

    @Provides
    @Singleton
    fun provideJokeRepository(api: JokesApi): JokeRepository {
        return JokeRepositoryImpl(api)
    }
}