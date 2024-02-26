package com.example.myapplication.OOPs

fun main(){
    //If strings have same data it is same object
    var name1="Hello"
    var name2="Hell"
    //Structural equality checks if two objects have the same contents or state.
    println(name1==name2)
    //Referential equality, also known as identity equality,
    // checks if two references point to the same object in memory.
    println(name1===name2)
}