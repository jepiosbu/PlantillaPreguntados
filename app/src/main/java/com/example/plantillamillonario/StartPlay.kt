package com.example.plantillamillonario

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
    var con = 9
    var r = 0
    var lista : MutableList<Tabla> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStartPlayBinding.inflate(layoutInflater)
        MilloDD = FirebaseDatabase.getInstance()
        DatosRef = MilloDD.getReference("Data")
        setContentView(binding.root)
        get()
        pregunta()
        option()
    }
    fun get(){
        DatosRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    var datos = it.getValue(Tabla::class.java)
                    var tabla = Tabla(datos!!.pregunta,datos!!.respuesta,datos!!.opt1,datos!!.opt2,datos!!.opt3,datos!!.opt4)
                    lista.add(tabla)
                }
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
            r = Random.nextInt(0,con)
            binding.question.text=lista[r].pregunta
            binding.optionA.text=lista[r].opt1
            binding.optionB.text=lista[r].opt2
            binding.optionC.text=lista[r].opt3
            binding.optionD.text=lista[r].opt4
            rpt=lista[r].respuesta
            lista.removeAt(r)
        },3000)
    }
    fun option(){
        binding.optionA.setOnClickListener {
            Toast.makeText(this, "${binding.optionA.text} ${rpt}", Toast.LENGTH_SHORT).show()
            if(binding.optionA.text==rpt){
                binding.optionA.setBackgroundResource(R.drawable.green)
                Handler().postDelayed({
                    con--
                    pregunta()
                },3000)

            }
            else
            {
                binding.optionA.setBackgroundResource(R.drawable.red)
                Handler().postDelayed({
                    con--
                    pregunta()
                },3000)
            }
        }
        binding.optionB.setOnClickListener {
            if(binding.optionB.text==rpt){
                binding.optionB.setBackgroundResource(R.drawable.green)
                Handler().postDelayed({
                    con--
                    pregunta()
                },3000)
            }
            else
            {
                binding.optionB.setBackgroundResource(R.drawable.red)
                Handler().postDelayed({
                    con--
                    pregunta()
                },3000)
            }
        }
        binding.optionC.setOnClickListener {
            if(binding.optionC.text==rpt){
                binding.optionC.setBackgroundResource(R.drawable.green)
                Handler().postDelayed({
                    con--
                    pregunta()
                },3000)
            }
            else
            {
                binding.optionC.setBackgroundResource(R.drawable.red)
                Handler().postDelayed({
                    con--
                    pregunta()
                },3000)
            }
        }
        binding.optionD.setOnClickListener {
            if(binding.optionD.text==rpt){
                binding.optionD.setBackgroundResource(R.drawable.green)
                Handler().postDelayed({
                    con--
                    pregunta()
                },3000)
            }
            else
            {
                binding.optionD.setBackgroundResource(R.drawable.red)
                Handler().postDelayed({
                    con--
                    pregunta()
                },3000)
            }
        }
    }
}
