package com.example.myapplication.OOPs

fun main(){
    println(Directions.EAST)
    println(Directions.WEST)
    println(Directions.NORTH.name)
    println(Directions.SOUTH.distance)
    Directions.EAST.printData()
    Directions.WEST.printData()

    val selectedColor = Color.valueOf("green".uppercase())

    when (selectedColor) {
        Color.RED -> println("Selected color is red")
        Color.GREEN -> println("Selected color is green")
        Color.BLUE -> println("Selected color is blue")
    }
}
/*
 An enum class is a special type of class that represents a group of constants (enum constants).
 Each enum constant is an object of the enum class and has a name and an optional set of properties.
 */
enum class Directions(var distance:Int){
    //Each Constant in enum class is an object of that class
    //SO we also have to pass constant values in that constants as
    NORTH(10),
    SOUTH(20),
    EAST(30),
    WEST(40);
    //We can also have functions in enum class
    fun printData(){
        println("The direction is $name distance is $distance")
    }

}
enum class Color {
    RED, GREEN, BLUE
}
