package com.beltranbetania.postsjson.data.model

import com.google.gson.annotations.SerializedName

data class CommentModel (
    @SerializedName("id") val id: Int,
    @SerializedName("postId") val postId: Int,
    @SerializedName("name") val name: String,
    @SerializedName("email") val email: String,
    @SerializedName("body") val body: String

)