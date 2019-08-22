package com.sudhan.synupproject.splash.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class VariationModel : Serializable{
    @SerializedName("name")
    lateinit var name: String
    @SerializedName("price")
    var price: Int = 0
    @SerializedName("default")
    var default: Int = 0
    @SerializedName("id")
    var id: Int = 0
    @SerializedName("inStock")
    var inStock: Int = 0
    @SerializedName("isVeg")
    var isVeg: Int = 0

    var isEnabled:Boolean = true
}