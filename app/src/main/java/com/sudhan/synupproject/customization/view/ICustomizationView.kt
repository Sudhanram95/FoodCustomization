package com.sudhan.synupproject.customization.view

interface ICustomizationView {
    fun onRenderPizzaCustomizationResult(variantAdapter: VariantAdapter)
    fun updateTotalPrice(price: Int)
}