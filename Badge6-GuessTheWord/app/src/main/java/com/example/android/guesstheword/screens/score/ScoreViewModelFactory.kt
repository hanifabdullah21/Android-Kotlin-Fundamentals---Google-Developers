package com.example.android.guesstheword.screens.score

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

//TODO 8.2 Add new kotlin class named ScoreViewModelFactory
// The factory method pattern is a creational design pattern that uses factory methods to create objects.
// A factory method is a method that returns an instance of the same class.
// @link https://en.wikipedia.org/wiki/Factory_method_pattern
// @link https://en.wikipedia.org/wiki/Creational_pattern
class ScoreViewModelFactory (private val finalScore: Int): ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ScoreViewModel::class.java)){
            return ScoreViewModel(finalScore) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}