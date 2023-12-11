package com.alithya.boilerplate.features.login.di

import com.alithya.boilerplate.features.login.data.remote.LoginService
import com.alithya.boilerplate.features.login.data.repository.LoginRepositoryImpl
import com.alithya.boilerplate.features.login.data.repository.datasource.LoginRemoteDataSource
import com.alithya.boilerplate.features.login.data.repository.datasourceimpl.LoginRemoteDataSourceImpl
import com.alithya.boilerplate.features.login.domain.repository.LoginRepository
import com.alithya.boilerplate.features.login.domain.usecases.LoginUseCase
import com.alithya.boilerplate.features.login.presentation.LoginViewModel
import com.alithya.boilerplate.features.settings.SettingsScreenModel
import org.koin.dsl.module

/**
 * created by Josue Lubaki
 * date : 2023-12-06
 * version : 1.0.0
 */

fun loginModule() = module {

    single<LoginService> { LoginService() }

    single<LoginRemoteDataSource> {
        LoginRemoteDataSourceImpl(
            service = get(),
            settingsRepository = get()
        )
    }

    single<LoginRepository> { LoginRepositoryImpl(remoteDataSource = get()) }

    single<LoginUseCase> { LoginUseCase(repository = get()) }

    single<LoginViewModel> {
        LoginViewModel(
            useCase = get(),
            dispatcher = get()
        )
    }
}