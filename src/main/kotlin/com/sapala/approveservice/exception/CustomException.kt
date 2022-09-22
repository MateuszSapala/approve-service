package com.sapala.approveservice.exception

class CustomException(val status: Int, message: String) : Exception(message) {
    val body: MessageResponse get() = MessageResponse(status, message!!)
}