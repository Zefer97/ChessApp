package com.example.foodapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.View.INVISIBLE
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.ImageReplay
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.buttonSetting1
import kotlinx.android.synthetic.main.activity_main.buttonSetting2
import kotlinx.android.synthetic.main.activity_main.editTextSetting1
import kotlinx.android.synthetic.main.activity_main.editTextSetting2
import kotlinx.android.synthetic.main.activity_main.imagePlayPause
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
//        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        supportActionBar?.hide()

        window.decorView.apply {
            // Hide both the navigation bar and the status bar.
            // SYSTEM_UI_FLAG_FULLSCREEN is only available on Android 4.1 and higher, but as
            // a general rule, you should design your app to hide the status bar whenever you
            // hide the navigation bar.
            systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION or View.SYSTEM_UI_FLAG_FULLSCREEN
        }
        binding.ImageReplay.setOnClickListener { startActivity(Intent(this, MainActivity::class.java))}

        val mp = MediaPlayer.create(this,R.raw.click)

        binding.imagePlayPause.setOnClickListener {
            if (a == 2) {;a = 1;timerOn = true;time()
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(244, 182, 59))
                binding.settingPlayerOne.visibility = INVISIBLE
                binding.settingPlayerTwo.visibility = INVISIBLE
            }}

        binding.imageView3.setOnClickListener {
            if (a == 1) { ;a = 2;timerOn = false
                binding.settingPlayerOne.visibility = View.VISIBLE
                binding.settingPlayerTwo.visibility = View.VISIBLE
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))} }

        binding.imageSetting.setOnClickListener {
                timeEdit.visibility = View.VISIBLE
                button.visibility = View.VISIBLE
                imageSetting.visibility = INVISIBLE
                ImageReplay.visibility = INVISIBLE
                imagePlayPause.visibility = INVISIBLE
            button.setOnClickListener {
                times1 = timeEdit.text.toString();time1 = times1.toInt();time2 = times1.toInt()
                timeEdit.visibility = INVISIBLE
                button.visibility= INVISIBLE
                imageSetting.visibility = View.VISIBLE
                ImageReplay.visibility = View.VISIBLE
                imagePlayPause.visibility = View.VISIBLE
                textButton1.text = "$times1:00";textButton2.text =
                "$times1:00"
            }
        }
        binding.settingPlayerOne.setOnClickListener {
            editTextSetting1.visibility = View.VISIBLE
            buttonSetting1.visibility = View.VISIBLE
            buttonSetting1.setOnClickListener {
                times1 = editTextSetting1.text.toString();time1 = times1.toInt()
                editTextSetting1.visibility = INVISIBLE
                buttonSetting1.visibility= INVISIBLE
                textButton1.text = "$times1:00"
            }
        }
        binding.settingPlayerTwo.setOnClickListener {
            editTextSetting2.visibility = View.VISIBLE
            buttonSetting2.visibility = View.VISIBLE
            buttonSetting2.setOnClickListener {
                times1 = editTextSetting2.text.toString();time2 = times1.toInt()
                editTextSetting2.visibility = INVISIBLE
                buttonSetting2.visibility= INVISIBLE
                textButton2.text = "$times1:00"
            }
        }
        var clickCountButton1 = 0
        var clickCountButton2 = 0

        binding.ButtonPlayerOne.setOnClickListener { if (a == 1 && b == 1) { playerOn = 2
            val clickCount1 = 1+clickCountButton1++
            textView6.text = "Moves : $clickCount1"
            binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
            binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(244, 182, 59));b = 2;time();mp.start()}}

        binding.ButtonPlayerTwo.setOnClickListener { if (a == 1 && b == 2) { playerOn = 1
            val clickCount2 = 1+clickCountButton2++
            textView7.text = "Moves : $clickCount2"
            binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
            binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(244, 182, 59));b = 1;time();mp.start()}}


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
                    if (time12 == 0 && time1 != 0) { time1 -= 1;time12 = 60 }
                    time12 -= 1
                    var output1 = "$time1:$time12"
                    if (time1 == 0 && time12 == 0){
                        output1 = "GAME OVER :( TRY AGAIN :)"
                    }
                    Handler().postDelayed({ binding.textButton1.text = output1 }, 1000)
                    Handler().postDelayed({ if (time1 <= 0 && time12 <= 0) { playerOn = 0 }
                    if (time12 >= 0 && playerOn == 1 && a == 1) { p1() } }, 1000)

                }
                p1()
            }
            if (playerOn == 2) {
                fun p2(){
                    if (time21 == 0 && time2 != 0) { time2 -= 1;time21 = 60 }
                    time21 -= 1
                    var output = "$time2:$time21"
                    if (time2 == 0 && time21 == 0){
                        output = "GAME OVER :( TRY AGAIN :)"
                    }
                    Handler().postDelayed({ binding.textButton2.text = output }, 1000)
                    Handler().postDelayed({ if (time2 <= 0 && time21 <= 0) { playerOn = 0 }
                    if (time21 >= 0 && playerOn == 2 && a == 1) { p2() } }, 1000)

                }
                    p2()
               }
            }
        }
    }
