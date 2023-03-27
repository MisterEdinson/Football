package com.example.football.ui.team

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.data.host.team.TeamGeneral
import com.example.football.repository.Repository
import com.example.football.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TeamViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    private val teamId:String = ""
    val teamLiveData: MutableLiveData<Resource<TeamGeneral>> = MutableLiveData()
    init {
        getTeam(teamId)
    }
    fun getTeam(teamId:String) =
        viewModelScope.launch {
            teamLiveData.postValue(Resource.Loading())
            val responce = repository.getTeam(teamId)
            if (responce.isSuccessful) {
                responce.body().let {
                    teamLiveData.postValue(Resource.Success(it))
                }
            } else {
                teamLiveData.postValue(Resource.Error(responce.message()))
            }
        }
}