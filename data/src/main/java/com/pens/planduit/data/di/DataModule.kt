package com.pens.planduit.data.di

import com.google.gson.Gson
import com.pens.planduit.data.apis.TestingApi
import com.pens.planduit.data.repositories.TestingRepositoryImpl
import com.pens.planduit.domain.repositories.TestingRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DataModule {
    @Provides
    @Singleton
    fun provideRetrofit(
        gson: Gson,
        client: OkHttpClient,
        ): Retrofit =
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .baseUrl("https://mocki.io/v1/")
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideClient(): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)

        val logger = HttpLoggingInterceptor().apply { level = HttpLoggingInterceptor.Level.BODY }
        builder.addInterceptor(logger)

        return builder.build()
    }

    @Singleton
    @Provides
    fun provideTestingApi(retrofit: Retrofit): TestingApi = retrofit.create(TestingApi::class.java)

    @Singleton
    @Provides
    fun provideTestingRepository(api : TestingApi): TestingRepository = TestingRepositoryImpl(api)

}