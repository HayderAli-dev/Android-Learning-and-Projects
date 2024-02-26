package com.example.kotlinfirst

fun main(){
val objectA=ClassB(9)
    val objectB=ClassA(5)
    println(objectA.add(3,4))
    println(objectB.subtract(45,34))
    println(objectA.subtract(45,34))

}
open class ClassA(a:Int){
    init {
        println("This is parent constructor $a")
    }
    fun add(a:Int,b:Int):Int{
        return a+b
    }
    open fun subtract(a:Int,b:Int):Int{
        return a-b
    }
}
class ClassB (a:Int): ClassA(a){
    init {
        println("This is Child constructor")
    }
    override fun subtract(a: Int, b: Int): Int {
       val sub=super.subtract(a, b)
        val sqr=sub*sub
        return sqr
    }
}