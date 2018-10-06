package studio.dipdev.ahal.data.restAPI

open class AhalRestApiHelper<T> {

   protected val errorHandler: IAhalRestApiErrorHandler<T> = AhalRestApiErrorHandler()
}
