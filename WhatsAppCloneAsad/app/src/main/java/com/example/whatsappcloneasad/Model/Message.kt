package com.example.whatsappcloneasad.Model

data class Message(var message:String="",var senderId:String="",var timestamp:Long =0) {

    var messageId:String=""

    var feeling:Int=0
}