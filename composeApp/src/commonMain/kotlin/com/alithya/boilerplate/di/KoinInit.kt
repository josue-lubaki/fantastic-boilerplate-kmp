package com.alithya.boilerplate.di

import com.alithya.boilerplate.features.login.di.loginModule
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

class KoinInit {

    companion object {
        val allModules = listOf(
            commonModule(),
            platformModule(),
            loginModule()
        )
    }

    fun init(appDeclaration: KoinAppDeclaration = {}): Koin {
        return startKoin {
            modules(allModules)
            appDeclaration()
        }.koin
    }
}