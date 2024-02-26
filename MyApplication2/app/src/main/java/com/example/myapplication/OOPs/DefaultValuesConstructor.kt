package com.example.myapplication.OOPs

fun main(){
val user=simple(age=21, lastName = "Ali", firstName = "Haider")
    println("${user.firstName} ${user.lastName} ${user.age}")
}
//Default Values can be provided in primary constructor in place of creating multiple secondary constructors
//Use init block to execute block of code that should be executed every time at initialization of each object
//But sometimes you have to execute block of code in special case only then use secondary constructors to execute block of specific code
class simple(var firstName:String="FirstName",var lastName:String="LastName",var age:Int=0)