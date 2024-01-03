package com.santosgo.mavelheroes

data class Hero(
    val name : String = "",
    val power : Int = 0,
    val intelligence : Int = 0,
    val photo : String = "",
    private val description : String = "",
)
