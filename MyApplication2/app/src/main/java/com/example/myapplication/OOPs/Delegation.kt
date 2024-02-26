package com.example.myapplication.OOPs

fun main(){

}
//As we know we cannot inherit two classes in derived class at one time
//We can inherit only one class
//But we can inherit two interfaces in one class
//But we can also inherit more classes by using delegations
//Now this class can use implementations of functions of interfaces defined in classes
class Mains:A by FirstDelegation(),B by SecondDelegation(){
    override fun print() {
        TODO("Not yet implemented")
    }

    override fun print2() {
        TODO("Not yet implemented")
    }
}
interface A{
    fun print()
}
interface B{
    fun print2()
}
class FirstDelegation:A{
    override fun print() {

    }

}
class SecondDelegation:B{
    override fun print2() {

    }

}