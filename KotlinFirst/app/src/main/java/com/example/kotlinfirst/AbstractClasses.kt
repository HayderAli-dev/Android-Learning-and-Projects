package com.example.kotlinfirst

fun main(){
    println(MAIN().add())
    println(MAIN().sub())
}
abstract class AB{
    abstract fun add():String
    fun sub():String{
        return "Will return Sub"
    }
}
class MAIN : AB() {
    override fun add(): String {
        return "Will return Addition"
    }

}