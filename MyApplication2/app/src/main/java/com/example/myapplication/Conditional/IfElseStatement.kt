package com.example.myapplication.Conditional

fun main(){
    // Method 1
    val isActive=true
    if (isActive){
        println("The user is active")
    } else{
        println("The user is not active")
    }
    //Method 2
    val text=if (isActive){
        "The user is active"
    } else{
        "The user is not active"
    }
    println(text)

}