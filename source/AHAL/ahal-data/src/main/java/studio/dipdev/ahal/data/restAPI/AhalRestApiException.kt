package studio.dipdev.ahal.data.restAPI


data class AhalRestApiException<T>(val message: String?, val cause: Throwable?, val apiException: T) {

    val error = ApiError(message, cause)

    class ApiError(message: String?, cause: Throwable?) : RuntimeException(message, cause)

}
