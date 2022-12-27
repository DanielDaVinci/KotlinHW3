package com.example.kotlinhw3.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

data class AnswersResponse(
    @SerializedName("data") var data: ArrayList<Item> = arrayListOf()
) : java.io.Serializable
{
    @Parcelize
    data class Item(
        @SerializedName("id") var id: Int = -1,
        @SerializedName("text") var text: String = "a",
        @SerializedName("correct") var status: Boolean = false,
        @SerializedName("profile") var profile_id: Int = -1,
    ) : Parcelable
}