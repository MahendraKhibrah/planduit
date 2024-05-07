package com.pens.planduit.domain.di

import com.pens.planduit.domain.repositories.ArticleRepository
import com.pens.planduit.domain.repositories.DictionaryRepository
import com.pens.planduit.domain.repositories.GeneralCalculationRepository
import com.pens.planduit.domain.repositories.RatingRepository
import com.pens.planduit.domain.repositories.TestingRepository
import com.pens.planduit.domain.repositories.ZakatRepository
import com.pens.planduit.domain.usecases.GetAgricultureZakatUsecase
import com.pens.planduit.domain.usecases.GetArticleDetailUsecase
import com.pens.planduit.domain.usecases.GetArticlesUsecase
import com.pens.planduit.domain.usecases.GetBudgetCalculationUsecase
import com.pens.planduit.domain.usecases.GetDictionaryDetailUsecase
import com.pens.planduit.domain.usecases.GetDictionaryUsecase
import com.pens.planduit.domain.usecases.GetGoldPriceUsecase
import com.pens.planduit.domain.usecases.GetIncomeZakatCalculationUsecase
import com.pens.planduit.domain.usecases.GetInvestmentRequestUsecase
import com.pens.planduit.domain.usecases.GetQuestionProfileRiskUsecase
import com.pens.planduit.domain.usecases.GetRatingStatusUsecase
import com.pens.planduit.domain.usecases.GetRicePriceUsecase
import com.pens.planduit.domain.usecases.GetTradingZakatUsecase
import com.pens.planduit.domain.usecases.SaveInvestmentRequestUsecase
import com.pens.planduit.domain.usecases.SaveRatingStatusUsecase
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

    @Provides
    @Singleton
    fun provideGetRicePriceUseSase(repository: ZakatRepository): GetRicePriceUsecase = GetRicePriceUsecase(repository)

    @Provides
    @Singleton
    fun provideGetAgricultureZakatUsecase(repository: ZakatRepository) : GetAgricultureZakatUsecase = GetAgricultureZakatUsecase(repository)

    @Provides
    @Singleton
    fun provideGetTradingZakatUsecase(repository: ZakatRepository) : GetTradingZakatUsecase = GetTradingZakatUsecase(repository)

    @Singleton
    fun provideGetRatingStatusUsecase(repository: RatingRepository) : GetRatingStatusUsecase = GetRatingStatusUsecase(repository)

    @Singleton
    fun provideSaveRatingStatusUsecase(repository: RatingRepository) : SaveRatingStatusUsecase = SaveRatingStatusUsecase(repository)

    @Provides
    @Singleton
    fun provideGetDictionaryUsecase(repository: DictionaryRepository) : GetDictionaryUsecase = GetDictionaryUsecase(repository)

    @Provides
    @Singleton
    fun provideGetDictionaryDetailUsecase(repository: DictionaryRepository) : GetDictionaryDetailUsecase = GetDictionaryDetailUsecase(repository)

    @Provides
    fun provideGetArticleUsecase(repository: ArticleRepository) : GetArticlesUsecase = GetArticlesUsecase(repository)

    @Provides
    @Singleton
    fun provideGetArticleDetailUsecase(repository: ArticleRepository) : GetArticleDetailUsecase = GetArticleDetailUsecase(repository)
}