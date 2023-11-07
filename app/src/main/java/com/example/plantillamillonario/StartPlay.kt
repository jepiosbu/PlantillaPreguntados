package com.example.plantillamillonario

import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.Toast
import com.example.plantillamillonario.DB.Tabla
import com.example.plantillamillonario.databinding.ActivityStartPlayBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlin.random.Random

class StartPlay : AppCompatActivity() {
    lateinit var binding : ActivityStartPlayBinding
    lateinit var MilloDD: FirebaseDatabase
    lateinit var DatosRef: DatabaseReference
    var rpt=""
    var puntaje=0
    var con = 0
    var r = 0
    var lista : MutableList<Tabla> = mutableListOf()
    lateinit var mp:MediaPlayer
    companion object{
        var time=0
        var winover = ""
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartPlayBinding.inflate(layoutInflater)
        MilloDD = FirebaseDatabase.getInstance()
        DatosRef = MilloDD.getReference("Data")
        setContentView(binding.root)
        mp = MediaPlayer.create(this,R.raw.ambiente)
        time=0
        winover = ""
        get()
        pregunta()
        option()
        mp.start()
        timer()
    }
    fun get(){
        DatosRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    var datos = it.getValue(Tabla::class.java)
                    var tabla = Tabla(datos!!.pregunta,datos!!.respuesta,datos!!.opt1,datos!!.opt2,datos!!.opt3,datos!!.opt4)
                    lista.add(tabla)
                }
                con = lista.size
            }
            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@StartPlay, "error", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun pregunta(){
        binding.optionA.setBackgroundResource(R.drawable.boxquestion)
        binding.optionB.setBackgroundResource(R.drawable.boxquestion)
        binding.optionC.setBackgroundResource(R.drawable.boxquestion)
        binding.optionD.setBackgroundResource(R.drawable.boxquestion)
        Handler().postDelayed({
            con = lista.size
            if(lista.size==0)
            {
                winover = "Your Winner"
                startActivity(Intent(this@StartPlay,ResultActivity::class.java))
                mp.stop()
            }
            else{
                binding.price.text = puntaje.toString()
                r = Random.nextInt(0,con)
                binding.question.text=lista[r].pregunta
                binding.optionA.text=lista[r].opt1
                binding.optionB.text=lista[r].opt2
                binding.optionC.text=lista[r].opt3
                binding.optionD.text=lista[r].opt4
                rpt=lista[r].respuesta
                lista.removeAt(r)
            }
        },2000)
    }
    fun option(){
        binding.optionA.setOnClickListener {
            if(binding.optionA.text==rpt){
                binding.optionA.setBackgroundResource(R.drawable.green)
                puntaje+=100000
                Handler().postDelayed({
                    pregunta()
                },1000)

            }
            else
            {
                binding.optionA.setBackgroundResource(R.drawable.red)
                Handler().postDelayed({
                    mp.pause()
                    winover = "Game Over"
                    startActivity(Intent(this@StartPlay,ResultActivity::class.java))
                },1000)
            }
        }
        binding.optionB.setOnClickListener {
            if(binding.optionB.text==rpt){
                binding.optionB.setBackgroundResource(R.drawable.green)
                puntaje+=100000
                Handler().postDelayed({
                    pregunta()
                },1000)
            }
            else
            {
                binding.optionB.setBackgroundResource(R.drawable.red)
                Handler().postDelayed({
                    mp.pause()
                    winover = "Game Over"
                    startActivity(Intent(this@StartPlay,ResultActivity::class.java))
                },1000)
            }
        }
        binding.optionC.setOnClickListener {
            if(binding.optionC.text==rpt){
                binding.optionC.setBackgroundResource(R.drawable.green)
                puntaje+=100000
                Handler().postDelayed({
                    pregunta()
                },1000)
            }
            else
            {
                binding.optionC.setBackgroundResource(R.drawable.red)
                Handler().postDelayed({
                    mp.pause()
                    winover = "Game Over"
                    startActivity(Intent(this@StartPlay,ResultActivity::class.java))
                },1000)
            }
        }
        binding.optionD.setOnClickListener {
            if(binding.optionD.text==rpt){
                binding.optionD.setBackgroundResource(R.drawable.green)
                puntaje+=100000
                Handler().postDelayed({
                    pregunta()
                },1000)
            }
            else
            {
                binding.optionD.setBackgroundResource(R.drawable.red)
                Handler().postDelayed({
                    mp.pause()
                    winover = "Game Over"
                    startActivity(Intent(this@StartPlay,ResultActivity::class.java))
                },1000)
            }
        }
    }
    fun timer(){
        if (winover==""){
            Handler().postDelayed({
                time++
                timer()
            },1000)
        }
    }
}
