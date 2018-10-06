package studio.dipdev.ahal.data.restAPI

interface IAhalRestApiErrorHandler<T> {
    fun processError(throwable: Throwable, entity: Class<T>): Throwable
}
