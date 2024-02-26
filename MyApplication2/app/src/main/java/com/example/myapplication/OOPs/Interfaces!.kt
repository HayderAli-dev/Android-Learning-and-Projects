package com.example.myapplication.OOPs

fun main(){
val tr=Truck.HeavyTruck("Ab")
    tr.lights()
}
//In interfaces the function has no body and variable has no initialization
//You have to implement all the members of interface in derived class
interface Engine{
    fun startEngine()
    var abc:String
}
interface Lights{
    fun lights()
}
open class Truck(override var abc: String) :Engine,Lights{
    override fun startEngine() {
        TODO("Not yet implemented")
    }

    override fun lights() {
       println("The light has start")
    }
class HeavyTruck(override var abc:String): Truck(abc) {
}
}