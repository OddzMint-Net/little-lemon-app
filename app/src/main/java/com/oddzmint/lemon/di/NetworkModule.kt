/*
 * Copyright (c) 2026 OddzMint.
 * Licensed under the MIT License - see LICENSE file in the project root.
 */
package com.oddzmint.lemon.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(): HttpClient = HttpClient(Android) {
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
            json(
                Json { ignoreUnknownKeys = true },
                contentType = ContentType.Text.Plain
            )
        }
    }
}