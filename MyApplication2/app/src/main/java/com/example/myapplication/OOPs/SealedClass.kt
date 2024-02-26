package com.example.myapplication.OOPs

import java.lang.Exception

fun main(){
val success=Result.Success("Success")
    getData(success)
    val circle=Shape.Circle(1.2)
    val square=Shape.Square(1.2)
    println(calculateArea(circle))
    println(calculateArea(square))
}
fun getData(result: Result){
    when(result) {

        is Result.Success -> result.showMessage()
        is Result.Error.NonRecoverableError -> result.showMessage()
        is Result.Error.RecoverableError -> result.showMessage()
    }
}
/*
A sealed class is a class that can have a fixed set of subclasses.
 This means that all subclasses of a sealed class are known at compile time,
  and no other subclasses can be created outside of the sealed class.
  Sealed classes are often used to represent restricted class hierarchies,
  where all possible subclasses are known.
 */
sealed class Result(val message:String){
    class Success(message: String):Result(message)
   sealed class Error(message: String):Result(message){
        class RecoverableError(exception: Exception,message:String):Error(message)
       class NonRecoverableError(exception: Exception,message:String):Error(message)

    }
    fun showMessage(){
println("Result: $message")
    }
}

sealed class Shape {
    class Circle(val radius: Double) : Shape()
    class Square(val sideLength: Double) : Shape()
}

fun calculateArea(shape: Shape): Double {
    return when (shape) {
        is Shape.Circle -> Math.PI * shape.radius * shape.radius
        is Shape.Square -> shape.sideLength * shape.sideLength
    }
}
