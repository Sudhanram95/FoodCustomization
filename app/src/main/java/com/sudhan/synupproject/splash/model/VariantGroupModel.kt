package com.sudhan.synupproject.splash.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class VariantGroupModel : Serializable {
    @SerializedName("group_id")
    var groupId: Int = 0
    @SerializedName("name")
    lateinit var name: String
    @SerializedName("variations")
    lateinit var variationsList: List<VariationModel>
}