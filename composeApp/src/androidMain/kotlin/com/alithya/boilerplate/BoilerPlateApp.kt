package com.alithya.boilerplate

import android.app.Application
import com.alithya.boilerplate.di.KoinInit
import com.alithya.boilerplate.di.androidModule
import com.russhwolf.settings.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

/**
 * created by Josue Lubaki
 * date : 2023-12-04
 * version : 1.0.0
 */

class BoilerPlateApp : Application() {
        override fun onCreate() {
            super.onCreate()

            KoinInit().init {
                androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
                androidContext(androidContext = this@BoilerPlateApp)
                modules(
                    listOf(
                        androidModule,
                    ),
                )
            }
        }
}