package com.example.myapplication.Functions

fun main(){
    println(getMax(1,2))
    println(getMax2(1,2))
    println(getMax3(1,2))
    simple()
}
fun getMax(a:Int,b:Int):Int{
    val max=if (a>b) a else b
    return max
}
fun getMax2(a:Int,b:Int):Int{
    return if (a>b) a else b
}
//Single line function
fun getMax3(a:Int,b:Int):Int=if (a>b) a else b
//Simple return in function without return type
fun simple(){
    println("Hello Haider")
    return
    //Now the Ali will not be printed
    println("Ali")
}