package com.example.myapplication.OOPs



fun main() {
    //This object will be initialized now
 val obj1=Users("Ali",21)
    //This object will not be initialized even we have declare this , It will be initialize when it will be used for the first time
    val obj2 by lazy { Users("Haider",22) }
    //It will be initialized now
    println(obj2.firstName)
    println(obj2.age)
}

/*
 Lazy initialization is a technique used to defer the initialization of a property
 until it is accessed for the first time.
 This can be useful for properties that are computationally expensive to initialize
  or for properties that depend on other parts of the system
   that may not be ready at the time of object creation.
 */
class Users(var firstName:String,var age:Int){
    init {
        println("User $firstName is created.")
    }
}
