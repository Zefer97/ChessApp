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
import kotlinx.android.synthetic.main.activity_main.ImageReplay
import kotlinx.android.synthetic.main.activity_main.button
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

        val mp = MediaPlayer.create(this,R.raw.click)
        binding.imagePause.setOnClickListener {
            if (a == 2) { binding.imagePause.setBackgroundResource(R.drawable.pause);a = 1;timerOn = true;time()
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(244, 182, 59))}
            else if (a == 1) { binding.imagePause.setBackgroundResource(R.drawable.play);a = 2;timerOn = false
                binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(149, 149, 149))
                binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))}
        }

        binding.imageSetting.setOnClickListener {
                timeEdit.visibility = View.VISIBLE
                button.visibility = View.VISIBLE
                imageSetting.visibility = View.INVISIBLE
                ImageReplay.visibility = View.INVISIBLE
                imagePause.visibility = View.INVISIBLE
            button.setOnClickListener {
                times1 = timeEdit.text.toString();time1 = times1.toInt();time2 = times1.toInt()
                timeEdit.visibility = View.INVISIBLE
                button.visibility= View.INVISIBLE;
                imageSetting.visibility = View.VISIBLE
                ImageReplay.visibility = View.VISIBLE
                imagePause.visibility = View.VISIBLE
                textButton1.text = "$times1:00";textButton2.text =
                "$times1:00"
            }
        }
        var clickCount1 = 0
        var clickCount2 = 0
        binding.ImageReplay.setOnClickListener { startActivity(Intent(this, MainActivity::class.java))}

        binding.ButtonPlayerOne.setOnClickListener { if (a == 1 && b == 1) { playerOn = 2
            var clickCount1 = 1+clickCount1++
            clickCount1
            textView6.text = "Moves : $clickCount1"
            binding.ButtonPlayerOne.setBackgroundColor(Color.rgb(149, 149, 149))
            binding.ButtonPlayerTwo.setBackgroundColor(Color.rgb(244, 182, 59));b = 2;time();mp.start()}}

        binding.ButtonPlayerTwo.setOnClickListener { if (a == 1 && b == 2) { playerOn = 1
            var clickCount2 = 1+clickCount2++
            clickCount2
            textView7.text = "Moves : $clickCount1"
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
