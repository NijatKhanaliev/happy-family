package com.happyfamily;


import com.happyfamily.enums.DayOfWeek;
import com.happyfamily.models.*;

import java.util.*;

public class Main {

    public static void main(String[] args) {

        Map<DayOfWeek, String> johnSchedule = new HashMap<>();
        johnSchedule.put(DayOfWeek.MONDAY, "go to university");
        johnSchedule.put(DayOfWeek.TUESDAY, "go to course");
        johnSchedule.put(DayOfWeek.THURSDAY, "go to shopping");
        johnSchedule.put(DayOfWeek.WEDNESDAY, "play domino");
        johnSchedule.put(DayOfWeek.FRIDAY, "go to university");
        johnSchedule.put(DayOfWeek.SATURDAY, "go to football");
        johnSchedule.put(DayOfWeek.SUNDAY, "rest");

        Set<String> dogHabits = new HashSet<>();
        dogHabits.add("eat");
        dogHabits.add("sleep");


        try {
            Human john = new Man("john", "abdullayev", 1998);
            john.setIq(82);
            john.setSchedule(johnSchedule);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        Human alisa = new Woman("alisa", "aydanov", 2000);
        alisa.setIq(74);
        alisa.setSchedule(johnSchedule);

        System.out.println(alisa);

        Human hasan = new Man("hasan", "abdullayev", 2021);
        hasan.setIq(64);
        hasan.setSchedule(johnSchedule);

        hasan.describePet();

        Human karim = new Man("karim", "abdullayev", 2019);
        karim.setIq(14);
        karim.setSchedule(johnSchedule);

        Human sabina = new Woman("sabina", "hasanova", 2021);
        sabina.setIq(72);
        sabina.setSchedule(johnSchedule);


        Family family = new Family(karim, sabina);
        Dog dog = new Dog();
        dog.setNickname("dog1");
        Set<Pet> pets = new HashSet<>();
        pets.add(dog);

        family.setPet(pets);
        sabina.describePet();


        family.printChildren();

        System.out.println("-----------------------------");

        Human child1 = family.bornChild();
        family.bornChild();
        family.bornChild();
        family.bornChild();

        family.printChildren();

        family.deleteChild(1);

        System.out.println("-----------------------------------");
        family.printChildren();

        family.deleteChild(child1);

        System.out.println("-------------------------");
        family.printChildren();

        try {
            Pet pet = new Fish();
            pet.setNickname("samir");
            pet.setHabits(dogHabits);
            pet.setTrickLevel(45); // throws exception trickLevel>100
            pet.respond();
            pet.eat();
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

    }
}