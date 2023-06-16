package com.sample.app

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.data.Repository
import com.sample.data.entities.RelatedTopic


class SharedViewModel : ViewModel() {
    private val _currentCast = MutableLiveData<ArrayList<RelatedTopic>>(arrayListOf())
    val currentCast: LiveData<ArrayList<RelatedTopic>>
        get() = _currentCast

    val characterSelected: MutableLiveData<RelatedTopic> by lazy {
        MutableLiveData<RelatedTopic>()
    }

    fun getCast(): ArrayList<RelatedTopic>? {
        return _currentCast.value
    }

    fun onGetCharacters(shows: String) {
        Repository.getCharacters(shows, _currentCast)
    }

    fun setSelectedCharacter(character: RelatedTopic) {
        characterSelected.postValue(character)
    }

    fun setCurrentCast(character: ArrayList<RelatedTopic>) {
        _currentCast.postValue(character)
    }
}