package com.example.football.data.host.news

import com.google.gson.annotations.SerializedName

data class NewsGeneral(

	@field:SerializedName("totalResults")
	val totalResults: Int? = null,

	@field:SerializedName("articles")
	val articles: List<ArticlesItem?>? = null,

	@field:SerializedName("status")
	val status: String? = null
)