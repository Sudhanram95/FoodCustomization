package com.sudhan.synupproject.splash.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class VariantsModel : Serializable{
    @SerializedName("variant_groups")
    lateinit var variantGroup: ArrayList<VariantGroupModel>
    @SerializedName("exclude_list")
    lateinit var excludeList: ArrayList<ArrayList<ExcludeModel>>
}