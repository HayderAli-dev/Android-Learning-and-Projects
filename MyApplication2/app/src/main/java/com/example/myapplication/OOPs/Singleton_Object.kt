

fun main(){
val instance=Database.getInstance()
    println(instance)
    //Now if we create another instance it will not create according to our given logic
    //It will return the same value again
    val instance2=Database.getInstance()
    println(instance2)
    println(DBMS)
    println(DBMS)
}
// --> By declaring constructor as private we cannot create object of this class
// --> So this class methods or properties can have only one initialization
class Database private constructor(){
    //The code in companion object can be accessed directly without object
    companion object{
        private var instance:Database?=null
        fun getInstance():Database?{
            if (instance==null){
                instance=Database()
            }
            return instance
        }
    }
}
// In case we have to create only one instance of a class without creating object
// we can use object keyword in place of class
// So this object can have only one instance
//This is too simple and easy for code that should have only one time execution
object DBMS{
 init {
     println("DataBase Created ${DBMS}")
 }
}