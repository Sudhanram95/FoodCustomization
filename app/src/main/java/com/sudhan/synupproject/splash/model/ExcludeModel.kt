package com.sudhan.synupproject.splash.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class ExcludeModel : Serializable {
    @SerializedName("group_id")
    var groupId: Int = 0
    @SerializedName("variation_id")
    var variationId: Int = 0
}