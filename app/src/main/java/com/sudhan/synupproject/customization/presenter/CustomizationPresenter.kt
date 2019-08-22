package com.sudhan.synupproject.customization.presenter

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.sudhan.synupproject.customization.view.ICustomizationView
import com.sudhan.synupproject.customization.view.VariantAdapter
import com.sudhan.synupproject.splash.model.ExcludeModel
import com.sudhan.synupproject.splash.model.ResponseModel

class CustomizationPresenter(var context: Context, var iMainView: ICustomizationView, var responseModel: ResponseModel) : ICustomizationPresenter {

    lateinit var variantAdapter: VariantAdapter
    val allSelectedMap = HashMap<String, HashMap<String, ArrayList<ExcludeModel>>>()

    override fun onRenderPizzaCustomization() {
        variantAdapter = VariantAdapter(context, responseModel.variants.variantGroup, this)
        iMainView.onRenderPizzaCustomizationResult(variantAdapter)
    }

    override fun onGetExclusionMap(groupId:Int, variantId:Int): HashMap<String, ArrayList<ExcludeModel>> {
        val map = HashMap<String, ArrayList<ExcludeModel>>()
        val exclutionList = ArrayList<ExcludeModel>()
        for (list in responseModel.variants.excludeList) {
            for (excludeModel in list) {
                if(excludeModel.groupId == groupId && excludeModel.variationId == variantId) {
                    exclutionList.addAll(list)
                }
            }
            map.put(variantId.toString(), exclutionList)
        }
        return map
    }

    override fun onNotifyAdapter(selectedMap:HashMap<String, HashMap<String, ArrayList<ExcludeModel>>>) {
        var totalAdditionalPrice = 0
        allSelectedMap.putAll(selectedMap)
        for (entry in allSelectedMap.entries) {
            val groupId = entry.key //Selected group ID
            val variantMap = entry.value
             for (e in variantMap.entries) {
                val variantId = e.key //Selected variant ID
                val excludeList = e.value
                val selectedVariantList = responseModel.variants.variantGroup.get(groupId.toInt() - 1).variationsList
                for (model in selectedVariantList) {
                    if (model.id == variantId.toInt()) {
                        model.default = 1
                        totalAdditionalPrice += model.price
                    }
                }
                for (excludeModel in excludeList) {
                    if (groupId.equals(excludeModel.groupId.toString()) && variantId.equals(excludeModel.variationId.toString())) {
                        Log.e("Adapter", "Group::$groupId variant::$variantId")
                    } else {
                        val variantList = responseModel.variants.variantGroup.get(excludeModel.groupId - 1).variationsList
                        for (model in variantList) {
                            if (model.id == excludeModel.variationId) {
                                model.isEnabled = false
                            }
                        }
                    }
                }
            }
        }
//        variantAdapter.notifyDataSetChanged()
        iMainView.updateTotalPrice(totalAdditionalPrice + 100)
        onRenderPizzaCustomization()
    }

}