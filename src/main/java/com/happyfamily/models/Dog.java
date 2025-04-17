package com.happyfamily.models;

import com.happyfamily.interfaces.Foulable;

public class Dog extends Pet implements Foulable {
    @Override
    public void respond() {
        System.out.println("Hello, owner. I am dog. My name is" + super.getNickname() + ". I miss you!");
    }
}
