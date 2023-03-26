package com.example.football.data.host.matches

data class General_matches(
	val competition: Competition? = null,
	val filters: Filters? = null,
	val matches: List<MatchesItem?>? = null,
	val resultSet: ResultSet? = null
)
