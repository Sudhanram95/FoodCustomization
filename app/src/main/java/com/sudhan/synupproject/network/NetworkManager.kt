package com.sudhan.synupproject.network

import android.content.Context
import com.sudhan.synupproject.splash.model.ResponseModel
import com.sudhan.synupproject.splash.presenter.ISplashPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class NetworkManager(var context: Context, var iSplashPresenter:ISplashPresenter) {
    var apiEndpoints:ApiEndpoint? = NetworkUtil.retrofitHelper(context)?.create(ApiEndpoint::class.java)

    fun getVariants() {
        apiEndpoints?.getVariants()
            ?.subscribeOn(Schedulers.io())
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribeWith(object : DisposableSingleObserver<ResponseModel>() {
                override fun onSuccess(response: ResponseModel) {
                    iSplashPresenter.onGetVariantsApiSuccess(response)
                }

                override fun onError(e: Throwable) {
                    iSplashPresenter.onGetVariantsApiFailure(e.message)
                }
            })
    }
}