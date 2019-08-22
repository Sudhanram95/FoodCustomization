package com.sudhan.synupproject.splash.presenter

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.util.Log
import com.google.gson.Gson
import com.sudhan.synupproject.customization.view.CustomizationActivity
import com.sudhan.synupproject.network.NetworkManager
import com.sudhan.synupproject.splash.model.ResponseModel
import com.sudhan.synupproject.splash.view.ISplashView

class SplashPresenter(var context: Context, var iSplashView: ISplashView) : ISplashPresenter{

    override fun getAllVariantsApi() {
        NetworkManager(context, this).getVariants()
    }

    override fun onGetVariantsApiSuccess(response: ResponseModel) {
        Log.v("SplashPresenter", "Response::::${Gson().toJson(response)}")
        val intent = Intent(context, CustomizationActivity::class.java)
        intent.putExtra("response", response)
        context.startActivity(intent)
        (context as Activity).finish()
    }

    override fun onGetVariantsApiFailure(errorMessage: String?) {
        iSplashView.onShowApiErrorResult()
    }
}