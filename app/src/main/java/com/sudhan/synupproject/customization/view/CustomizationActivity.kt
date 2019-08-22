package com.sudhan.synupproject.customization.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.Button
import com.sudhan.synupproject.R
import com.sudhan.synupproject.customization.presenter.ICustomizationPresenter
import com.sudhan.synupproject.customization.presenter.CustomizationPresenter
import com.sudhan.synupproject.splash.model.ResponseModel

class CustomizationActivity : AppCompatActivity(), ICustomizationView {

    lateinit var iCustomizationPresenter: ICustomizationPresenter

    lateinit var rvVariant: RecyclerView
    lateinit var btnTotalAmount: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initializeView();
    }

    private fun initializeView() {
        rvVariant = findViewById(R.id.rv_variants)
        btnTotalAmount = findViewById(R.id.btn_pay)

        val responseModel = intent.getSerializableExtra("response") as ResponseModel
        iCustomizationPresenter = CustomizationPresenter(this@CustomizationActivity, this, responseModel)
        iCustomizationPresenter.onRenderPizzaCustomization()
    }

    override fun onRenderPizzaCustomizationResult(variantAdapter: VariantAdapter) {
        rvVariant.layoutManager = LinearLayoutManager(this@CustomizationActivity, LinearLayoutManager.VERTICAL, false)
        rvVariant.setAdapter(variantAdapter)
    }

    override fun updateTotalPrice(price: Int) {
        btnTotalAmount.text = "Total Amount - Rs.$price"
    }
}
