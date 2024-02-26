package com.example.myapplication.KotlinBasics

fun main(){
    //In kotlin each variable is non null by default
    // We cannot assign null to variable directly like this
    // var name:String=null
    //To make variable nullable we have to use ? with data type
    var name:String?="Ali"
    println(name)
    //Now we cannot use any function directly with this variable because we declared it as nullable
    //Method 1
    if (name != null) {
        println(name.length)
    }
    //Method 2
    println(name?.length) //Now by using ? if it is null it will print length as null
    println(name!!.length)//Now by using !! if it is null it will through null pointer exception

    //We can assign a default value to variable if the assigned value can be null as
    var text:String?=null
    var text2=text?:"This is null"
    println(text2)
    text="Haider"
     text2=text?:"This is null"
    println(text2)
}