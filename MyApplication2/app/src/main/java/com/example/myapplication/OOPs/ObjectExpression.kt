package com.example.myapplication.OOPs

fun main(){
    //As we have to pass name,id and an object of OnClickListener Type
    //As we cannot create object of instance
    //So we have to use word object keyword to create instance of interface and implement it's func
    val click=Button("Login",123,object :OnClickListner{
        override fun onClick() {
            TODO("Not yet implemented")
        }

    })
}
class Button(var name:String,var id:Int,onClickListner:OnClickListner){

}
interface OnClickListner{
    fun onClick()
}