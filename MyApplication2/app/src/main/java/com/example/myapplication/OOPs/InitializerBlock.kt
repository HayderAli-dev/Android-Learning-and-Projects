package com.example.myapplication.OOPs

fun main(){
    val user=User("Haider")
}
// init block is used to put code which is executed when an object of the class is created
class User(name:String){
    var name:String
    init{
        if (name.lowercase().startsWith("h")){
            this.name=name
            println("The name starts with H")
            println("Name: ${this.name}")
        } else{
            this.name=name
            println("The name does not start with H")
            println("Name: ${this.name}")
        }
    }
}