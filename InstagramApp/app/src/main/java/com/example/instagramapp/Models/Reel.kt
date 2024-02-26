package com.example.instagramapp.Models

class Reel {
    var videoUrl:String=""
    var caption:String=""
    var userName:String=""
    var userProfileLink:String?=null
    constructor(){}
    constructor(videoUrl: String, caption: String) {
        this.videoUrl = videoUrl
        this.caption = caption
    }

    constructor(videoUrl: String, caption: String, userName: String, userProfileLink: String?) {
        this.videoUrl = videoUrl
        this.caption = caption
        this.userName = userName
        this.userProfileLink = userProfileLink
    }
}