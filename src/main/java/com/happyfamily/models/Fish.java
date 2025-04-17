package com.happyfamily.models;

public class Fish extends Pet{
    @Override
    public void respond() {
        System.out.println("Hello, owner. I am fish. My name is" + super.getNickname() + ". I miss you!");
    }
}
