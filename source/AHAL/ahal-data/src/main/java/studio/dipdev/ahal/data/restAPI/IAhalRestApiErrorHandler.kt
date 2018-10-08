package studio.dipdev.ahal.data.restAPI

open interface IAhalRestApiErrorHandler<T> {
    fun processError(throwable: Throwable, entity: Class<T>): Throwable
}
