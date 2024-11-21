package com.abanapps.bookshelf

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform