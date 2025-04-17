package com.happyfamily;


import com.happyfamily.enums.DayOfWeek;
import com.happyfamily.enums.Genders;
import com.happyfamily.models.*;

public class Main {

    public static void main(String[] args) {
        String[][] johnSchedule = {
                {DayOfWeek.MONDAY.name(), "go to university"},
                {DayOfWeek.TUESDAY.name(), "go to course"},
                {DayOfWeek.THURSDAY.name(), "go to shopping"},
                {DayOfWeek.WEDNESDAY.name(), "play domino"},
                {DayOfWeek.FRIDAY.name(), "go to university"},
                {DayOfWeek.SATURDAY.name(), "go to football"},
                {DayOfWeek.SUNDAY.name(), "rest"}
        };

        String[] dogHabits = {"eating", "sleeping"};


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

        Human hasan = new Man("hasan", "abdullayev", 2021);
        hasan.setIq(64);
        hasan.setSchedule(johnSchedule);

        Human karim = new Man("karim", "abdullayev", 2019);
        karim.setIq(14);
        karim.setSchedule(johnSchedule);

        Human sabina = new Woman("sabina", "hasanova", 2021);
        sabina.setIq(72);
        sabina.setSchedule(johnSchedule);


        Family family = new Family(karim,sabina);

        Human child1 = sabina.bornChild(Genders.MALE);
        Human child2 = sabina.bornChild(Genders.FEMALE);
        Human child3 = sabina.bornChild(Genders.MALE);

        family.printChildren();

        family.deleteChild(0);
        family.deleteChild(child2);

        System.out.println("-----------------------------------");
        family.printChildren();

        System.out.println("-----------------------------------");

        try {
            Human human = hasan.bornChild(Genders.FEMALE);
            System.out.println(human.getName());
            family.addChild(human);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("-----------------------------------");

        System.out.println("-----------------------------------");
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