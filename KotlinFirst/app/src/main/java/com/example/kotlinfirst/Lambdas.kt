package com.example.kotlinfirst

fun main(){
    println(sqr(7))
    println(sum(3,4))
    printName("Ali")
}

val sqr={x:Int->x*x}
val sum:(Int,Int)->Int={x,y->x+y}
val printName={ name:String->println("My name is Haider "+name) }