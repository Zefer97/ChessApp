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
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.ButtonPlayerOne
import kotlinx.android.synthetic.main.activity_main.ButtonPlayerTwo
import kotlinx.android.synthetic.main.activity_main.ImageReplay
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.buttonSetting1
import kotlinx.android.synthetic.main.activity_main.buttonSetting2
import kotlinx.android.synthetic.main.activity_main.editTextSetting1
import kotlinx.android.synthetic.main.activity_main.editTextSetting2
import kotlinx.android.synthetic.main.activity_main.imagePause
import kotlinx.android.synthetic.main.activity_main.imageSetting
import kotlinx.android.synthetic.main.activity_main.textButton1
import kotlinx.android.synthetic.main.activity_main.textButton2
import kotlinx.android.synthetic.main.activity_main.textView6
import kotlinx.android.synthetic.main.activity_main.textView7
import kotlinx.android.synthetic.main.activity_main.timeEdit

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

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
        binding.ImageReplay.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    MainActivity::class.java
                )
            )
        }

        val click = MediaPlayer.create(this, R.raw.click)
        val animation = AnimationUtils.loadAnimation(this, R.anim.button_animation)

        binding.imagePause.setOnClickListener {
            if (a == 2) {
                binding.imagePause.setImageResource(R.drawable.pause)
                a = 1;timerOn = true;time()
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(244, 182, 59))
                binding.settingPlayerOne.visibility = INVISIBLE
                binding.settingPlayerTwo.visibility = INVISIBLE }
            else if (a == 1) {
                binding.imagePause.setImageResource(R.drawable.play);a = 2;timerOn = false
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.settingPlayerOne.visibility = VISIBLE
                binding.settingPlayerTwo.visibility = VISIBLE
            }
        }
        binding.imageSetting.setOnClickListener {
            timeEdit.visibility = VISIBLE
            button.visibility = VISIBLE
            imageSetting.visibility = INVISIBLE
            ImageReplay.visibility = INVISIBLE
            imagePause.visibility = INVISIBLE
            ButtonPlayerOne.isClickable = false
            ButtonPlayerTwo.isClickable = false
            timeEdit.setOnEditorActionListener { _, _, _ ->
                if (timeEdit.text.isEmpty()) {
                    textButton1.text = "$time1:00";textButton2.text =
                        "$time2:00"
                    timeEdit.visibility = INVISIBLE
                    button.visibility = INVISIBLE
                    imagePause.visibility = VISIBLE
                    imageSetting.visibility = VISIBLE
                    ImageReplay.visibility = VISIBLE
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                    true
                }else{
                    times1 = timeEdit.text.toString();time1 = times1.toInt();time2 = times1.toInt()
                    timeEdit.visibility = INVISIBLE
                    button.visibility = INVISIBLE
                    imagePause.visibility = VISIBLE
                    imageSetting.visibility = VISIBLE
                    ImageReplay.visibility = VISIBLE
                    textButton1.text = "$times1:00";textButton2.text =
                        "$times1:00"
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                    false
                }
            }
            button.setOnClickListener {
                if (timeEdit.text.isEmpty()) {
                    textButton1.text = "$time1:00";textButton2.text =
                        "$time2:00"
                    timeEdit.visibility = INVISIBLE
                    button.visibility = INVISIBLE
                    imagePause.visibility = VISIBLE
                    imageSetting.visibility = VISIBLE
                    ImageReplay.visibility = VISIBLE
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                } else {
                    times1 = timeEdit.text.toString();time1 = times1.toInt();time2 = times1.toInt()
                    timeEdit.visibility = INVISIBLE
                    button.visibility = INVISIBLE
                    imagePause.visibility = VISIBLE
                    imageSetting.visibility = VISIBLE
                    ImageReplay.visibility = VISIBLE
                    textButton1.text = "$times1:00";textButton2.text =
                        "$times1:00"
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                }
            }
        }
        binding.settingPlayerOne.setOnClickListener {
            editTextSetting1.visibility = VISIBLE
            buttonSetting1.visibility = VISIBLE
            ButtonPlayerOne.isClickable = false
            ButtonPlayerTwo.isClickable = false
            editTextSetting1.setOnEditorActionListener { _, _, _ ->
                if (editTextSetting1.text.isEmpty()) {
                    textButton1.text = "$time1:00"
                    editTextSetting1.visibility = INVISIBLE
                    buttonSetting1.visibility = INVISIBLE
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                    true
                } else {
                    times1 = editTextSetting1.text.toString();time1 = times1.toInt()
                    textButton1.text = "$times1:00"
                    editTextSetting1.visibility = INVISIBLE
                    buttonSetting1.visibility = INVISIBLE
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                    false
                }
            }
            buttonSetting1.setOnClickListener {
                if (editTextSetting1.text.isEmpty()) {
                    textButton1.text = "$time1:00"
                    editTextSetting1.visibility = INVISIBLE
                    buttonSetting1.visibility = INVISIBLE
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                } else {
                    times1 = editTextSetting1.text.toString();time1 = times1.toInt()
                    textButton1.text = "$times1:00"
                    editTextSetting1.visibility = INVISIBLE
                    buttonSetting1.visibility = INVISIBLE
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                }
            }
        }
        binding.settingPlayerTwo.setOnClickListener {
            editTextSetting2.visibility = VISIBLE
            buttonSetting2.visibility = VISIBLE
            ButtonPlayerOne.isClickable = false
            ButtonPlayerTwo.isClickable = false
            editTextSetting2.setOnEditorActionListener { _, _, _ ->
                if (editTextSetting2.text.isEmpty()) {
                    editTextSetting2.visibility = INVISIBLE
                    buttonSetting2.visibility = INVISIBLE
                    textButton2.text = "$time2:00"
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                    true
                } else {
                    times1 = editTextSetting2.text.toString();time2 = times1.toInt()
                    editTextSetting2.visibility = INVISIBLE
                    buttonSetting2.visibility = INVISIBLE
                    textButton2.text = "$times1:00"
                    val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                    false
                }
            }
            buttonSetting2.setOnClickListener {
                if (editTextSetting2.text.isEmpty()) {
                    editTextSetting2.visibility = INVISIBLE
                    buttonSetting2.visibility = INVISIBLE
                    textButton2.text = "$time2:00"
                } else {
                    times1 = editTextSetting2.text.toString();time2 = times1.toInt()
                    editTextSetting2.visibility = INVISIBLE
                    buttonSetting2.visibility = INVISIBLE
                    textButton2.text = "$times1:00"
                }
            }
        }
        var clickCountButton1 = 0
        var clickCountButton2 = 0
        binding.ButtonPlayerOne.setOnClickListener {
            if (a == 1 && b == 1) {
                playerOn = 2
                val clickCount1 = 1 + clickCountButton1++
                textView6.text = "Moves : $clickCount1"
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(244, 182, 59));b =
                    2;click.start()
                ButtonPlayerOne.isClickable = false
            }

            if (a == 2) {
                a = 1 ;timerOn = true
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(244, 182, 59))
                binding.settingPlayerOne.visibility = INVISIBLE
                binding.settingPlayerTwo.visibility = INVISIBLE
                ButtonPlayerOne.isClickable = true
            }
            ButtonPlayerOne.startAnimation(animation)
            time()
            ButtonPlayerTwo.isClickable = true
        }

        binding.ButtonPlayerTwo.setOnClickListener {
            if (a == 1 && b == 2) {
                playerOn = 1
                val clickCount2 = 1 + clickCountButton2++
                textView7.text = "Moves : $clickCount2"
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(244, 182, 59));b =
                    1;click.start()
            }

            if (a == 2) {
                a = 1;timerOn = true
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(244, 182, 59))
                binding.settingPlayerOne.visibility = INVISIBLE
                binding.settingPlayerTwo.visibility = INVISIBLE
            }
            ButtonPlayerTwo.startAnimation(animation)
            time()
            ButtonPlayerTwo.isClickable = false
            ButtonPlayerOne.isClickable = true
        }
    }

    private var times1 = ""
    private var a = 2
    private var playerOn = 1
    private var timerOn = false
    private var time1 = 5
    private var time2 = 5
    private var time12 = 0
    private var time21 = 0
    private var b = 1

    private fun time() {
        if (timerOn) {
            if (playerOn == 1) {
                fun p1() {
                    if (time12 == 0 && time1 != 0) {
                        time1 -= 1;time12 = 60
                    }
                    time12 -= 1
                    val output1 = "$time1:$time12"
                    if (time1 == 0 && time12 == 0) {
                        ButtonPlayerOne.setBackgroundColor(Color.RED)
                        val lose = MediaPlayer.create(this, R.raw.loseeffect)
                        lose.start()
                        imagePause.visibility = INVISIBLE
                    }
                    Handler().postDelayed({ binding.textButton1.text = output1 }, 0)
                    Handler().postDelayed({
                        if (time1 <= 0 && time12 <= 0) {
                            playerOn = 0
                        }
                        if (time12 >= 0 && playerOn == 1 && a == 1) {
                            p1()
                        }
                    }, 1000)

                }
                p1()
            }
            if (playerOn == 2) {
                fun p2() {
                    if (time21 == 0 && time2 != 0) {
                        time2 -= 1;time21 = 60
                    }
                    time21 -= 1
                    val output = "$time2:$time21"
                    if (time2 == 0 && time21 == 0) {
                        imagePause.visibility = INVISIBLE
                        ButtonPlayerTwo.setBackgroundColor(Color.RED)
                        val lose = MediaPlayer.create(this, R.raw.loseeffect)
                        lose.start()
                        imagePause.visibility = INVISIBLE
                    }
                    Handler().postDelayed({ binding.textButton2.text = output }, 0)
                    Handler().postDelayed({
                        if (time2 <= 0 && time21 <= 0) {
                            playerOn = 0
                        }
                        if (time21 >= 0 && playerOn == 2 && a == 1) {
                            p2()
                        }
                    }, 1000)

                }
                p2()
            }
        }
    }
}

