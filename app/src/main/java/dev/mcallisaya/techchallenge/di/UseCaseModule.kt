package dev.mcallisaya.techchallenge.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mcallisaya.techchallenge.domain.use_case.GetRickMortyUseCase
import dev.mcallisaya.techchallenge.domain.use_case.GetRickMortyUseCaseImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class UseCaseModule {
    @Binds
    @Singleton
    abstract fun bindGetRickMortyUseCase(
        getRickMortyUseCaseImpl: GetRickMortyUseCaseImpl
    ): GetRickMortyUseCase
}