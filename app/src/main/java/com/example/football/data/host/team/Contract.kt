package com.example.football.data.host.team

import com.google.gson.annotations.SerializedName

data class Contract(

	@field:SerializedName("start")
	val start: Any? = null,

	@field:SerializedName("until")
	val until: Any? = null
)