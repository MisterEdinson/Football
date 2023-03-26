package com.example.football.data.host.table

import com.google.gson.annotations.SerializedName

data class Filters(

	@field:SerializedName("season")
	val season: String? = null
)