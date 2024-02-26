package com.example.chatsapp.Models;

public class UserModel_DB {
    public String uniqueId,name,phoneNumber,profileImage,token;

    public UserModel_DB() {
        //Empty Constructor is always needed while working with firebase
    }

    public UserModel_DB(String uniqueId, String name, String phoneNumber, String profileImage) {
        this.uniqueId = uniqueId;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.profileImage = profileImage;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
