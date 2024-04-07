package com.pens.planduit.data.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import com.google.gson.Gson
import com.pens.planduit.data.apis.GeneralCalculationApi
import com.pens.planduit.data.apis.RatingApi
import com.pens.planduit.data.apis.TestingApi
import com.pens.planduit.data.apis.ZakatApi
import com.pens.planduit.data.repositories.GeneralCalculationRepositoryImpl
import com.pens.planduit.data.repositories.RatingRepositoryImpl
import com.pens.planduit.data.repositories.TestingRepositoryImpl
import com.pens.planduit.data.repositories.ZakatRepositoryImpl
import com.pens.planduit.data.sharedPreferences.GeneralCalculationPref
import com.pens.planduit.data.sharedPreferences.RatingPref
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import com.pens.planduit.domain.repositories.RatingRepository
import com.pens.planduit.domain.repositories.TestingRepository
import com.pens.planduit.domain.repositories.ZakatRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
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
            .baseUrl("https://backoffice.planduit.my.id/api/v1/")
            .client(client)
            .build()

    @Singleton
    @Provides
    fun provideGson(): Gson = Gson()

    @Singleton
    @Provides
    fun provideClient(@ApplicationContext context: Context): OkHttpClient {
        val builder = OkHttpClient.Builder()
            .readTimeout(60L, TimeUnit.SECONDS)
            .connectTimeout(60L, TimeUnit.SECONDS)
            .writeTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(ChuckerInterceptor(context))

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

    @Singleton
    @Provides
    fun provideGeneralCalculationApi(retrofit: Retrofit): GeneralCalculationApi = retrofit.create(GeneralCalculationApi::class.java)

    @Singleton
    @Provides
    fun provideGeneralCalculationPref(@ApplicationContext context: Context): GeneralCalculationPref = GeneralCalculationPref(context)

    @Singleton
    @Provides
    fun provideGeneralCalculationRepository(api : GeneralCalculationApi, sharedPref : GeneralCalculationPref): GeneralCalculationRepository = GeneralCalculationRepositoryImpl(api,sharedPref)

    @Singleton
    @Provides
    fun provideZakatApi(retrofit: Retrofit) : ZakatApi = retrofit.create(ZakatApi::class.java)

    @Singleton
    @Provides
    fun provideZakatRepository(api : ZakatApi) : ZakatRepository = ZakatRepositoryImpl(api)

    @Singleton
    @Provides
    fun provideRatingPref(@ApplicationContext context: Context): RatingPref = RatingPref(context)

    @Singleton
    @Provides
    fun provideRatingApi(retrofit: Retrofit) : RatingApi = retrofit.create(RatingApi::class.java)

    @Singleton
    @Provides
    fun provideRatingRepository(pref : RatingPref, api: RatingApi) : RatingRepository = RatingRepositoryImpl(pref, api)
}