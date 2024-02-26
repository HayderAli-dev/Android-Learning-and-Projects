package com.example.myapplication.OOPs

/*

 */
fun main(){
val user1=User2("Haider","Ali",23)
    val user2=User2("Haider","Ali",23)
    println(user1==user2)
    println(user1===user2)
    //Referential Equality
    println(user1.equals(user2))
    println(user1.hashCode())
    println(user1)
}
//Every class in Kotlin is inherited from Any Class
//Any Class have some functions and String class has those functions implementations on it
//String class have their own implementation so we use == as structural equality
//The equals method in any use referential equality only
//So we have to give our own implementation in it to have our users structurally equal
class User1(var firstName:String,var lastName:String,var age:Int):Any(){
    override fun equals(other: Any?): Boolean {
     if (this===other){
         return true
     }
        if (other is User1){
            return this.firstName==other.firstName && this.lastName==other.lastName
                    && this.age==other.age
        }
        return false
    }
    //The rule is when you override equal you have to override hashcode
    override fun hashCode(): Int {
        //This is particularly used inside collections
        return 0
    }

    override fun toString(): String {
        return "User : $firstName , $lastName , $age"
    }
}
//You dont have to write all of this code in Kotlin
//You have to just type
data class User2(var firstName: String,var lastName: String,var age: Int)