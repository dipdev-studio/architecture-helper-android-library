package studio.dipdev.ahal.domain.utils.rx

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single

public open interface IAhalRxHelper {
    fun setup(upstream: Completable): Completable

    fun <T> setup(upstream: Observable<T>): Observable<T>

    fun <T> setup(upstream: Single<T>): Single<T>

    fun <T> setupList(upstream: Single<List<T>>): Single<List<T>>

    fun <T> setup(upstream: Flowable<T>): Flowable<T>
}
