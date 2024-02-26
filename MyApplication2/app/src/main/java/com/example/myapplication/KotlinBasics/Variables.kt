package com.example.myapplication.KotlinBasics

fun main(){
    //Two ways of Declaring variables in Kotlin
    /*---> using "var" keyword ---> By using this you can also update
    variable value later in the code
     */
    var userName:String="Haider"
    println(userName)
    userName="Ali"
    //You can user dollar sign to place variable inside a string
    println("The updated value is $userName")
    /*
    2nd way is using "val" key word by using this keyword you cannot update
    variable value later , the firstly assigned value become permanent
     */
    //Its not necessary to give datatype in kotlin
    //Compiler will automatically determine datatype from firstly assigned data
    val fatherName="Najabat Ali"
    println("$fatherName")

}