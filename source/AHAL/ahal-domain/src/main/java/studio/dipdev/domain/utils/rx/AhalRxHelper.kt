package studio.dipdev.domain.utils.rx

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.OnLifecycleEvent
import com.annimon.stream.Stream
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import org.reactivestreams.Subscription
import java.util.*

class AhalRxHelper internal constructor(lifecycle: Lifecycle) : IAhalRxHelper, LifecycleObserver {
    private val subscriptionContainer = ArrayList<Subscription>()
    private var compositeDisposable: CompositeDisposable? = null

    init {
        lifecycle.addObserver(this)
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    internal fun stop() {
        //destroy();
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    internal fun destroy() {
        getCompositeDisposable()?.dispose()
        Stream.of(subscriptionContainer)
                .forEach { it.cancel() }
    }

    override fun setup(upstream: Completable): Completable {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { this.addCompositeDisposable(it) }
    }

    override fun <T> setup(upstream: Observable<T>): Observable<T> {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { this.addCompositeDisposable(it) }
    }

    override fun <T> setup(upstream: Single<T>): Single<T> {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { this.addCompositeDisposable(it) }
    }

    override fun <T> setupList(upstream: Single<List<T>>): Single<List<T>> {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { this.addCompositeDisposable(it) }
    }

    override fun <T> setup(upstream: Flowable<T>): Flowable<T> {
        return upstream
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { this.addCompositeDisposable(it) }
    }

    private fun addCompositeDisposable(subscription: Subscription) {
        subscriptionContainer.add(subscription)
    }

    private fun addCompositeDisposable(disposable: Disposable) {
        getCompositeDisposable()?.add(disposable)
    }

    private fun getCompositeDisposable(): CompositeDisposable? {
        if (compositeDisposable == null || compositeDisposable!!.isDisposed) {
            compositeDisposable = CompositeDisposable()
        }
        return compositeDisposable
    }
}