package com.example.myapplication.OOPs

fun main(){
    val user=Admin("Haider","Ali",21)
    println(user.name)
    println(user.lastName)
    println(user.age)
    println("\n")
    val user2=Admin("Haider","Ali")
    println(user2.name)
    println(user2.lastName)
    println(user2.age)
    println("\n")
    val user3=Admin("Haider")
    println(user3.name)
    println(user3.lastName)
    println(user3.age)
}
class Admin(var name:String,var lastName:String,var age:Int){
    init {
        println("This is init block")
    }
    //We can use secondary constructors in Kotlin
    //Secondary constructor can have only parameters it doesnt have properties like primary
    //Secondary constructor pass the parameter value and some default values to primary constructor
    constructor(name:String):this(name,"lastName",0){
        //Secondary constructor can have its own body to execute some code at object creation
        println("This is 2nd constructor")
    }
    //We can have multiple secondary constructor
    constructor(name:String,lastName:String):this(name,lastName,0){
        println("This is 3rd constructor")
    }

}