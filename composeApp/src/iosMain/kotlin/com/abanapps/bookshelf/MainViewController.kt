package com.abanapps.bookshelf

import androidx.compose.runtime.remember
import androidx.compose.ui.window.ComposeUIViewController
import com.abanapps.App
import com.abanapps.di.initKoin
import io.ktor.client.engine.darwin.Darwin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}