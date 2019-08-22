package com.sudhan.synupproject.splash.view

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import com.sudhan.synupproject.R
import com.sudhan.synupproject.splash.presenter.ISplashPresenter
import com.sudhan.synupproject.splash.presenter.SplashPresenter

class SplashActivity : AppCompatActivity(), ISplashView {

    lateinit var iSplashPresenter: ISplashPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        iSplashPresenter = SplashPresenter(this@SplashActivity, this)
        iSplashPresenter.getAllVariantsApi()
    }

    override fun onShowApiErrorResult() {
        val dialog = Dialog(this, android.R.style.Theme_Holo_NoActionBar_Fullscreen)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setContentView(R.layout.error_dialog)
        val btnRetry = dialog.findViewById<Button>(R.id.btn_retry)
        btnRetry.setOnClickListener(View.OnClickListener {
            dialog.dismiss()
            iSplashPresenter.getAllVariantsApi()
        })
        dialog.show()
    }
}
