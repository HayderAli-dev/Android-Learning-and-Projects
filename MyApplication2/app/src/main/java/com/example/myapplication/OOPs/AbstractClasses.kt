package com.example.myapplication.OOPs

fun main(){
val car=Car1()
    car.move()
    car.stop()
    car.brake()
}
//We cannot create object of abstract class
//We have to inherit classes from abstract class
//We can have abstract methods in abstract class which have no body
//These abstract methods needs to be implemented in derived classes
//This is mostly used as base class to be derived
abstract class Vehicle(){
    abstract var abc:String
    fun move(){
 println("The car will move")
    }
    fun stop(){
println("The car will move")
    }
    abstract fun brake()
}
/*
Why we use abstract class over interfaces
Abstract class can be open or close but interfaces are always opened
Abstract classes can have methods with implementation
Interface can have only no body functions
 */
class Car1:Vehicle(){
    override var abc: String
        get() = TODO("Not yet implemented")
        set(value) {}

    override fun brake() {
println("The car is braked")
    }

}