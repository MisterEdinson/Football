package com.example.football.data.host.model

import com.google.gson.annotations.SerializedName

data class CompetitionsItem(

	@field:SerializedName("area")
	val area: Area? = null,

	@field:SerializedName("lastUpdated")
	val lastUpdated: String? = null,

	@field:SerializedName("code")
	val code: String? = null,

	@field:SerializedName("currentSeason")
	val currentSeason: CurrentSeason? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("numberOfAvailableSeasons")
	val numberOfAvailableSeasons: Int? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("emblem")
	val emblem: String? = null,

	@field:SerializedName("plan")
	val plan: String? = null
)