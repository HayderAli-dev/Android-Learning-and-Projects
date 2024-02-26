package com.example.myapplication.OOPs

fun main(){
val car=Cars("BMW","Red",1,4)
    car.move()
}
//To inherit class it should be open
//Generic class should only have basic values and methods
open class Vehicles(var name:String, var color:String){
    //We have to open function to override
   open fun move(){
        println("The car $name is moving")
    }
    fun stop(){
        println("The car $name is stopped")
    }
}
//We need to pass values to constructor to inherit this class
class Cars(name:String,color:String,var engines:Int,var doors:Int):Vehicles(name,color){
    override fun move() {
        println("The car is flying")
        super.move()
    }
}