package com.example.kotlinfirst;

public class ConditionalAndLoops {
    companion object

    {
        @JvmStatic
        fun main (args:Array<String>){
            /*
            Topics:
            1.Conditional Statements(If-else and When)
            2.for and for-each loop
            3.While and Do While Loop
             */
/*
            var a=11
            var msg=""
            msg=if (a>12)"No. is greater" else "No.is smaller"
            println(msg)
            if (a>12)
                println("No is greater than 12")
            else if (a<12)
                println("No is less than 12")

            when(a){
                1-> println("No is 1")
                12-> println("No is 11")
            }
            when{
                a>12-> println("No is greater than 12")
                else-> println("No is less than 12")
            }
            */

            // will go from 0 to 10
            /*
            for (i in 0..10){
                println(i)
            }
            */

            //will go from 0 to 9
            /*
            for (i in 0 until 10){
                println(i)
            } */

            // will increment more than 1
            /*
            for (i in 0..10 step 5){
                println(i)
            }
            */

            //will go reverse
            /*
            for (i in 10 downTo 0){
                println(i)
            }
             */
var num=10
            /*
            while (num<=16){
                println(num)
                num++
            }
             */
            do {
                println(num)
            } while (num<9)

        }
    }
}
