package com.example.myapplication.OOPs

fun main(){
    val obj=GetSet("Ali")
    println(obj.name)
    obj.age="22"
    println(obj.age)
}
class GetSet(name:String){
    var name:String=name
        //In Kotlin Getter Setters are implicitly defined as
        //So we dont need to define them explicitly
        //Can be used for putting some extra code in get() or set()
        get() {
           return "Name : $field"
        }
        set(value) {field=value}
    //Use of lateinit keyword
    //It is used when you have to declare variable first and initialize it later on
    //We cannot use lateinit keyword with primitive data types
    lateinit var age:String

}