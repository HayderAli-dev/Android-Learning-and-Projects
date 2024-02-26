package com.example.kotlinfirst

class FirstAsClass {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val kb=AbC()
            println(kb.a)
            println(kb.b)
            println(kb.add(3,4))
            println(AbC.subtract(4,5))
        }
    }
}
class AbC {

    constructor(a:Int) {
        println(a)
    }
    constructor(){
        println("This is a constructor")
    }
    var a=3
    var b=4
    fun add(a:Int,b:Int):Int{
        return a+b
    }
    companion object{
        var num=38
        fun subtract(a:Int,b:Int):Int{
            return a-b
        }
    }
}