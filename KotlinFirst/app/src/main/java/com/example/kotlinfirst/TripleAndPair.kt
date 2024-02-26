package com.example.kotlinfirst

class TripleAndPair {
    //Pair
    companion object{
        @JvmStatic
    fun main(args:Array<String>) {
        val (a, b) = Pair("1", 3)
        var pair = Pair("a", "b")
            println(a)
            println(b)
            println(pair.first+pair.second)

            val pair3=Pair("Haider",Pair("Fakhar",Pair("Ali","Dogar")))
            println(pair3.first+pair3.second.second.first+pair3.second.second.second)

            val triple=Triple("Haider","Ali","Dogar")
            println(triple.first.plus(triple.second).plus(triple.third))
    }
    }
}