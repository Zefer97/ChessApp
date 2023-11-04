package com.example.foodapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.animation.AnimationUtils
import android.graphics.Color
import android.graphics.Color.rgb
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.VISIBLE
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.R.drawable
import com.example.foodapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.buttonSetting1
import kotlinx.android.synthetic.main.activity_main.editTextSetting1
import kotlinx.android.synthetic.main.activity_main.textButton1
import kotlinx.android.synthetic.main.activity_main.timeEdit

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val alertDialog by lazy {
        AlertDialog.Builder(this).setMessage("Reset the Clock").setPositiveButton("Yes") { _, _ ->
            startActivity(
                Intent(
                    this, MainActivity::class.java
                )
            )
        }.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }.setCancelable(false).create()
    }
    private var clickCountButton1 = 0
    private var clickCountButton2 = 0
    private val click by lazy {
        MediaPlayer.create(this, R.raw.click)
    }
    private val animation by lazy {
        AnimationUtils.loadAnimation(this, R.anim.button_animation)
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
            val view = binding.root
        setContentView(view)
        supportActionBar?.hide()
        window.decorView.apply {
            systemUiVisibility =
                View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        initViewsClickListener()
    }

    @SuppressLint("SetTextI18n")
    private fun initViewsClickListener() {
        fun buttonPlayerOnePauseColor(){
            binding.ButtonPlayerOne.setBackgroundColor(rgb(149, 149, 149))
        }
        fun buttonPlayerTwoPauseColor(){
            binding.ButtonPlayerTwo.setBackgroundColor(rgb(149, 149, 149))
        }
        fun buttonPlayerOnePlayColor(){
            binding.ButtonPlayerOne.setBackgroundColor(rgb(127, 167, 81))
        }
        fun buttonPlayerTwoPlayColor(){
            binding.ButtonPlayerTwo.setBackgroundColor(rgb(127, 167, 81))
        }
        fun textPlayerOnePlayColor(){
            binding.textButton1.setTextColor(Color.WHITE)
        }
        fun textPlayerTwoPlayColor(){
            binding.textButton2.setTextColor(Color.WHITE)
        }
        fun textPlayerOnePauseColor(){
            binding.textButton1.setTextColor(rgb(41, 41, 39))
        }
        fun textPlayerTwoPauseColor(){
            binding.textButton2.setTextColor(rgb(41, 41, 39))
        }

        binding.ButtonPlayerOne.setOnClickListener {
            val handler = Handler()
            val myRunnable = Runnable {
                if (startGame == START_GAME && player == PLAYER_1) {
                    binding.ButtonPlayerOne.isClickable = false
                    click.start()
                    selectedPlayer = PLAYER_TWO_INDEX
                    val clickCount1 = 1 + clickCountButton1++
                    binding.moveCountTextViewP1.text = "Moves : $clickCount1"
                    buttonPlayerOnePauseColor()
                    buttonPlayerTwoPlayColor()
                    player = PLAYER_2
                    textPlayerTwoPlayColor()
                    textPlayerOnePauseColor()
                    binding.ButtonPlayerOne.startAnimation(animation)
                    binding.ButtonPlayerTwo.isClickable = true
                    initTimeDecreaseLogic()
                } else if (startGame == STOP_GAME) {
                    startGame = START_GAME
                    startTimer = true
                    buttonPlayerTwoPauseColor()
                    buttonPlayerOnePlayColor()
                    textPlayerOnePlayColor()
                    binding.ButtonPlayerOne.isClickable = true
                    binding.imagePause.setImageResource(drawable.pause)
                    initTimeDecreaseLogic()
                }
            }
            handler.postDelayed(myRunnable, 1000)
            handler.removeCallbacks(myRunnable,1000)
        }
        binding.ButtonPlayerTwo.setOnClickListener {
            val handler = Handler()
            val myRunnable = Runnable {
                if (startGame == START_GAME && player == PLAYER_2) {
                    player = PLAYER_1
                    click.start()
                    selectedPlayer = PLAYER_ONE_INDEX
                    val clickCount2 = 1 + clickCountButton2++
                    binding.moveCountTextViewP2.text = "Moves : $clickCount2"
                    binding.ButtonPlayerTwo.startAnimation(animation)
                    binding.ButtonPlayerTwo.isClickable = false
                    binding.ButtonPlayerOne.isClickable = true
                    buttonPlayerTwoPauseColor()
                    buttonPlayerOnePlayColor()
                    textPlayerTwoPauseColor()
                    textPlayerOnePlayColor()
                    initTimeDecreaseLogic()
                } else if (startGame == STOP_GAME) {
                    startGame = START_GAME
                    startTimer = true
                    binding.imagePause.setImageResource(drawable.pause)
                    buttonPlayerTwoPauseColor()
                    buttonPlayerOnePlayColor()
                    textPlayerOnePlayColor()
                    initTimeDecreaseLogic()
                }
            }
            handler.postDelayed(myRunnable, 1000)
            handler.removeCallbacks(myRunnable,1000)

        }
        binding.imageSetting.setOnClickListener {
            binding.ButtonPlayerOne.isClickable = false
            binding.ButtonPlayerTwo.isClickable = false
            binding.timeEdit.visibility = VISIBLE
            binding.button.visibility = VISIBLE
            binding.imageSetting.visibility = INVISIBLE
            binding.ImageReplay.visibility = INVISIBLE
            binding.imagePause.visibility = INVISIBLE
            if (startGame == START_GAME) {
                binding.imagePause.setImageResource(drawable.play)
                startGame = STOP_GAME;startTimer = false
                buttonPlayerOnePauseColor()
                buttonPlayerTwoPauseColor()
                textPlayerOnePauseColor()
                textPlayerTwoPauseColor()
            }
            binding.timeEdit.setOnEditorActionListener { _, _, _ ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                binding.timeEdit.visibility = INVISIBLE
                binding.button.visibility = INVISIBLE
                binding.imagePause.visibility = VISIBLE
                binding.imageSetting.visibility = VISIBLE
                if (timeEdit.text.isEmpty()) {
                    binding.textButton1.text = String.format("%02d:%02d", time1min, time1sec)
                    binding.textButton2.text = String.format("%02d:%02d", time2min, time2sec)
                    startGame = START_GAME;startTimer = true;initTimeDecreaseLogic()
                    true
                } else {
                    times1 = timeEdit.text.toString();time1min = times1.toInt();time2min =
                        times1.toInt()
                    binding.ImageReplay.visibility = VISIBLE
                    binding.textButton1.text = String.format("%02d:%02d", time1min, time1sec)
                    binding.textButton2.text = String.format("%02d:%02d", time2min, time2sec)
                    false
                }
            }
            binding.button.setOnClickListener {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                binding.timeEdit.visibility = INVISIBLE
                binding.button.visibility = INVISIBLE
                binding.imagePause.visibility = VISIBLE
                binding.imageSetting.visibility = VISIBLE
                binding.ImageReplay.visibility = VISIBLE
                if (timeEdit.text.isEmpty()) {
                    binding.textButton1.text = String.format("%02d:%02d", time1min, time1sec)
                    binding.textButton2.text = String.format("%02d:%02d", time2min, time2sec)
                } else {
                    times1 = timeEdit.text.toString();time1min = times1.toInt();time2min =
                        times1.toInt()
                    time1sec = 0
                    time2sec = 0
                    binding.textButton1.text = String.format("%02d:%02d", time1min, time1sec)
                    binding.textButton2.text = String.format("%02d:%02d", time2min, time2sec)
                }
            }
        }
        binding.imagePause.setOnClickListener {
            if (startGame == STOP_GAME) {
                binding.ButtonPlayerOne.isClickable = true
                binding.ButtonPlayerTwo.isClickable = true
                binding.imagePause.setImageResource(drawable.pause)
                startGame = START_GAME;startTimer = true;initTimeDecreaseLogic()
                if (startGame == START_GAME && player == PLAYER_2) {
                    buttonPlayerOnePauseColor()
                    buttonPlayerTwoPlayColor()
                    textPlayerTwoPlayColor()
                } else if (startGame == START_GAME && player == PLAYER_1) {
                    buttonPlayerTwoPauseColor()
                    buttonPlayerOnePlayColor()
                    textPlayerOnePlayColor()
                }
            } else if (startGame == START_GAME) {
                binding.ButtonPlayerOne.isClickable = false
                binding.ButtonPlayerTwo.isClickable = false
                binding.imagePause.setImageResource(drawable.play);startGame = STOP_GAME;startTimer = false
                textPlayerTwoPauseColor()
                textPlayerOnePauseColor()
                buttonPlayerOnePauseColor()
                buttonPlayerTwoPauseColor()
            }
        }
        binding.ImageReplay.setOnClickListener {
            if (startGame == START_GAME) {
                binding.imagePause.setImageResource(drawable.play)
                startGame = STOP_GAME;startTimer = false
                buttonPlayerOnePauseColor()
                buttonPlayerTwoPauseColor()
                textPlayerOnePauseColor()
                textPlayerTwoPauseColor()
            }
            showResetTimerAlertDialogLogic()
        }
        binding.settingPlayerOne.setOnClickListener {
            if (startGame == START_GAME) {
                binding.imagePause.setImageResource(drawable.play)
                startGame = STOP_GAME;startTimer = false
                buttonPlayerOnePauseColor()
                buttonPlayerTwoPauseColor()
                textPlayerOnePauseColor()
                textPlayerTwoPauseColor()
            }
            binding.editTextSetting1.visibility = VISIBLE
            binding.buttonSetting1.visibility = VISIBLE
            binding.ButtonPlayerOne.isClickable = false
            binding.ButtonPlayerTwo.isClickable = false
            binding.editTextSetting1.setOnEditorActionListener { _, _, _ ->
                binding.editTextSetting1.visibility = INVISIBLE
                binding.buttonSetting1.visibility = INVISIBLE
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                if (binding.editTextSetting1.text.isEmpty()) {
                    binding.textButton1.text = String.format("%02d:%02d", time1min, time1sec)
                    true
                } else {
                    times1 = binding.editTextSetting1.text.toString();time1min = times1.toInt()
                    binding.textButton1.text = String.format("%02d:%02d", time1min, time1sec)
                    false
                }
            }
            binding.buttonSetting1.setOnClickListener {
                editTextSetting1.visibility = INVISIBLE
                buttonSetting1.visibility = INVISIBLE
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                if (editTextSetting1.text.isEmpty()) {
                    textButton1.text = String.format("%02d:%02d", time1min, time1sec)
                } else {
                    times1 = editTextSetting1.text.toString();time1min = times1.toInt()
                    textButton1.text = String.format("%02d:%02d", time1min, time1sec)
                }
            }
        }
        binding.settingPlayerTwo.setOnClickListener {
            if (startGame == START_GAME) {
                binding.imagePause.setImageResource(drawable.play)
                startGame = STOP_GAME;startTimer = false
                buttonPlayerOnePauseColor()
                buttonPlayerTwoPauseColor()
                textPlayerOnePauseColor()
                textPlayerTwoPauseColor()
            }
            binding.editTextSetting2.visibility = VISIBLE
            binding.buttonSetting2.visibility = VISIBLE
            binding.ButtonPlayerOne.isClickable = false
            binding.ButtonPlayerTwo.isClickable = false
            binding.editTextSetting2.setOnEditorActionListener { _, _, _ ->
                binding.editTextSetting2.visibility = INVISIBLE
                binding.buttonSetting2.visibility = INVISIBLE
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                if (binding.editTextSetting2.text.isEmpty()) {
                    binding.textButton2.text = String.format("%02d:%02d", time2min, time2sec)
                    true
                } else {
                    times1 = binding.editTextSetting2.text.toString();time2min = times1.toInt()
                    binding.textButton2.text = String.format("%2d:%02d", time2min, time2sec)
                    false
                }
            }
            binding.buttonSetting2.setOnClickListener {
                binding.editTextSetting2.visibility = INVISIBLE
                binding.buttonSetting2.visibility = INVISIBLE
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                if (binding.editTextSetting2.text.isEmpty()) {
                    binding.textButton2.text = String.format("%02d:%02d", time2min, time2sec)
                } else {
                    times1 = binding.editTextSetting2.text.toString();time2min = times1.toInt()
                    binding.textButton2.text = String.format("%02d:%02d", time2min, time2sec)
                }
            }
        }
    }

    private var times1 = ""
    private var startGame = STOP_GAME
    private var selectedPlayer = PLAYER_ONE_INDEX
    private var startTimer = false
    private var time1min = 5
    private var time2min = 5
    private var time1sec = 0
    private var time2sec = 0
    private var player = PLAYER_1


    private fun initTimeDecreaseLogic() {
        if (startTimer) {
            if (selectedPlayer == PLAYER_ONE_INDEX) {
                fun p1() {
                    if (time1sec == TIME_ZERO && time1min != TIME_ZERO) {
                        time1min -= 1;time1sec = SECOND
                    }
                    fun times() {
                        time1sec -= 1
                    }
                    val formattedTime = String.format("%02d:%02d", time1min, time1sec)
                    Handler().postDelayed({ binding.textButton1.text = formattedTime }, 0)
                    Handler().postDelayed({
                        if (time1min <= TIME_ZERO && time1sec <= TIME_ZERO) {
                            selectedPlayer = PLAYER_DEFAULT_INDEX
                            binding.ButtonPlayerOne.setBackgroundColor(Color.RED)
                            val lose = MediaPlayer.create(this, R.raw.loseeffect)
                            lose.start()
                            binding.imagePause.visibility = INVISIBLE
                        }
                        if (time1sec >= TIME_ZERO && selectedPlayer == PLAYER_ONE_INDEX && startGame == START_GAME) {
                            times()
                            p1()
                        }
                    }, 1000)
                }
                p1()
            }
            if (selectedPlayer == PLAYER_TWO_INDEX) {
                fun p2() {
                    if (time2sec == TIME_ZERO && time2min != TIME_ZERO) {
                        time2min -= 1;time2sec = SECOND
                    }
                    fun times() {
                        time2sec -= 1
                    }
                    val formattedTime = String.format("%02d:%02d", time2min, time2sec)
                    Handler().postDelayed({ binding.textButton2.text = formattedTime }, 0)
                    Handler().postDelayed({
                        if (time2min <= TIME_ZERO && time2sec <= TIME_ZERO) {
                            selectedPlayer = PLAYER_DEFAULT_INDEX
                            binding.ButtonPlayerTwo.setBackgroundColor(Color.RED)
                            val lose = MediaPlayer.create(this, R.raw.loseeffect)
                            lose.start()
                            binding.imagePause.visibility = INVISIBLE
                        }
                        if (time2sec >= TIME_ZERO && selectedPlayer == PLAYER_TWO_INDEX && startGame == START_GAME) {
                            times()
                            p2()
                        }
                    }, 1000)
                }
                p2()
            }
        }
    }

    private fun showResetTimerAlertDialogLogic() {
        alertDialog.show()
        Handler().postDelayed({
            if (alertDialog.isShowing) {
                alertDialog.dismiss()
            }
        }, 5000)
    }
    companion object {
        const val PLAYER_DEFAULT_INDEX = 0
        const val PLAYER_ONE_INDEX = 1
        const val PLAYER_TWO_INDEX = 2
        const val SECOND = 60
        const val TIME_ZERO = 0
        const val STOP_GAME = 2
        const val START_GAME = 1
        const val PLAYER_1 = 1
        const val PLAYER_2 = 2
    }
}

