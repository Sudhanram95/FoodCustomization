package com.sudhan.synupproject.customization.presenter

import com.sudhan.synupproject.splash.model.ExcludeModel

interface ICustomizationPresenter {
    fun onRenderPizzaCustomization()
    fun onGetExclusionMap(groupId:Int, variantId:Int): HashMap<String, ArrayList<ExcludeModel>>
    fun onNotifyAdapter(selectedMap:HashMap<String, HashMap<String, ArrayList<ExcludeModel>>>)
}