package com.example.football.data.host.table

import com.google.gson.annotations.SerializedName

data class Competition(

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("emblem")
	val emblem: String? = null
)