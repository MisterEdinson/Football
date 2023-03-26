package com.example.football.data.host.table

import com.google.gson.annotations.SerializedName

data class GeneralTable(

	@field:SerializedName("area")
	val area: Area? = null,

	@field:SerializedName("season")
	val season: Season? = null,

	@field:SerializedName("competition")
	val competition: Competition? = null,

	@field:SerializedName("filters")
	val filters: Filters? = null,

	@field:SerializedName("standings")
	val standings: List<StandingsItem?>? = null
)