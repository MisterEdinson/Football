package com.example.football.data.host.matches

data class MatchesItem(
	val area: Area? = null,
	val matchday: Int? = null,
	val awayTeam: AwayTeam? = null,
	val competition: Competition? = null,
	val utcDate: String? = null,
	val lastUpdated: String? = null,
	val score: Score? = null,
	val stage: String? = null,
	val odds: Odds? = null,
	val season: Season? = null,
	val homeTeam: HomeTeam? = null,
	val id: Int? = null,
	val referees: List<Any?>? = null,
	val status: String? = null,
	val group: Any? = null
)
