package com.happyfamily.models;

import com.happyfamily.interfaces.Foulable;

public class DomesticCat extends Pet implements Foulable {
    @Override
    public void respond() {
        System.out.println("Hello, owner. I am domestic cat. My name is" + super.getNickname() + ". I miss you!");

    }
}
