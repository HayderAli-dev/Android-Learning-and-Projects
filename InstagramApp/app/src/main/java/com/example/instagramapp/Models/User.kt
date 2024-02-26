package com.example.instagramapp.Models

class User {
 var image:String?=null
 var name:String?=null
    var email:String?=null
    var passwords:String?=null
    constructor()
    constructor(image: String?, name: String?, email: String?, passwords: String?) {
        this.image = image
        this.name = name
        this.email = email
        this.passwords = passwords
    }

    constructor(name: String?, email: String?, passwords: String?) {
        this.name = name
        this.email = email
        this.passwords = passwords
    }

    constructor(email: String?, passwords: String?) {
        this.email = email
        this.passwords = passwords
    }


}