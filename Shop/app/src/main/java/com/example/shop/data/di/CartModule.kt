package com.example.shop.data.di

import com.example.shop.data.repository.AuthRepositoryImpl
import com.example.shop.data.repository.CartRepositoryImpl
import com.example.shop.domain.repository.AuthRepository
import com.example.shop.domain.repository.CartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object CartModule {

    @Provides
    fun provideCartRepository(): CartRepository {
        return CartRepositoryImpl()
    }
}
