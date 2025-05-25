package com.happyfamily;


import com.happyfamily.controller.FamilyController;
import com.happyfamily.dao.FamilyDao;
import com.happyfamily.dao.impl.CollectionFamilyDao;
import com.happyfamily.enums.DayOfWeek;
import com.happyfamily.models.*;
import com.happyfamily.service.FamilyService;
import com.happyfamily.service.impl.FamilyServiceImpl;

import java.time.LocalDate;
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

        Human man = new Man("samir","hasanov",1984);
        man.setIq(85);
        Human woman = new Woman("leman","cavadov",2000);
        woman.setIq(90);

        Human man1 = new Man("kamil","qasanov",1994);
        man1.setIq(95);
        Human woman1 = new Woman("samira","zeynalova",1998);
        woman1.setIq(75);

        Human human = new Man("rasul","ahmadov",2015);
        human.setIq(99);

        FamilyDao familyDao = new CollectionFamilyDao();
        FamilyService familyService = new FamilyServiceImpl(familyDao);
        FamilyController familyController = new FamilyController(familyService);

        Family family = familyController.createNewFamily(man,woman);
        Family family1 = familyController.createNewFamily(man1,woman1);

        System.out.println(familyController.getAllFamilies());

        Family family2 = familyController.bornChild(family,"senan","aydan");

        Family family3 = familyController.bornChild(family1,"hasan","nilay");

        familyController.adoptChild(family2,human);

        System.out.println(family2 +" ---------------------------------");

        System.out.println(familyController.getFamilyById(0));

        Dog dog = new Dog();
        dog.setHabits(dogHabits);

        familyController.addPet(0,dog);
        familyController.addPet(0,new Fish());
        familyController.addPet(1,new DomesticCat());

        System.out.println(familyController.getAllFamilies());

        System.out.println(familyController.count());

        System.out.println(familyController.getFamilyById(0).getChildren());

        familyController.deleteAllChildrenOlderThan(5);

        System.out.println(familyController.getFamilyById(0).getChildren());

        familyController.deleteFamilyByIndex(1);

        System.out.println(familyController.getAllFamilies());

        familyController.createNewFamily(man,woman);

        System.out.println(familyController.getAllFamilies());

        Man man2 = new Man();
        man2.setName("kazim");

        Woman woman2 = new Woman();
        woman2.setName("kamila");

        familyController.createNewFamily(man2,woman2);

        System.out.println("------------------------------------");
        System.out.println(familyController.getFamiliesBiggerThan(2) + " ------------bigger than");
        System.out.println(familyController.getFamiliesLessThan(3) + " --------------- less than");

    }
}