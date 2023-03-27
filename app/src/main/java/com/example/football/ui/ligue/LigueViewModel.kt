package com.example.football.ui.ligue

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.football.data.host.table.GeneralTable
import com.example.football.repository.Repository
import com.example.football.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LigueViewModel @Inject constructor(private val repository: Repository) : ViewModel() {

    private val ligue:String = ""

    val ligsTableLiveData: MutableLiveData<Resource<GeneralTable>> = MutableLiveData()

    init {
        getTable(ligue)
    }

    fun getTable(ligue:String) =
        viewModelScope.launch {
            ligsTableLiveData.postValue(Resource.Loading())
            val responce = repository.getLigueTable(ligue)
            if (responce.isSuccessful) {
                responce.body().let {
                    ligsTableLiveData.postValue(Resource.Success(it))
                }
            } else {
                ligsTableLiveData.postValue(Resource.Error(responce.message()))
            }
        }
}