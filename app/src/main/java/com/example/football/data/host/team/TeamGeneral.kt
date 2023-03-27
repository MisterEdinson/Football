package com.example.football.data.host.team

import com.google.gson.annotations.SerializedName

data class TeamGeneral(

	@field:SerializedName("area")
	val area: Area? = null,

	@field:SerializedName("venue")
	val venue: String? = null,

	@field:SerializedName("website")
	val website: String? = null,

	@field:SerializedName("address")
	val address: String? = null,

	@field:SerializedName("tla")
	val tla: String? = null,

	@field:SerializedName("founded")
	val founded: Int? = null,

	@field:SerializedName("staff")
	val staff: List<Any?>? = null,

	@field:SerializedName("lastUpdated")
	val lastUpdated: String? = null,

	@field:SerializedName("runningCompetitions")
	val runningCompetitions: List<RunningCompetitionsItem?>? = null,

	@field:SerializedName("clubColors")
	val clubColors: String? = null,

	@field:SerializedName("squad")
	val squad: List<SquadItem?>? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("shortName")
	val shortName: String? = null,

	@field:SerializedName("coach")
	val coach: Coach? = null,

	@field:SerializedName("crest")
	val crest: String? = null
)