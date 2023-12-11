package com.alithya.boilerplate.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

actual fun dispatcher(): CoroutineDispatcher = Dispatchers.IO