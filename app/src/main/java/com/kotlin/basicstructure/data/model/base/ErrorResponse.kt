package com.kotlin.basicstructure.data.model.base

/**
 * Created by Sajid.
 */
class ErrorResponse(
    val message: String? = null, // this is the translated error shown to the user directly from the API
    val causes: Map<String, String>? = null //this is for errors on specific field on a form
)