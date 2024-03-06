package com.pens.planduit.domain.di

import com.pens.planduit.domain.repositories.TestingRepository
import com.pens.planduit.domain.usecases.TestingUsecase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class DomainModule {
    @Provides
    @Singleton
    fun provideTestingUseSase(repository: TestingRepository): TestingUsecase = TestingUsecase(repository)
}