package com.example.myapplication.Functions

/*
We can make multiple functions with same name and
 different number or type parameters
 */
fun main(){
    greeting()
    greeting("Ali")
    println( greeting("Namaste","Ali"))
    greeting(0)

}
fun greeting(){
    println("Hello")
}
fun greeting(name:String){
    println("Hello $name")
}
fun greeting(greet:String,name:String)="$greet $name"
fun greeting(a:Int){
    println("Hello $a")
}

