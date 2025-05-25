package com.happyfamily.service.impl;

import com.happyfamily.dao.FamilyDao;
import com.happyfamily.exceptions.InvalidCreateFamilyRequest;
import com.happyfamily.models.*;
import com.happyfamily.service.FamilyService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class FamilyServiceImpl implements FamilyService {
    private final FamilyDao familyDao;

    public FamilyServiceImpl(FamilyDao familyDao){
        this.familyDao = familyDao;
    }

    @Override
    public List<Family> getAllFamilies() {
        return familyDao.getAllFamilies();
    }

    @Override
    public void displayAllFamilies() {
        List<Family> allFamily = familyDao.getAllFamilies();
        for (Family family : allFamily) {
            System.out.println(family);
        }
    }

    @Override
    public List<Family> getFamiliesBiggerThan(int peopleCount) {
        List<Family> allFamilies = familyDao.getAllFamilies();
        List<Family> newFamilyList = new ArrayList<>();
        for (Family family : allFamilies) {
            if (family.countOfPeople() > peopleCount) {
                newFamilyList.add(family);
            }
        }

        for (Family family : newFamilyList) {
            System.out.println(family);
        }

        return newFamilyList;
    }

    @Override
    public List<Family> getFamiliesLessThan(int peopleCount) {
        List<Family> allFamilies = familyDao.getAllFamilies();
        List<Family> newFamilyList = new ArrayList<>();
        for (Family family : allFamilies) {
            if (family.countOfPeople() < peopleCount) {
                newFamilyList.add(family);
            }
        }

        return newFamilyList;
    }

    @Override
    public int countFamiliesWithMemberNumber(int memberCount) {
        List<Family> allFamilies = familyDao.getAllFamilies();
        int count = 0;
        for(Family family : allFamilies){
            if(family.countOfPeople()==memberCount){
              count++;
            }
        }

        return count;
    }

    @Override
    public Family createNewFamily(Human man, Human woman) {
        Family family = new Family(man,woman);
        if(!familyDao.getAllFamilies().contains(family)){
            familyDao.saveFamily(family);

            return family;
        }

        throw new InvalidCreateFamilyRequest("You are already married");
    }

    @Override
    public boolean deleteFamilyByIndex(int index) {
        return familyDao.deleteFamily(index);
    }

    @Override
    public Family bornChild(Family family, String masculineName, String feminineName) {
        Random random = new Random();
        boolean isBoy = random.nextBoolean();
        int iq = (family.getFather().getIq() + family.getMother().getIq()) / 2;

        Human human;
        if(isBoy){
            human = new Man();
            human.setName(masculineName);
        }else{
            human = new Woman();
            human.setName(feminineName);
        }
        human.setFamily(family);
        human.setIq(iq);
        human.setSurname(family.getFather().getSurname());
        human.setDateOfBirth(LocalDate.now().getYear());

        family.addChild(human);
        familyDao.saveFamily(family);

        return family;
    }

    @Override
    public Family adoptChild(Family family, Human human) {
        human.setFamily(family);
        family.addChild(human);

        familyDao.saveFamily(family);

        return family;
    }

    @Override
    public void deleteAllChildrenOlderThan(int age) {
        List<Family> allFamilies = familyDao.getAllFamilies();

        for(Family family : allFamilies){
            List<Human> children = family.getChildren();
            children.stream()
                    .filter((c)->c.getAge()>age)
                    .forEach(family::deleteChild);

            familyDao.saveFamily(family);
        }

    }

    @Override
    public int count() {
        return familyDao.getAllFamilies().size();
    }

    @Override
    public Family getFamilyById(int index) {
        return familyDao.getFamilyByIndex(index);
    }

    @Override
    public Set<Pet> getPets(int familyIndex) {
        return familyDao.getFamilyByIndex(familyIndex).getPet();
    }

    @Override
    public void addPet(int familyIndex, Pet pet) {
        familyDao.getFamilyByIndex(familyIndex).addPet(pet);
    }
}
