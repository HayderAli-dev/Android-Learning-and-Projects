package com.example.recyclerview;

public class contact_Model {
    int img;
    String name;
    String number;

    public contact_Model(int img,String name,String number){
        this.img=img;
        this.name=name;
        this.number=number;

    }
    public contact_Model(String name,String number) {
        this.name = name;
        this.number = number;
    }
}
