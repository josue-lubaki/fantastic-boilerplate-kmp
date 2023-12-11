package com.alithya.boilerplate.di

import com.alithya.boilerplate.features.login.di.loginModule
import org.koin.core.context.startKoin
import org.koin.dsl.module

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

fun initKoin() = module {
    startKoin {
        modules(KoinInit.allModules)
    }
}