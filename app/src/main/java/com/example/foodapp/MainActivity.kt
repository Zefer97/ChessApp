package com.example.foodapp

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.foodapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.ButtonplayerOne
import kotlinx.android.synthetic.main.activity_main.ButtonplayerTwo
import kotlinx.android.synthetic.main.activity_main.ImageReplay
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.imagePause
import kotlinx.android.synthetic.main.activity_main.imageSetting
import kotlinx.android.synthetic.main.activity_main.timeedit

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

        val mp = MediaPlayer.create(this,R.raw.click)
        binding.imagePause.setOnClickListener {
            if (a == 2) { binding.imagePause.setBackgroundResource(R.drawable.pause);a = 1;timerOn = true;time() }
            else if (a == 1) { binding.imagePause.setBackgroundResource(R.drawable.play);a = 2;timerOn = false}
        }

        binding.imageSetting.setOnClickListener {
                timeedit.visibility = View.VISIBLE
                button.visibility = View.VISIBLE
                imageSetting.visibility = View.INVISIBLE
                ImageReplay.visibility = View.INVISIBLE
                imagePause.visibility = View.INVISIBLE
            button.setOnClickListener {
                times1 = timeedit.text.toString();time1 = times1.toInt();time2 = times1.toInt()
                timeedit.visibility = View.INVISIBLE
                button.visibility= View.INVISIBLE;
                imageSetting.visibility = View.VISIBLE
                ImageReplay.visibility = View.VISIBLE
                imagePause.visibility = View.VISIBLE
                ButtonplayerOne.text = "$times1:00";ButtonplayerTwo.text =
                "$times1:00"
            }
        }

        binding.ImageReplay.setOnClickListener { startActivity(Intent(this, MainActivity::class.java))}

        binding.ButtonplayerOne.setOnClickListener { if (a == 1 && b == 1) { playerOn = 2
            binding.ButtonplayerOne.setBackgroundColor(Color.rgb(149, 149, 149));ButtonplayerOne.setTextColor(Color.BLACK)
            binding.ButtonplayerTwo.setBackgroundColor(Color.rgb(244, 182, 59));ButtonplayerTwo.setTextColor(Color.WHITE);b = 2;time();mp.start()}}

        binding.ButtonplayerTwo.setOnClickListener { if (a == 1 && b == 2) { playerOn = 1
            binding.ButtonplayerTwo.setBackgroundColor(Color.rgb(149, 149, 149));ButtonplayerOne.setTextColor(Color.BLACK)
            binding.ButtonplayerOne.setBackgroundColor(Color.rgb(244, 182, 59));ButtonplayerTwo.setTextColor(Color.WHITE);b = 1;time();mp.start()}}


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
                    Handler().postDelayed({ binding.ButtonplayerOne.text = output1 }, 1000)
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
                    Handler().postDelayed({ binding.ButtonplayerTwo.text = output }, 1000)
                    Handler().postDelayed({ if (time2 <= 0 && time21 <= 0) { playerOn = 0 }
                    if (time21 >= 0 && playerOn == 2 && a == 1) { p2() } }, 1000)

                }
                    p2()
               }
            }
        }
    }
