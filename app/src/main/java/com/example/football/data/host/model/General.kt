package com.example.football.data.host.model

import com.google.gson.annotations.SerializedName

data class General(

	@field:SerializedName("count")
	val count: Int? = null,

	@field:SerializedName("competitions")
	val competitions: List<CompetitionsItem?>? = null,

	@field:SerializedName("filters")
	val filters: Filters? = null
)