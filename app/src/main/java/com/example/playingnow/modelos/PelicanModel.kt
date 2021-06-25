package com.example.playingnow.modelos

import com.google.gson.annotations.SerializedName


data class PelicanModel(
    @SerializedName("dates") var dates: List<String>,
    @SerializedName("page") var page: Int,
    @SerializedName("results") var results: List<ResultMovie>,
    @SerializedName("total_pages") var total_pages: Int,
    @SerializedName("total_results") var total_results: Int
)