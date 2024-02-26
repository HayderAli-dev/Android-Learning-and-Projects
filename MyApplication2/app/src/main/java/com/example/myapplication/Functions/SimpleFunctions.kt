package com.example.myapplication.Functions

fun main(){
    greetings()
    greeting2("Fakhar")
    println(greeting3())
    println(greeting4("Najabat"))
}
//Type 1 No Parameter No Return Type
fun greetings(){
    println("Hello Haider")
}
//Type 2 Parameters but No Return Type
fun greeting2(name:String){
    println("Hello $name")
}
//Type 3 No Parameter but Return Type
fun greeting3():String{
    return "Hello Haider"
}
//Type 4 Parameters with Return Type
fun greeting4(name:String):String{
    return "Hello $name"
}