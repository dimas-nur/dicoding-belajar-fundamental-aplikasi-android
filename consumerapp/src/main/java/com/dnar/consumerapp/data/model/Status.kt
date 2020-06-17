package com.dnar.consumerapp.data.model

// Resource class for status; Keyword : Resource
class Status<T>(val status: StatusType, val data: T?, val message: String?) {

    enum class StatusType {
        SUCCESS, ERROR
    }

    companion object {
        fun <T> success(data: T?): Status<T> {
            return Status(StatusType.SUCCESS, data, null)
        }

        fun <T> error(message: String?, data: T?): Status<T> {
            return Status(StatusType.ERROR, data, message)
        }
    }
}