package com.example.football.data.host.matches

data class GeneralMatches(
	val competition: Competition? = null,
	val filters: Filters? = null,
	val matches: List<MatchesItem?>? = null,
	val resultSet: ResultSet? = null
)
