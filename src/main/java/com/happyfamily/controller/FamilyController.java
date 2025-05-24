package com.happyfamily.controller;

import com.happyfamily.models.Family;
import com.happyfamily.models.Human;
import com.happyfamily.models.Pet;
import com.happyfamily.service.FamilyService;

import java.util.List;
import java.util.Set;

public class FamilyController {
    private final FamilyService familyService;

    public FamilyController(FamilyService familyService){
        this.familyService = familyService;
    }


    public List<Family> getAllFamilies() {
        try {
            return familyService.getAllFamilies();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public void displayAllFamilies() {
        try {
            familyService.displayAllFamilies();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }


    public List<Family> getFamiliesBiggerThan(int peopleCount) {
        try {
            return familyService.getFamiliesBiggerThan(peopleCount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public List<Family> getFamiliesLessThan(int peopleCount) {
        try {
            return familyService.getFamiliesLessThan(peopleCount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Integer countFamiliesWithMemberNumber(int memberCount) {
        try {
            return familyService.countFamiliesWithMemberNumber(memberCount);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public Family createNewFamily(Human man, Human woman) {
        try {
            return familyService.createNewFamily(man,woman);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public boolean deleteFamilyByIndex(int index) {
        try {
            return familyService.deleteFamilyByIndex(index);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public Family bornChild(Family family, String masculineName, String feminineName) {
        try {
            return familyService.bornChild(family,masculineName,feminineName);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public Family adoptChild(Family family, Human human) {
        try {
            return familyService.adoptChild(family,human);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void deleteAllChildrenOlderThan(int age) {
        try {
            familyService.deleteAllChildrenOlderThan(age);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Integer count() {
        try {
            return familyService.count();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public Family getFamilyById(int index) {
        try {
            return familyService.getFamilyById(index);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }


    public Set<Pet> getPets(int familyIndex) {
        try {
            return familyService.getPets(familyIndex);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void addPet(int familyIndex, Pet pet) {
        try {
            familyService.addPet(familyIndex,pet);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
