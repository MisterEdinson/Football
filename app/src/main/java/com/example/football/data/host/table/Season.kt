package com.example.football.data.host.table

import com.google.gson.annotations.SerializedName

data class Season(

	@field:SerializedName("winner")
	val winner: Any? = null,

	@field:SerializedName("currentMatchday")
	val currentMatchday: Int? = null,

	@field:SerializedName("endDate")
	val endDate: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("startDate")
	val startDate: String? = null
)