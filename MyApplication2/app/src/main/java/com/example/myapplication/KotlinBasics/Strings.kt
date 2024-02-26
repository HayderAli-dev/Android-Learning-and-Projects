package com.example.myapplication.KotlinBasics


fun main(){
   /* Strings are used for storing texts
    You don't have to specify variable type in kotlin like other languages
    But if you want to assign the value later you must specify the data type
    */
    val firstName = "Haider"
    println(firstName[0])
    val lastName="Ali"
    //There are placeholders in Kotlin ${x+y}
    println("The name is ${firstName+lastName}")
    println("The length of first name is ${(firstName+lastName).length}")


}