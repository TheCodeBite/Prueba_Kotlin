package com.example.playingnow.modelos

import com.google.gson.annotations.SerializedName

data class DatesMovie(
    @SerializedName("maximum") var maximum: String,
    @SerializedName("minimum") var minimum: String
)