package com.happyfamily.models;

public final class Woman extends Human {

    public Woman() {
    }

    public Woman(String name, String surname, Integer dateOfBirth) {
        super(name, surname, dateOfBirth);
    }

    public void makeUp(){
        System.out.println("make up");
    }

    @Override
    public void greetPet() {
        System.out.println("Hello Woman, " + super.getFamily().getPet().getNickname());
    }

}
