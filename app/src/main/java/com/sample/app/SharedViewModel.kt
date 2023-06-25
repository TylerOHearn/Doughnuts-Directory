package com.sample.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.data.Repository
import com.sample.data.entities.Characters


class SharedViewModel : ViewModel() {
    private val _currentCast = MutableLiveData<ArrayList<Characters>>(arrayListOf())
    val currentCast: LiveData<ArrayList<Characters>>
        get() = _currentCast

    val characterSelected: MutableLiveData<Characters> by lazy {
        MutableLiveData<Characters>()
    }

    fun getCast(): ArrayList<Characters>? {
        return _currentCast.value
    }

    fun onGetCharacters(shows: String) {
        Repository.getCharacters(shows, _currentCast)
    }

    fun setSelectedCharacter(character: Characters) {
        characterSelected.postValue(character)
    }

    fun setCurrentCast(character: ArrayList<Characters>) {
        _currentCast.postValue(character)
    }
}