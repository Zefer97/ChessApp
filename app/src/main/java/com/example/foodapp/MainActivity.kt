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
import com.example.foodapp.R.drawable
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
                binding.ButtonPlayerOne.isClickable = true
                binding.ButtonPlayerTwo.isClickable = true
                binding.imagePause.setImageResource(drawable.pause)
                a = 1;timerOn = true;time()
                if (a == 1 && b ==2 ){
                    binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
                    binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(244, 182, 59))
                    textButton2.setTextColor(Color.WHITE)
                }else if(a == 1 && b == 1 ){
                    binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                    binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(244, 182, 59))
                    textButton1.setTextColor(Color.WHITE)
                }
            }
            else if (a == 1) {
                binding.ButtonPlayerOne.isClickable = false
                binding.ButtonPlayerTwo.isClickable = false
                binding.imagePause.setImageResource(drawable.play);a = 2;timerOn = false
                textButton2.setTextColor(Color.BLACK)
                textButton1.setTextColor(Color.BLACK)
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
            }
        }

        binding.imageSetting.setOnClickListener {
            ButtonPlayerOne.isClickable =false
            ButtonPlayerTwo.isClickable =false
            timeEdit.visibility = VISIBLE
            button.visibility = VISIBLE
            imageSetting.visibility = INVISIBLE
            ImageReplay.visibility = INVISIBLE
            imagePause.visibility = INVISIBLE
            if(a == 1){
                binding.imagePause.setImageResource(drawable.play)
                a = 2;timerOn = false
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
            }
            timeEdit.setOnEditorActionListener { _, _, _ ->
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                timeEdit.visibility = INVISIBLE
                button.visibility = INVISIBLE
                imagePause.visibility = VISIBLE
                imageSetting.visibility = VISIBLE
                if (timeEdit.text.isEmpty()) {
                    textButton1.text = "$time1min:00";textButton2.text =
                        "$time2min:00"
                    a = 1;timerOn = true;time()
                    true
                }else{
                    times1 = timeEdit.text.toString();time1min = times1.toInt();time2min = times1.toInt()
                    ImageReplay.visibility = VISIBLE
                    textButton1.text = "${times1}:${time1sec}".replace(" ", "");textButton2.text =
                        "${time1min}:${time2sec}".replace(" ", "")
                    false
                }
            }
            button.setOnClickListener {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                if (timeEdit.text.isEmpty()) {
                    textButton1.text = "$time1min:00";textButton2.text =
                        "$time2min:00"
                    timeEdit.visibility = INVISIBLE
                    button.visibility = INVISIBLE
                    imagePause.visibility = VISIBLE
                    imageSetting.visibility = VISIBLE
                    ImageReplay.visibility = VISIBLE
                } else {
                    times1 = timeEdit.text.toString();time1min = times1.toInt();time2min = times1.toInt()
                    timeEdit.visibility = INVISIBLE
                    button.visibility = INVISIBLE
                    imagePause.visibility = VISIBLE
                    imageSetting.visibility = VISIBLE
                    ImageReplay.visibility = VISIBLE
                    time1sec = 0
                    time2sec = 0
                    textButton1.text = "${times1}:${time1sec}".replace(" ", "");textButton2.text =
                        "${time1min}:${time2sec}".replace(" ", "")
                }

            }
        }
        binding.settingPlayerOne.setOnClickListener {
            if(a == 1){
                binding.imagePause.setImageResource(drawable.play)
                a = 2;timerOn = false
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
            }
            editTextSetting1.visibility = VISIBLE
            buttonSetting1.visibility = VISIBLE
            ButtonPlayerOne.isClickable = false
            ButtonPlayerTwo.isClickable = false
            editTextSetting1.setOnEditorActionListener { _, _, _ ->
                editTextSetting1.visibility = INVISIBLE
                buttonSetting1.visibility = INVISIBLE
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                if (editTextSetting1.text.isEmpty()) {
                    textButton1.text = "$time1min:$time1sec"
                    true
                } else {
                    times1 = editTextSetting1.text.toString();time1min = times1.toInt()
                    textButton1.text = "${times1}:$time2sec".replace(" ", "")
                    false
                }
            }
            buttonSetting1.setOnClickListener {
                editTextSetting1.visibility = INVISIBLE
                buttonSetting1.visibility = INVISIBLE
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                if (editTextSetting1.text.isEmpty()) {
                    textButton1.text = "$time1min:$time1sec"
                } else {
                    times1 = editTextSetting1.text.toString();time1min = times1.toInt()
                    textButton1.text = "${times1}:$time1sec".replace(" ", "")
                }
            }
        }
        binding.settingPlayerTwo.setOnClickListener {
            if(a == 1){
                binding.imagePause.setImageResource(drawable.play)
                a = 2;timerOn = false
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
            }
            editTextSetting2.visibility = VISIBLE
            buttonSetting2.visibility = VISIBLE
            ButtonPlayerOne.isClickable = false
            ButtonPlayerTwo.isClickable = false
            editTextSetting2.setOnEditorActionListener { _, _, _ ->
                editTextSetting2.visibility = INVISIBLE
                buttonSetting2.visibility = INVISIBLE
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                if (editTextSetting2.text.isEmpty()) {
                    textButton2.text = "$time2min:$time2sec"
                    true
                } else {
                    times1 = editTextSetting2.text.toString();time2min = times1.toInt()
                    textButton2.text = "${time2min}:$time2sec".replace(" ", "")
                    false
                }
            }
            buttonSetting2.setOnClickListener {
                editTextSetting2.visibility = INVISIBLE
                buttonSetting2.visibility = INVISIBLE
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(timeEdit.windowToken, 0)
                if (editTextSetting2.text.isEmpty()) {
                    textButton2.text = "$time2min:$time2sec"
                } else {
                    times1 = editTextSetting2.text.toString();time2min = times1.toInt()
                    textButton2.text = "${time2min}:$time2sec".replace(" ", "")
                }
            }
        }
        var clickCountButton1 = 0
        var clickCountButton2 = 0
        binding.ButtonPlayerOne.setOnClickListener {
            if (a == 1 && b == 1) {
                playerOnline = 2
                val clickCount1 = 1 + clickCountButton1++
                textView6.text = "Moves : $clickCount1"
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(244, 182, 59));b =
                    2;click.start()
                ButtonPlayerOne.isClickable = false
                textButton2.setTextColor(Color.WHITE)
                textButton1.setTextColor(Color.BLACK)
            }
            if (a == 2) {
                a = 1 ;timerOn = true
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(244, 182, 59))
                ButtonPlayerOne.isClickable = true
                textButton1.setTextColor(Color.WHITE)
                binding.imagePause.setImageResource(drawable.pause)
            }
            ButtonPlayerOne.startAnimation(animation)
            time()
            ButtonPlayerTwo.isClickable = true
        }

        binding.ButtonPlayerTwo.setOnClickListener {
            if (a == 1 && b == 2) {
                playerOnline = 1
                val clickCount2 = 1 + clickCountButton2++
                textView7.text = "Moves : $clickCount2"
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(244, 182, 59))
                b = 1;click.start()
                textButton2.setTextColor(Color.BLACK)
                textButton1.setTextColor(Color.WHITE)
            }

            if (a == 2) {
                a = 1;timerOn = true
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(244, 182, 59))
                textButton1.setTextColor(Color.WHITE)
                binding.imagePause.setImageResource(drawable.pause)
            }
            ButtonPlayerTwo.startAnimation(animation)
            time()
            ButtonPlayerTwo.isClickable = false
            ButtonPlayerOne.isClickable = true
        }
    }

    private var times1 = ""
    private var a = 2
    private var playerOnline = 1
    private var timerOn = false
    private var time1min = 5
    private var time2min = 5
    private var time1sec = 0
    private var time2sec = 0
    private var b = 1

    private fun time() {
        if (timerOn) {
            if (playerOnline == 1) {
                fun p1() {
                    if (time1sec == 0 && time1min != 0) {
                        time1min -= 1;time1sec = 60
                    }
                    if (time1sec == 60){
                        time1sec-=1
                    }
                    fun times(){time1sec -= 1}
                    val output1 = "$time1min:$time1sec"
                    Handler().postDelayed({ binding.textButton1.text = output1 }, 0)
                    Handler().postDelayed({
                        if (time1min <= 0 && time1sec <= 0) {
                            playerOnline = 0
                            ButtonPlayerOne.setBackgroundColor(Color.RED)
                            val lose = MediaPlayer.create(this, R.raw.loseeffect)
                            lose.start()
                            imagePause.visibility = INVISIBLE
                        }
                        if (time1sec >= 0 && playerOnline == 1 && a == 1) {
                            times()
                            p1()
                        }
                    }, 1000)

                }
                p1()
            }
            if (playerOnline == 2) {
                fun p2() {
                    if (time2sec == 0 && time2min != 0) {
                        time2min -= 1;time2sec =60
                    }
                    if (time2sec == 60){
                        time2sec-=1
                    }
                    fun times (){time2sec -= 1}
                    val output = "$time2min:$time2sec"
                    Handler().postDelayed({ binding.textButton2.text = output }, 0)
                    Handler().postDelayed({
                        if (time2min <= 0 && time2sec <= 0) {
                            playerOnline = 0
                            imagePause.visibility = INVISIBLE
                            ButtonPlayerTwo.setBackgroundColor(Color.RED)
                            val lose = MediaPlayer.create(this, R.raw.loseeffect)
                            lose.start()
                            imagePause.visibility = INVISIBLE
                        }
                        if (time2sec >= 0 && playerOnline == 2 && a == 1) {
                            times()
                            p2()
                        }
                    }, 1000)

                }
                p2()
            }
        }
    }
}

