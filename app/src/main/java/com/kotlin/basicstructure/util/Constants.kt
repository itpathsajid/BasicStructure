package com.kotlin.basicstructure.util

/**
 * Created by Sajid.
 */

object Constants {
    const val AUTHORISATION_TOKEN = "563492ad6f91700001000001f80e25ee327343d19bee70ca076493b5"
    const val BASE_URL = "https://api.pexels.com/v1/"
    const val GET_USERS = "search?query=mustang"
    const val UNAUTHORISED = 401

    const val REGEX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$"
    const val PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{6,}"
    const val PASSWORD_PATTERN_WITH_ONE_SPECIAL_CHARS = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[@\$!%*#?&])[A-Za-z\\d@\$!%*#?&]{8,}\$"
    const val PERMISSION_REQUEST_CODE = 1001
}

