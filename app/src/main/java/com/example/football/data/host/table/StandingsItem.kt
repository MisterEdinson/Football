package com.example.football.data.host.table

import com.google.gson.annotations.SerializedName

data class StandingsItem(

	@field:SerializedName("stage")
	val stage: String? = null,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("table")
	val table: List<TableItem?>? = null,

	@field:SerializedName("group")
	val group: Any? = null
)