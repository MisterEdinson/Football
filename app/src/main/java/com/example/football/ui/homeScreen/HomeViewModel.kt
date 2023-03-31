package com.example.football.ui.homeScreen

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.data.host.matches.General_matches
import com.example.football.data.host.model.General
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
class HomeViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    val ligsLiveData: MutableLiveData<Resource<General>> = MutableLiveData()
    val matchLiveData: MutableLiveData<Resource<General_matches>> = MutableLiveData()
    val match10LiveData: MutableLiveData<Resource<General_matches>> = MutableLiveData()

    var today = Date()

    @SuppressLint("SimpleDateFormat")
    var formatToday = SimpleDateFormat("yyyy-MM-dd").format(today).toString()
    var dateParse = LocalDate.parse(formatToday)
    var period = Period.of(0, 0, 5)
    var modifiedDate = dateParse.plus(period).toString()

    init {
        getCompetitions()
        getMatches()
        getMatch10Day(modifiedDate, formatToday)
    }

    private fun getCompetitions() =
        viewModelScope.launch {
            ligsLiveData.postValue(Resource.Loading())
            val responce = repository.getCompetition()
            if (responce.isSuccessful) {
                responce.body().let {
                    ligsLiveData.postValue(Resource.Success(it))
                }
            } else {
                ligsLiveData.postValue(Resource.Error(responce.message()))
            }
        }

    private fun getMatches() =
        viewModelScope.launch {
            matchLiveData.postValue(Resource.Loading())
            val responce = repository.getMatchDay()
            if (responce.isSuccessful) {
                responce.body().let {
                    matchLiveData.postValue(Resource.Success(it))
                }
            } else {
                matchLiveData.postValue(Resource.Error(responce.message()))
            }
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

