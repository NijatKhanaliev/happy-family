package com.happyfamily.service;

import com.happyfamily.models.Family;
import com.happyfamily.models.Human;
import com.happyfamily.models.Pet;

import java.util.List;
import java.util.Set;

public interface FamilyService {
    List<Family> getAllFamilies();
    void displayAllFamilies();
    List<Family> getFamiliesBiggerThan(int peopleCount);
    List<Family> getFamiliesLessThan(int peopleCount);
    int countFamiliesWithMemberNumber(int count);
    Family createNewFamily(Human man,Human woman);
    boolean deleteFamilyByIndex(int index);
    Family bornChild(Family family,String masculineName,String feminineName);
    Family adoptChild(Family family,Human human);
    void deleteAllChildrenOlderThan(int age);
    int count();
    Family getFamilyById(int index);
    Set<Pet> getPets(int familyIndex);
    void addPet(int familyIndex,Pet pet);

}
