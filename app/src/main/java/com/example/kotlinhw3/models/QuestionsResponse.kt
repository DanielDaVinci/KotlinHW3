package com.example.kotlinhw3.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class QuestionsResponse(
    @SerializedName("data") var data: ArrayList<Item> = arrayListOf()
) : java.io.Serializable
{
    @Parcelize
    data class Item(
        @SerializedName("id") var id: Int = -1,
        @SerializedName("title") var title: String = "",
        @SerializedName("text") var text: String = "",
        @SerializedName("status") var status: String = "",
        @SerializedName("profile_id") var profile_id: Int = -1,
    ) : Parcelable
}