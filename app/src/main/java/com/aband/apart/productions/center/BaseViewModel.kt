package com.aband.apart.productions.center

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.aband.apart.productions.control.model.local.SerieLocal
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

import java.util.*

open class BaseViewModel : ViewModel() {

    val liveData = MutableLiveData<List<SerieLocal>>()
    val liveDataDetail = MutableLiveData<SerieLocal>()
    private val disposables = CompositeDisposable()

    protected fun  addDisposable(observable: Observable<List<SerieLocal>>){
        val disposables1 : Disposable = observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
            }
            .subscribe ({
                liveData.postValue(it)
            },{
                Log.d("holi","holi2")
            })
        disposables.add(
            disposables1
        )
    }

    protected fun  addDisposableDetail(observable: Observable<SerieLocal>){
        disposables.add(observable.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe{
            }
            .subscribe ({
                liveDataDetail.postValue(it)
                Log.d("addDisposableDetail", it.toString())
            },{
                Log.d("holiDetail","holiDetail")
            })
        )
    }

    override fun onCleared() {
        disposables.clear()
    }
}