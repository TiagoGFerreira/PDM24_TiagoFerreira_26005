package com.example.shop.di

import com.example.shop.data.repository.ProductRepositoryImpl
import com.example.shop.domain.repository.ProductRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ProductModule {

    @Provides
    fun provideProductRepository(): ProductRepository {
        return ProductRepositoryImpl()
    }
}