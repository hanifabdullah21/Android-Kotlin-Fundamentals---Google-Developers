package com.hanifabdullah21.aboutme

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil
import com.hanifabdullah21.aboutme.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var activityMainBinding: ActivityMainBinding
    lateinit var myName: MyName

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        myName = MyName(name = "Hanif Abdullah")
        activityMainBinding.myName = myName

    }

    fun addNickname(view: View) {
        activityMainBinding.apply {
            myName?.nickname = nicknameEdit.text.toString()
            invalidateAll()
        }
        activityMainBinding.nicknameText.visibility = View.VISIBLE
        activityMainBinding.nicknameEdit.visibility = View.GONE
        activityMainBinding.doneButton.visibility = View.GONE

        val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
    }

    fun updateNickname(view: View) {
        activityMainBinding.nicknameText.visibility = View.GONE
        activityMainBinding.nicknameEdit.visibility = View.VISIBLE
        activityMainBinding.doneButton.visibility = View.VISIBLE

        activityMainBinding.nicknameEdit.requestFocus()

        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(nickname_edit, 0)
    }
}