package studio.dipdev.ahal.data.restAPI

import io.reactivex.Completable
import io.reactivex.Single


public open class AhalRestApiRxHelper<T>(private val entity: Class<T>) : AhalRestApiHelper<T>() {

    fun <TResponse> processResponse(upstream: Single<TResponse>): Single<TResponse> {
        return upstream
                .onErrorResumeNext { throwable -> Single.error(errorHandler.processError(throwable, entity)) }
    }

    fun <TResponse> processListResponse(upstream: Single<List<TResponse>>): Single<List<TResponse>> {
        return upstream
                .onErrorResumeNext { throwable -> Single.error(errorHandler.processError(throwable, entity)) }
    }

    fun processResponse(upstream: Completable): Completable {
        return upstream
                .onErrorResumeNext { throwable -> Completable.error(errorHandler.processError(throwable, entity)) }
    }

}