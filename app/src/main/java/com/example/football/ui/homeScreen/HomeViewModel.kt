package com.example.football.ui.homeScreen

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.data.host.matches.GeneralMatches
import com.example.football.data.room.models.FootballLigsEntity
import com.example.football.data.room.models.FootballMatchesDayEntity
import com.example.football.repository.Repository
import com.example.football.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.Period
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: Repository)
    : ViewModel() {

    val ligsLiveData: MutableLiveData<List<FootballLigsEntity>> = MutableLiveData()
    val matchLiveData: MutableLiveData<List<FootballMatchesDayEntity>> = MutableLiveData()
    val match10LiveData: MutableLiveData<Resource<GeneralMatches>> = MutableLiveData()

    var today = Date()
    @SuppressLint("SimpleDateFormat")
    var formatToday = SimpleDateFormat("yyyy-MM-dd").format(today).toString()
    private var dateParse = LocalDate.parse(formatToday)
    private var period = Period.of(0, 0, 5)
    var modifiedDate = dateParse.plus(period).toString()

    init {
        getCompetitions()
        getMatches()
        getMatch10Day(modifiedDate, formatToday)
    }

    private fun getCompetitions() =
        viewModelScope.launch {
            val responce = repository.getCompetition()
            ligsLiveData.value = responce
        }

    private fun getMatches() =
        viewModelScope.launch {
            val responce = repository.getMatchDay()
            matchLiveData.value = responce
        }

    private fun getMatch10Day(dataTo: String, dataFrom: String) =
        viewModelScope.launch {
            match10LiveData.postValue(Resource.Loading())
            val responce = repository.getMatch10Day(dataTo, dataFrom)
            if (responce.isSuccessful) {
                responce.body().let {
                    match10LiveData.postValue(Resource.Success(it))
                }
            } else {
                match10LiveData.postValue(Resource.Error(responce.message()))
            }
        }
}


