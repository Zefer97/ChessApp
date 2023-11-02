package com.example.foodapp

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.animation.AnimationUtils
import android.graphics.Color
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
import kotlinx.android.synthetic.main.activity_main.textButton2
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
//        binding.ButtonPlayerOne.setOnClickListener {
//            if (a == 1 && b == 1) {
//                selectedPlayer = PLAYER_TWO_INDEX
//                val clickCount1 = 1 + clickCountButton1++
//                binding.textView6.text = "Moves : $clickCount1"
//                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
//                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(127, 167, 81));b =
//                    2;click.start()
//                binding.ButtonPlayerOne.isClickable = false
//                binding.textButton2.setTextColor(Color.WHITE)
//                binding.textButton1.setTextColor(Color.rgb(41, 41, 39))
//            }
//            if (a == 2) {
//                a = 1
//                timerOn = true
//                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
//                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(127, 167, 81))
//                binding.ButtonPlayerOne.isClickable = true
//                binding.textButton1.setTextColor(Color.WHITE)
//                binding.imagePause.setImageResource(drawable.pause)
//            }
//            binding.ButtonPlayerOne.startAnimation(animation)
//            binding.ButtonPlayerTwo.isClickable = true
//            initTimeDecreaseLogic()
//        }
//        binding.ButtonPlayerTwo.setOnClickListener {
//            if (a == 1 && b == 2) {
//                selectedPlayer = PLAYER_ONE_INDEX
//                val clickCount2 = 1 + clickCountButton2++
//                binding.textView7.text = "Moves : $clickCount2"
//                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
//                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(127, 167, 81))
//                b = 1;click.start()
//                binding.textButton2.setTextColor(Color.rgb(41, 41, 39))
//                binding.textButton1.setTextColor(Color.WHITE)
//            }
//            if (a == 2) {
//                a = 1;timerOn = true
//                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
//                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(127, 167, 81))
//                binding.textButton1.setTextColor(Color.WHITE)
//                binding.imagePause.setImageResource(drawable.pause)
//            }
//            binding.ButtonPlayerTwo.startAnimation(animation)
//            binding.ButtonPlayerTwo.isClickable = false
//            binding.ButtonPlayerOne.isClickable = true
//            initTimeDecreaseLogic()
//        }
        binding.ButtonPlayerOne.setOnClickListener {
            if (a == 1 && b == 1) {
                binding.ButtonPlayerOne.isClickable = false
                click.start()
                Handler().postDelayed({
                    selectedPlayer = PLAYER_TWO_INDEX
                    val clickCount1 = 1 + clickCountButton1++
                    binding.textView6.text = "Moves : $clickCount1"
                    binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
                    binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(127, 167, 81))
                    b = 2
                    binding.textButton2.setTextColor(Color.WHITE)
                    binding.textButton1.setTextColor(Color.rgb(41, 41, 39))
                    binding.ButtonPlayerOne.startAnimation(animation)
                    binding.ButtonPlayerTwo.isClickable = true
                    initTimeDecreaseLogic()
                }, 500)
            }
            if (a == 2) {
                a = 1
                startTimer = true
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(127, 167, 81))
                binding.ButtonPlayerOne.isClickable = true
                binding.textButton1.setTextColor(Color.WHITE)
                binding.imagePause.setImageResource(drawable.pause)
            }
        }
        binding.ButtonPlayerTwo.setOnClickListener {
            if (a == 1 && b == 2) {
                click.start()
                Handler().postDelayed({
                    selectedPlayer = PLAYER_ONE_INDEX
                    val clickCount2 = 1 + clickCountButton2++
                    binding.textView7.text = "Moves : $clickCount2"
                    binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                    binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(127, 167, 81))
                    b = 1
                    binding.textButton2.setTextColor(Color.rgb(41, 41, 39))
                    binding.textButton1.setTextColor(Color.WHITE)
                    binding.ButtonPlayerTwo.startAnimation(animation)
                    binding.ButtonPlayerTwo.isClickable = false
                    binding.ButtonPlayerOne.isClickable = true
                    initTimeDecreaseLogic()
                }, 500)
            }
            if (a == 2) {
                a = 1
                startTimer = true
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(127, 167, 81))
                binding.textButton1.setTextColor(Color.WHITE)
                binding.imagePause.setImageResource(drawable.pause)
            }
        }
        binding.ImageReplay.setOnClickListener {
            if (a == 1) {
                binding.imagePause.setImageResource(drawable.play)
                a = 2;startTimer = false
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.textButton1.setTextColor(Color.rgb(41, 41, 39))
                binding.textButton2.setTextColor(Color.rgb(41, 41, 39))
            }
            showResetTimerAlertDialogLogic()
        }
        binding.imagePause.setOnClickListener {
            if (a == 2) {
                binding.ButtonPlayerOne.isClickable = true
                binding.ButtonPlayerTwo.isClickable = true
                binding.imagePause.setImageResource(drawable.pause)
                a = 1;startTimer = true;initTimeDecreaseLogic()
                if (a == 1 && b == 2) {
                    binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
                    binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(127, 167, 81))
                    binding.textButton2.setTextColor(Color.WHITE)
                } else if (a == 1 && b == 1) {
                    binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                    binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(127, 167, 81))
                    binding.textButton1.setTextColor(Color.WHITE)
                }
            } else if (a == 1) {
                binding.ButtonPlayerOne.isClickable = false
                binding.ButtonPlayerTwo.isClickable = false
                binding.imagePause.setImageResource(drawable.play);a = 2;startTimer = false
                binding.textButton2.setTextColor(Color.rgb(41, 41, 39))
                binding.textButton1.setTextColor(Color.rgb(41, 41, 39))
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
            }
        }
        binding.imageSetting.setOnClickListener {
            binding.ButtonPlayerOne.isClickable = false
            binding.ButtonPlayerTwo.isClickable = false
            binding.timeEdit.visibility = VISIBLE
            binding.button.visibility = VISIBLE
            binding.imageSetting.visibility = INVISIBLE
            binding.ImageReplay.visibility = INVISIBLE
            binding.imagePause.visibility = INVISIBLE
            if (a == 1) {
                binding.imagePause.setImageResource(drawable.play)
                a = 2;startTimer = false
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.textButton1.setTextColor(Color.rgb(41, 41, 39))
                binding.textButton2.setTextColor(Color.rgb(41, 41, 39))
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
                    a = 1;startTimer = true;initTimeDecreaseLogic()
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
                if (timeEdit.text.isEmpty()) {
                    binding.textButton1.text = String.format("%02d:%02d", time1min, time1sec)
                    binding.textButton2.text = String.format("%02d:%02d", time2min, time2sec)
                    binding.timeEdit.visibility = INVISIBLE
                    binding.button.visibility = INVISIBLE
                    binding.imagePause.visibility = VISIBLE
                    binding.imageSetting.visibility = VISIBLE
                    binding.ImageReplay.visibility = VISIBLE
                } else {
                    times1 = timeEdit.text.toString();time1min = times1.toInt();time2min =
                        times1.toInt()
                    binding.timeEdit.visibility = INVISIBLE
                    binding.button.visibility = INVISIBLE
                    binding.imagePause.visibility = VISIBLE
                    binding.imageSetting.visibility = VISIBLE
                    binding.ImageReplay.visibility = VISIBLE
                    time1sec = 0
                    time2sec = 0
                    binding.textButton1.text = String.format("%02d:%02d", time1min, time1sec)
                    binding.textButton2.text = String.format("%02d:%02d", time2min, time2sec)
                }
            }
        }
        binding.settingPlayerOne.setOnClickListener {
            if (a == 1) {
                binding.imagePause.setImageResource(drawable.play)
                a = 2;startTimer = false
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.textButton1.setTextColor(Color.rgb(41, 41, 39))
                binding.textButton2.setTextColor(Color.rgb(41, 41, 39))
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
            if (a == 1) {
                binding.imagePause.setImageResource(drawable.play)
                a = 2;startTimer = false
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
                textButton1.setTextColor(Color.rgb(41, 41, 39))
                textButton2.setTextColor(Color.rgb(41, 41, 39))
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
                    binding. textButton2.text = String.format("%2d:%02d", time2min, time2sec)
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
    private var a = 2
    private var selectedPlayer = PLAYER_ONE_INDEX
    private var startTimer = false
    private var time1min = 5
    private var time2min = 5
    private var time1sec = 0
    private var time2sec = 0
    private var b = 1


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
                        if (time1sec >= TIME_ZERO && selectedPlayer == PLAYER_ONE_INDEX && a == 1) {
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
                            binding.ButtonPlayerOne.setBackgroundColor(Color.RED)
                            val lose = MediaPlayer.create(this, R.raw.loseeffect)
                            lose.start()
                            binding.imagePause.visibility = INVISIBLE
                        }
                        if (time2sec >= TIME_ZERO && selectedPlayer == PLAYER_TWO_INDEX && a == 1) {
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
        }, 1000)
    }
    companion object {
        const val PLAYER_DEFAULT_INDEX = 0
        const val PLAYER_ONE_INDEX = 1
        const val PLAYER_TWO_INDEX = 2
        const val SECOND = 60
        const val TIME_ZERO = 0
    }
}

