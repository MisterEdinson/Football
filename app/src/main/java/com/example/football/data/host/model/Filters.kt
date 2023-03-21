package com.example.football.data.host.model

import com.google.gson.annotations.SerializedName

data class Filters(

	@field:SerializedName("client")
	val client: String? = null
)