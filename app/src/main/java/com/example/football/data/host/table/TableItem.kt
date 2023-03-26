package com.example.football.data.host.table

import com.google.gson.annotations.SerializedName

data class TableItem(

	@field:SerializedName("goalsFor")
	val goalsFor: Int? = null,

	@field:SerializedName("form")
	val form: String? = null,

	@field:SerializedName("lost")
	val lost: Int? = null,

	@field:SerializedName("won")
	val won: Int? = null,

	@field:SerializedName("playedGames")
	val playedGames: Int? = null,

	@field:SerializedName("position")
	val position: Int? = null,

	@field:SerializedName("team")
	val team: Team? = null,

	@field:SerializedName("draw")
	val draw: Int? = null,

	@field:SerializedName("goalsAgainst")
	val goalsAgainst: Int? = null,

	@field:SerializedName("goalDifference")
	val goalDifference: Int? = null,

	@field:SerializedName("points")
	val points: Int? = null
)