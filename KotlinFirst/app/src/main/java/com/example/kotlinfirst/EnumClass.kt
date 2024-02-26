package com.example.kotlinfirst

fun main(){
println("The day today is ${Days.MONDAY}")
    for(day in Days.values()){
if (day.Holiday==true){
    println(""+day +"is a holiday")
}
    }
}
enum class Days(val Holiday:Boolean=false){
    SUNDAY(true),SATURDAY(true),MONDAY,TUESDAY,WEDNESDAY,THURSDAY,FRIDAY
}