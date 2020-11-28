/*
 * Copyright (C) 2019 Google Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    //TODO 4.1 Add new variable type GameViewModel
    private lateinit var gameViewModel: GameViewModel

    //TODO 5.1 Move variables (word, score, wordList) and methods (resetList() and nextWord()) to GameViewModel class

    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        //TODO 4.2 Initialize variable type GameViewModel. Use ViewModelProvider because :
        // * ViewModelProvider returns an existing ViewModel if one exists, or it creates a new one if it does not already exist.
        // * ViewModelProvider creates a ViewModel instance in association with the given scope (an activity or a fragment).
        // * The created ViewModel is retained as long as the scope is alive. For example, if the scope is a fragment, the ViewModel is retained until the fragment is detached.
        Log.i("GameFragment", "Called ViewModelProvider.get")
        gameViewModel = ViewModelProvider(this).get(GameViewModel::class.java)

        //TODO 16.2 Pass ViewModel to databinding
        binding.gameViewModel = gameViewModel

        //TODO 19.2 set binding lifecycle owner
        binding.lifecycleOwner = viewLifecycleOwner

        //TODO 10.1 Add Observer to the LiveData
        //TODO 20.5 Remove score observer
        //gameViewModel.score.observe(viewLifecycleOwner, Observer { newScore ->
        //    binding.scoreText.text = newScore.toString()
        //})

        //TODO 19.3 Remove observer word
        //gameViewModel.word.observe(viewLifecycleOwner, Observer { newWord ->
        //    binding.wordText.text = newWord
        //})

        //TODO 12.4 Add observer LiveData eventGameFinish
        gameViewModel.eventGameFinish.observe(viewLifecycleOwner, Observer {
            if (it) gameFinished()
        })

        //TODO 17.2 Remove event handling button
        //binding.correctButton.setOnClickListener { onCorrect() }
        //binding.skipButton.setOnClickListener { onSkip() }

        //TODO 7.2 Call method onEndGame when user tap endGameButton
        //binding.endGameButton.setOnClickListener { onEndGame() }

        //updateScoreText()
        //updateWordText()
        return binding.root

    }

    /** Methods for buttons presses **/

    //TODO 6.1 Update code inside method onSkip() and onCorrect()

    //TODO 10.2 Delete all updateWordText() and updateScoreText()

    //TODO 17.3 Remove event method handling button

    //private fun onSkip() {
        //score++
        //nextWord()
    //    gameViewModel.onSkip()
        //updateWordText()
        //updateScoreText()
    //}

    //private fun onCorrect() {
        //score++
        //nextWord()
    //    gameViewModel.onCorrect()
        //updateWordText()
        //updateScoreText()
    //}

    /** Methods for updating the UI **/

    //TODO 6.2 Update code inside method updateWordText() and updateScoreText().
    // Call word and score variable from GameViewModel object

    //TODO 9.4 Update how to call word and score on updateWordText(), updateScoreText() and gameFinished()

    //    private fun updateWordText() {
    //        // binding.wordText.text = word
    //        // binding.wordText.text = gameViewModel.word
    //        binding.wordText.text = gameViewModel.word.value
    //    }
    //
    //    private fun updateScoreText() {
    //        // binding.scoreText.text = score.toString()
    //        // binding.scoreText.text = gameViewModel.score.toString()
    //        binding.scoreText.text = gameViewModel.score.value.toString()
    //    }

    //TODO 7.1 Add method onEndGame
    //private fun onEndGame() {
    //    gameFinished()
    //}

    //TODO 7.3 Add method gameFinished() and call inside onEndGame() method
    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        Toast.makeText(activity, "Game has just finished", Toast.LENGTH_SHORT).show()
        val action = GameFragmentDirections.actionGameToScore()
//        action.score = gameViewModel.score
        action.score = gameViewModel.score.value ?: 0
        NavHostFragment.findNavController(this).navigate(action)

        //TODO 13.2 call onGameFinishComplete
        gameViewModel.onGameFinishComplete()
    }
}
