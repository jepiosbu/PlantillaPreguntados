package com.example.plantillamillonario

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.plantillamillonario.DB.Tabla
import com.example.plantillamillonario.databinding.ActivityAddQuestionBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class AddQuestion : AppCompatActivity() {
    lateinit var binding: ActivityAddQuestionBinding
    lateinit var DB: FirebaseDatabase
    lateinit var UseRef : DatabaseReference
    var con = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddQuestionBinding.inflate(layoutInflater)
        DB = FirebaseDatabase.getInstance()
        UseRef = DB.getReference("Data")
        setContentView(binding.root)
        key()
        save()
    }

    fun key(){
        UseRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                snapshot.children.forEach {
                    var data = it.getValue(Tabla::class.java)
                    con++
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AddQuestion, "error", Toast.LENGTH_SHORT).show()
            }
        })
    }
    fun save(){
        binding.button.setOnClickListener {
            UseRef.child("${con + 1}").setValue(
                Tabla(
                    binding.pregunta.text.toString(),
                    binding.respuesta.text.toString(), binding.opcion1.text.toString(),
                    binding.opcion2.text.toString(), binding.opcion3.text.toString(),
                    binding.opcion4.text.toString()
                )
            )
            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show()
            startActivity(Intent(this,PlayAgain::class.java))
        }
    }
}