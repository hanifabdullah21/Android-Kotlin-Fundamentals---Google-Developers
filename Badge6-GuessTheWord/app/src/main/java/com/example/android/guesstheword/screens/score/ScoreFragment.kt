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

package com.example.android.guesstheword.screens.score

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.ScoreFragmentBinding

/**
 * Fragment where the final score is shown, after the game is over
 */
class ScoreFragment : Fragment() {

    //TODO 8.3 Add new vaariable
    lateinit var scoreViewModel: ScoreViewModel
    lateinit var scoreViewModelFactory: ScoreViewModelFactory

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        // Inflate view and obtain an instance of the binding class.
        val binding: ScoreFragmentBinding = DataBindingUtil.inflate(
                inflater,
                R.layout.score_fragment,
                container,
                false
        )

        //TODO 8.4 Add initialize variable scoreViewModel and scoreViewModelFactory
        scoreViewModelFactory = ScoreViewModelFactory(ScoreFragmentArgs.fromBundle(requireArguments()).score)
        scoreViewModel = ViewModelProvider(this, scoreViewModelFactory).get(ScoreViewModel::class.java)

        //binding.scoreText.text = scoreViewModel.score.toString()

        //TODO 18.3 Add initialize databinding viewmodel
        binding.scoreViewModel = scoreViewModel

        //TODO 19.5 Setup binding lifecycle owner fragment
        binding.lifecycleOwner = viewLifecycleOwner

        //TODO 14.3 Add observer the score
        //TODO 19.6 Remove observer score
        //scoreViewModel.score.observe(viewLifecycleOwner, Observer {
        //    binding.scoreText.text = it.toString()
        //})

        //TODO 15.3 Add observer the eventPlayAgain
        scoreViewModel.eventPlayAgain.observe(viewLifecycleOwner, Observer {
            if (it) {
                findNavController().navigate(ScoreFragmentDirections.actionRestart())
                scoreViewModel.onPlayAgainFinish()
            }
        })

        //TODO 15.4 Add event when button play again clicked
        //TODO 18.4 Remove event listener
        //binding.playAgainButton.setOnClickListener { scoreViewModel.onPlayAgain() }

        return binding.root
    }
}
