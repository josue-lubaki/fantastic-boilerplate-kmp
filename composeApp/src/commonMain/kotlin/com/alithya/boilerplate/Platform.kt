package com.alithya.boilerplate

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform