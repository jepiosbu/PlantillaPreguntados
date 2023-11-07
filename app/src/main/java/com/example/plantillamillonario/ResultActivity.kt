package com.example.plantillamillonario

import android.content.Intent
import android.graphics.Color
import android.media.MediaPlayer
import android.media.browse.MediaBrowser.MediaItem
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.plantillamillonario.StartPlay.Companion.time
import com.example.plantillamillonario.StartPlay.Companion.winover
import com.example.plantillamillonario.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    lateinit var binding:ActivityResultBinding
    lateinit var winner:MediaPlayer
    lateinit var gameover:MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)
        winner=MediaPlayer.create(this,R.raw.winner)
        gameover=MediaPlayer.create(this,R.raw.gameover)
        result()
        option()
    }
    fun result(){
        if(winover=="Game Over"){
            gameover.start()
            binding.winover.setTextColor(resources.getColor(R.color.red))
            binding.winover.text = winover
            binding.timer.append(time.toString()+"s")
        }
        else
        {
            winner.start()
            binding.winover.setTextColor(resources.getColor(R.color.yellow))
            binding.winover.text = winover
            binding.timer.text="Su tiempo fue de: ${time}s"

        }
    }
    fun option(){
        binding.play.setOnClickListener{
            startActivity(Intent(this@ResultActivity,StartPlay::class.java))
            winner.pause()
            gameover.pause()
        }
        binding.back.setOnClickListener {
            startActivity(Intent(this@ResultActivity,PlayAgain::class.java))
            winner.pause()
            gameover.pause()
        }
    }
}