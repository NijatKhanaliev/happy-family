package com.happyfamily.models;

public final class Man extends Human{

    public Man() {
    }

    public Man(String name, String surname, String dateOfBirth) {
        super(name, surname, dateOfBirth);
    }

    public void repairCar(){
        System.out.println("repair car");
    }

    @Override
    public void greetPet() {
        System.out.println("Hello Man, " + super.getFamily().getPet().stream().findAny().get().getNickname());
    }

}
