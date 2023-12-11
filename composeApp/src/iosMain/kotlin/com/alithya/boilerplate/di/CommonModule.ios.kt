package com.alithya.boilerplate.di

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual fun dispatcher(): CoroutineDispatcher = Dispatchers.IO