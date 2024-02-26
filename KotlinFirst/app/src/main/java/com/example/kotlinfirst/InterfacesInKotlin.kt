package com.example.kotlinfirst

fun main(){
    println("The sum is "+ClassAli().add(1,2))
    println("The sum of square is "+ClassHaider().add(1,2))
}


interface Calculator{
    fun add(a:Int,b:Int):Int{
        return 9000
    }
}
class ClassAli:Calculator{
    override fun add(a: Int, b: Int): Int {
      return a+b
    }
}
class ClassHaider:Calculator{

}