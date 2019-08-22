package com.sudhan.synupproject.splash.presenter

import com.sudhan.synupproject.splash.model.ResponseModel

interface ISplashPresenter {
    fun getAllVariantsApi()
    fun onGetVariantsApiSuccess(response:ResponseModel)
    fun onGetVariantsApiFailure(errorMessage: String?)
}