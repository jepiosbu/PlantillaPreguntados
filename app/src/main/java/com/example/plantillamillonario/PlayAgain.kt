package com.example.plantillamillonario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.example.plantillamillonario.DB.Tabla
import com.example.plantillamillonario.databinding.ActivityMainBinding
import com.example.plantillamillonario.databinding.ActivityPlayAgainBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class PlayAgain : AppCompatActivity() {
    lateinit var binding: ActivityPlayAgainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPlayAgainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        play()
        add()
    }

    fun play(){
        binding.button2.setOnClickListener {
            startActivity(Intent(this@PlayAgain,StartPlay::class.java))
        }
    }
    fun add(){
        binding.button3.setOnClickListener {
            startActivity(Intent(this@PlayAgain,AddQuestion::class.java))
        }
    }
}