package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

//TODO 2 Create new Kotlin class called GameViewModel and extend the abstract class ViewModel
class GameViewModel : ViewModel() {

    //TODO 21.1 Add companion object hold timer
    companion object {
        // Time when the game is over
        private const val DONE = 0L

        // Countdown time interval
        private const val ONE_SECOND = 1000L

        // Total time for the game
        private const val COUNTDOWN_TIME = 60000L
    }

    //TODO 9.1 change the type variable of word and score
    //TODO 11.1 Add backing property to word and score for implement encapsulation

    // The current word
    // var word = ""
    private val _word = MutableLiveData<String>()
    val word: LiveData<String>
        get() = _word

    // The current score
    // var score = 0
    private val _score = MutableLiveData<Int>()
    val score: LiveData<Int>
        get() = _score

    //TODO 12.1 Add variable will hold the game-finished event
    private val _eventGameFinish = MutableLiveData<Boolean>()
    val eventGameFinish: LiveData<Boolean>
        get() = _eventGameFinish

    //TODO 21.2 Add variable store timer
    private val _currentTime = MutableLiveData<Long>()
    val currentTime: LiveData<Long>
        get() = _currentTime

    //TODO 22.1 Add Transformation current time
    val currentTimeString = Transformations.map(currentTime) { time ->
        DateUtils.formatElapsedTime(time)
    }

    //TODO 21.3 Add private variable
    private val timer: CountDownTimer

    // The list of words - the front of the list is the next word to guess
    lateinit var wordList: MutableList<String>

    //TODO 11.2 Change score and word to _score and _word

    init {
        Log.i("GameViewModel", "GameViewModel created!")

        //TODO 9.2 Initialize variable word and score first
        //word.value = ""
        //score.value = 0

        _word.value = ""
        _score.value = 0

        //TODO 21.4 Initialization timer
        timer = object : CountDownTimer(COUNTDOWN_TIME, ONE_SECOND) {
            override fun onFinish() {
                _currentTime.value = DONE
                onGameFinish()
            }

            override fun onTick(p0: Long) {
                _currentTime.value = p0 / ONE_SECOND
            }
        }
        timer.start()

        //TODO 5.2 move call method resetList() and nextWord() from onCreateView of GameFragment to here
        resetList()
        nextWord()
    }


    //TODO 9.3 Update how to score increment and decrement on onSkip() and onCorrect() and how to word remove on nextWord()

    /**
     * Resets the list of words and randomizes the order
     */
    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        if (!wordList.isEmpty()) {
            //Select and remove a word from the list
            //word = wordList.removeAt(0)
            //word.value = wordList.removeAt(0)
            _word.value = wordList.removeAt(0)
        } else {
            //TODO 12.3 Call method onGameFinish() when wordList is empty
            //onGameFinish()
            //TODO 21.5 Reset List
            resetList()
        }

        //TODO 6.3 Remove the methods below
        //updateWordText()
        //updateScoreText()
    }

    /** Methods for buttons presses **/

    //TODO 5.3 Copy method onSkip() and onCorrect() from GameFragment class

    fun onSkip() {
        // score--
        //score.value = (score.value)?.minus(1)
        _score.value = (score.value)?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        //score++
        //score.value = (score.value)?.plus(1)
        _score.value = (score.value)?.plus(1)
        nextWord()
    }

    //TODO 12.2 Add method onGameFinish()
    fun onGameFinish() {
        _eventGameFinish.value = true
    }

    //TODO 13.1 Add method ongameFinishComplete to fix issue re-call method
    fun onGameFinishComplete() {
        _eventGameFinish.value = false
    }

    //TODO 3 Add override method onCleared. This method called when fragment is detached or activity is finished
    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel", "GameViewModel destroyed!")
        //TODO 21.6 Clear timer
        timer.cancel()
    }
}