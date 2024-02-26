
fun main(){
    val max=Calculator.max
    val res= Calculator.sum(2,3)
    println(res)
    println(max)
}
class Calculator(){
    /*
    a companion object is a special type of object that is tied to a class
     rather than to instances of that class.
     It's similar to static members in Java
      but allows you to define methods and properties on the companion object itself.
     */
    companion object ali{
        //Now we will be able to call this property and method without creating objects
        var max=Int.MAX_VALUE
        fun sum(a:Int,b:Int):Int{
            return a+b
        }
    }
    /*
    They are useful for defining factory methods, constants,
    or any methods that should be tied to the class itself rather than to instances of the class.
     */

}