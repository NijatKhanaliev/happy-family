package com.happyfamily.models;

import com.happyfamily.interfaces.Foulable;

public class RoboCat extends Pet implements Foulable {
    @Override
    public void respond() {
        System.out.println("Hello, owner. I am roboto cat. My name is" + super.getNickname() + ". I miss you!");
    }
}
