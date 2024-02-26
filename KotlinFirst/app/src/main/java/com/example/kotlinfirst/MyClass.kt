package com.example.kotlinfirst

class MyClass {
    companion object{
        @JvmStatic
        fun main(args:Array<String>){
            /*
            Topics:
            1.First Code in Kotlin
            2.Variables and DataTypes (Difference of var and val)
            3.Declaration,Definition and calling a function

             */



           // println("Hello World")

          /* var a="Ali Haider"
            println("Hello $a")*/

           /* var a:Int
            a=90
            println("Hello $a")*/

           /* //Java has final key word but Kotlin has val
            val a="Ali"
            println("Hello $a, How are you") */
            println("The sum is ${add(7,7)}")

        }

        fun add(a:Int,b:Int):Int{
            return a+b
        }
    }
}