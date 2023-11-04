package com.example.plantillamillonario.DB

data class Tabla(val pregunta:String,val respuesta:String, val opt1:String,val opt2:String,val opt3:String,val opt4:String)
{
    constructor(): this("","","","","","")
}
