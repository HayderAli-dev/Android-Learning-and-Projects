package com.example.kotlinfirst

fun main(){
    println(Audi().display(5678))
}
open class Car{
    fun carAcce(a:Int):String{
        println("The car is accelerated at speed $a")
        return "Car Accelerated"
    }
}
class Audi:Car(){
    init {
        println("Step 1"+super.carAcce(7))
    }
    var chasisNo=1234
    fun display(chasisNo:Int){
        println("This is function no"+chasisNo)
        println("This is class no"+this.chasisNo)
    }

}