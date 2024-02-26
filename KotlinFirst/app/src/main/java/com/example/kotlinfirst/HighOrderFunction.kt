package com.example.kotlinfirst

fun main(){
    highOrder(add)
}
val add={x:Int,y:Int->x+y}
fun highOrder(func:(Int,Int)->Int):(Int,Int)->Int{
    println("The sum is "+func(7,8))
    return func
}