package com.example.myapplication.OOPs

fun main(){
var car1= Car("Tesla","S Plaid","Red","4")
//    car1.name="Tesla"
//    car1.model="S Plaid"
//    car1.color="Red"
//    car1.doors="4"
    println("Name: ${car1.name}")
    println("Name: ${car1.model}")
    println("Name: ${car1.color}")
    println("Name: ${car1.doors}")
    car1.move()
    car1.stop()
    println("\n")
    var car2= Car("Ford","Mustang","Black","2")
//    car2.name="Ford"
//    car2.model="Mustang"
//    car2.color="Black"
//    car2.doors="2"
    println("Name: ${car2.name}")
    println("Name: ${car2.model}")
    println("Name: ${car2.color}")
    println("Name: ${car2.doors}")
    car2.move()
    car2.stop()

}
/*
This class is made without constructor
class Car{
    var name=""
    var model=""
    var color=""
    var doors=""
    fun move(){
        println("The Car $name is Moving")
    }
    fun stop(){
        println("The car $name has stopped")
    }
}
 */
//This is class with primary constructor with properties
class Car(var name: String, var model: String, var color: String, var doors: String){
    fun move(){
        println("The Car $name is Moving")
    }
    fun stop(){
        println("The car $name has stopped")
    }
}
/* This is class with primary constructor with parameters and properties inside the constructor
class Car(name:String,model:String,color:String,doors:String){
    var name=name
    var model=model
    var color=color
    var doors=doors
    fun move(){
        println("The Car $name is Moving")
    }
    fun stop(){
        println("The car $name has stopped")
    }
}
 */