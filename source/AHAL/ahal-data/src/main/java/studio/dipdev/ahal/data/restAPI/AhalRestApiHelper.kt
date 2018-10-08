package studio.dipdev.ahal.data.restAPI

public open class AhalRestApiHelper<T> {

    protected val errorHandler: IAhalRestApiErrorHandler<T> = AhalRestApiErrorHandler()
}
