package com.example.kotlinhw3.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class ProfileResponse(
    @SerializedName("data") var data: Item
) : java.io.Serializable
{
    @Parcelize
    data class Item(
        @SerializedName("id") var id: Int = -1,
        @SerializedName("nickname") var nickname: String = "",
        @SerializedName("user") var user_id: Int = -1,
    ) : Parcelable
}