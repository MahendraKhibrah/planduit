package com.pens.planduit.domain.di

import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import com.pens.planduit.domain.repositories.TestingRepository
import com.pens.planduit.domain.repositories.ZakatRepository
import com.pens.planduit.domain.usecases.GetBudgetCalculationUsecase
import com.pens.planduit.domain.usecases.GetGoldPriceUsecase
import com.pens.planduit.domain.usecases.GetIncomeZakatCalculationUsecase
import com.pens.planduit.domain.usecases.GetInvestmentRequestUsecase
import com.pens.planduit.domain.usecases.GetQuestionProfileRiskUsecase
import com.pens.planduit.domain.usecases.SaveInvestmentRequestUsecase
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

    @Provides
    @Singleton
    fun provideBudgetUseSase(repository: GeneralCalculationRepository): GetBudgetCalculationUsecase = GetBudgetCalculationUsecase(repository)

    @Provides
    @Singleton
    fun provideGetInvestmentRequestUseSase(repository: GeneralCalculationRepository): GetInvestmentRequestUsecase = GetInvestmentRequestUsecase(repository)

    @Provides
    @Singleton
    fun provideSaveInvestmentRequestUseSase(repository: GeneralCalculationRepository): SaveInvestmentRequestUsecase = SaveInvestmentRequestUsecase(repository)

    @Provides
    @Singleton
    fun provideGetQuestionProfileRiskUseSase(repository: GeneralCalculationRepository): GetQuestionProfileRiskUsecase = GetQuestionProfileRiskUsecase(repository)

    @Provides
    fun provideGetGoldPriceUseSase(repository: ZakatRepository): GetGoldPriceUsecase = GetGoldPriceUsecase(repository)

    @Provides
    @Singleton
    fun provideIncomeZakatCalculationUseSase(repository: ZakatRepository): GetIncomeZakatCalculationUsecase = GetIncomeZakatCalculationUsecase(repository)
}