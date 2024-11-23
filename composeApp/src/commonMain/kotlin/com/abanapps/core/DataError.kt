package com.abanapps.core

import com.abanapps.core.domain.Error

sealed interface DataError:Error {

    enum class Remote:DataError{
        REQUEST_TIMEOUT,
        TOO_MANY_REQUESTS,
        NO_INTERNET,
        SERVER_ERROR,
        SERIALIZATION,
        UNKNOWN
    }

    enum class  Local:DataError{
        DISK_FULL,
        UNKNOWN
    }

}