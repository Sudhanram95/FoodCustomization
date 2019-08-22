package com.sudhan.synupproject.splash.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ResponseModel: Serializable {
    @SerializedName("variants")
    lateinit var variants: VariantsModel
}