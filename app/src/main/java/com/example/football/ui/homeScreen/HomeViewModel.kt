package com.example.football.ui.homeScreen

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.data.host.model.General
import com.example.football.repository.Repository
import com.example.football.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {
    val competitionLiveData: MutableLiveData<Resource<General>> = MutableLiveData()

    init {
        getCompetitions()
    }

    private fun getCompetitions() =
        viewModelScope.launch {
            competitionLiveData.postValue(Resource.Loading())
            val responce = repository.getCompetition()
            if (responce.isSuccessful) {
                responce.body().let {
                    competitionLiveData.postValue(Resource.Success(it))
                }
            } else {
                competitionLiveData.postValue(Resource.Error(responce.message()))
            }
        }


}

