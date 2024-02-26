package com.example.myapplication.OOPs

fun main(){
var listview=ListView(arrayOf("Ali","Haider","Asad"))
    listview.ListViewItems().displayItems(2)
}
/*
 an inner class is a class that is nested inside another class.
  Unlike a nested class (which is static by default),
  an inner class is associated with an instance of the outer class.
   This means that an inner class can access the members
    (including properties and methods) of the outer class.
 */
class ListView(var items:Array<String>){
    //When two class are attached to each other than instead of creating separated classed
    //We use inner class
    inner class ListViewItems{
        fun displayItems(pos:Int){
            //In inner class we can access the properties of outer class
            println(items[pos])
        }
    }
}