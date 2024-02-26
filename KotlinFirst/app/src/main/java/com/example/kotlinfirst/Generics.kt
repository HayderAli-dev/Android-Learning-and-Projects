package com.example.kotlinfirst

fun main(){
val ha=GenClass(7)
}
class GenClass<T>(valu:T) {
    init {
        println("The value is $valu")
        simple<T>(valu)
    }
}

    fun<T> simple(text:T){
        println("The text is "+text)

    }