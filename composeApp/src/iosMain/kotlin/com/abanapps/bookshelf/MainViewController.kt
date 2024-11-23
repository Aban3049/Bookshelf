package com.abanapps.bookshelf

import androidx.compose.ui.window.ComposeUIViewController
import com.abanapps.book.app.App
import com.abanapps.di.initKoin

fun MainViewController() = ComposeUIViewController(
    configure = {
        initKoin()
    }
) {
    App()
}