package com.alithya.boilerplate.utils

import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier

/**
 * created by Josue Lubaki
 * date : 2023-12-01
 * version : 1.0.0
 */

fun debugBuild() {
    Napier.base(DebugAntilog())
}