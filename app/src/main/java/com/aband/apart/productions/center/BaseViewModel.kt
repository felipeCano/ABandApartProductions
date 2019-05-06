package com.aband.apart.productions.center

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aband.apart.productions.control.model.local.SerieLocal
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

import java.util.*

open class BaseViewModel : ViewModel() {

    val liveData = MutableLiveData<List<SerieLocal>>()
    private val disposables = CompositeDisposable()

    protected fun  addDisposable(observable: Observable<List<SerieLocal>>){
        disposables.add(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
            }
            .subscribe {
                liveData.postValue(it)
            }
        )
    }
}