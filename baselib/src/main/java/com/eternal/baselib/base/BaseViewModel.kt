package com.eternal.baselib.base

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.Observer
import com.eternal.baselib.event.SingleLiveEvent
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer


open class BaseViewModel<M :IModel>(application: Application) : AndroidViewModel(application), IViewModel,
    Consumer<Disposable> {
    protected lateinit var model: M
    protected var mCompositeDisposable: CompositeDisposable? = null
    val ui: UIChangeLiveData<Void>

    init {
        ui = UIChangeLiveData()
    }

    override fun onCleared() {
        super.onCleared()
        unDispose()
        model.onDestroy()
    }

    fun addDispose(disposable: Disposable) {
        if (mCompositeDisposable == null) {
            mCompositeDisposable = CompositeDisposable()
        }
        mCompositeDisposable?.add(disposable)
    }

    private fun unDispose() {
        mCompositeDisposable?.clear()
    }

    override fun accept(t: Disposable) {
        addDispose(t)
    }

    override fun onAny(owner: LifecycleOwner?, event: Lifecycle.Event?) {

    }

    override fun onCreate() {
    }

    override fun onDestroy() {
    }

    override fun onStart() {
    }

    override fun onStop() {
    }

    override fun onResume() {
    }

    override fun onPause() {
    }

    class UIChangeLiveData<T> : SingleLiveEvent<T>() {
        var showDialogEvent = SingleLiveEvent<String>()
        var dismissDialogEvent = SingleLiveEvent<Void>()
        var finishEvent = SingleLiveEvent<Void>()
        var onBackPressedEvent = SingleLiveEvent<Void>()
        var finishRefreshEvent = SingleLiveEvent<Void>()
        var finishLoadMoreEvent = SingleLiveEvent<Void>()
        override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
            super.observe(owner, observer)
        }
    }
}