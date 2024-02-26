package com.example.myapplication.Conditional

fun main(){
    //Method 1
    var alaram=12
    when(alaram){
        12-> println("The time is $alaram")
        7-> println("The time is $alaram")
        else-> println("The time is $alaram")
    }
    //method 2
    alaram=10
    when(alaram){
        12,7,9,10-> println("The time is $alaram")
        else-> println("The time is $alaram")
    }
    //Method 3 using range concept
    alaram=19
    when(alaram){
        // Range is used from 1 to 20
        in 1..20-> println("The time is range $alaram")
        else-> println("The time is $alaram")
    }
    when(alaram){
        // Range is used from 1 to 20
       !in 1..20-> println("The time is in range $alaram")
        else-> println("The time is not in range $alaram")
    }
    //Method 4 when statement can be used as expression
    val num=1
    val text=when(num){
        1->"You are a good boy"
        else->"You are a bad boy"
    }
    println(text)

    alaram=11
    val txt=when{
        alaram>10->"Value is less than 10"
        alaram==8 || alaram<10->"The alaram is 8 or less"
        else->"$alaram"
    }
}