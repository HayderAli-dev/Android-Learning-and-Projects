package com.example.myapplication.KotlinBasics
/*
   Kotlin has 8 data Types
   ---> For Storing Numbers
   1.Byte
   2.Short
   3.Int
   4.Long
   --->For Storing Decimals
   5.Float
   6.Double
   --->For Storing Single Characters
   7.Char
   --->For Checks
   8.Boolean
   --->String is not a data type , it is a class
    */
fun main(){
    var numberByte:Byte=127
    var numberShort:Short=32767
    var numberInteger:Int=12352178
    var numberLong:Long=73815321576357152
    var charc:Char='M'
    var floatNumber:Float=12.2f
    var doubleNumber:Double=56.4
    println("Write your name")
    var name: String = readln()
    println("The first name of user is $name")
    name= name+" Ali"
    println("The full name of user is $name")
}