package dev.mcallisaya.techchallenge.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.mcallisaya.techchallenge.data.repository.RickMortyRepository
import dev.mcallisaya.techchallenge.data.repository.RickMortyRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindRickMortyRepository(
        rickMortyRepositoryImpl: RickMortyRepositoryImpl
    ): RickMortyRepository

}