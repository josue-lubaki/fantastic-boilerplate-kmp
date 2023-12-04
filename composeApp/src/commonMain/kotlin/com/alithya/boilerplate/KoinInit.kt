package com.alithya.boilerplate

import com.alithya.boilerplate.di.commonModule
import com.alithya.boilerplate.di.platformModule
import org.koin.core.Koin
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

class KoinInit {
    fun init(appDeclaration: KoinAppDeclaration = {}): Koin {
        return startKoin {
            modules(
                listOf(
                    platformModule(),
                    commonModule(),
                ),
            )
            appDeclaration()
        }.koin
    }
}