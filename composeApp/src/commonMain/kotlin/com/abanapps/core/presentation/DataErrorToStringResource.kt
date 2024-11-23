package com.abanapps.core.presentation

import bookshelf.composeapp.generated.resources.Res
import bookshelf.composeapp.generated.resources.error_disk_full
import bookshelf.composeapp.generated.resources.error_no_internet
import bookshelf.composeapp.generated.resources.error_request_timeout
import bookshelf.composeapp.generated.resources.error_too_many_requests
import bookshelf.composeapp.generated.resources.error_unknown
import bookshelf.composeapp.generated.resources.search_results
import com.abanapps.core.DataError

fun DataError.toUitText():UiText{

    val stringRes = when(this){
        DataError.Local.DISK_FULL -> Res.string.error_disk_full
        DataError.Local.UNKNOWN -> Res.string.error_unknown
        DataError.Remote.REQUEST_TIMEOUT -> Res.string.error_request_timeout
        DataError.Remote.TOO_MANY_REQUESTS -> Res.string.error_too_many_requests
        DataError.Remote.NO_INTERNET -> Res.string.error_no_internet
        DataError.Remote.SERVER_ERROR -> Res.string.error_unknown
        DataError.Remote.SERIALIZATION ->Res.string.search_results
        DataError.Remote.UNKNOWN -> Res.string.error_unknown
    }

    return UiText.StringResourceId(stringRes)

}