package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

//TODO 8.1 Create new kotlin class named ScoreViewModel
class ScoreViewModel(finalScore: Int) : ViewModel(){

    //TODO 14.1 Add Backing property and change to LiveData
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    //TODO 15.1 Add LiveData object to hold event
    private val _eventPlayAgain = MutableLiveData<Boolean>()
    val eventPlayAgain : LiveData<Boolean>
        get() = _eventPlayAgain

    init {
        Log.i("ScoreViewModel", "Final score is $finalScore")

        //TODO 14.2 Init the score
        _score.value = finalScore
    }

    //TODO 15.2 Add method event
    fun onPlayAgain(){
        _eventPlayAgain.value = true
    }

    fun onPlayAgainFinish(){
        _eventPlayAgain.value = false
    }



}