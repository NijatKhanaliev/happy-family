package com.happyfamily.models;

import java.util.Arrays;
import java.util.Objects;

public class Family{
    private final Human father;
    private final Human mother;
    private Human[] children = new Human[0];
    private Pet pet;

    static {
        System.out.println("Class name: " + Family.class.getName());
    }

    {
        System.out.println("Object type: " + this.getClass().getTypeName());
    }

    public Family(Human father, Human mother) {
        this.father = father;
        this.mother = mother;

        father.setFamily(this);
        mother.setFamily(this);
        mother.setSurname(father.getSurname());
    }

    public Family(Human father, Human mother, Pet pet) {
        this.father = father;
        this.mother = mother;
        this.pet = pet;

        father.setFamily(this);
        mother.setFamily(this);
        mother.setSurname(father.getSurname());
    }

    public Human getFather() {
        return father;
    }

    public void setFather(Human father) {
        new Family(father, mother, pet);
    }

    public Human getMother() {
        return mother;
    }

    public void setMother(Human mother) {
        new Family(father, mother, pet);
    }

    public Human[] getChildren() {
        return children;
    }

    public void setChildren(Human[] children) {
        this.children = children;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public void addChild(Human child) {
        Human[] newChildren = new Human[children.length + 1];

        for (int i = 0; i < children.length; i++) {
            newChildren[i] = children[i];
        }

        newChildren[children.length] = child;

        children = newChildren;

        child.setFamily(this);
    }

    public boolean deleteChild(int index) {
        if (index >= children.length) {
            System.out.println("Child is not exists with index: " + index);
            return false;
        }

        children[index].setFamily(null);

        Human[] newChildren = new Human[children.length - 1];
        boolean isFound = false;
        for (int i = 0; i < children.length; i++) {
            if (i == index) {
                isFound = true;
            } else {
                if (isFound) {
                    newChildren[i - 1] = children[i];
                } else {
                    newChildren[i] = children[i];
                }
            }

        }

        children = newChildren;
        return true;
    }

    public boolean deleteChild(Human child) {
        int index = children.length;

        for (int i = 0; i < children.length; i++) {
            if (children[i].equals(child) && children[i].hashCode() == child.hashCode()) {
                index = i;
                break;
            }
        }

        if (index >= children.length) {
            System.out.println("Child is not exists with name : " + child.getName() + " surname: " + child.getSurname() + " dateOfBirth: " + child.getDateOfBirth());
            return false;
        }

        children[index].setFamily(null);
        Human[] newChildren = new Human[children.length - 1];
        boolean isFound = false;
        for (int i = 0; i < children.length; i++) {
            if (i == index) {
                isFound = true;
            } else {
                if (isFound) {
                    newChildren[i - 1] = children[i];
                } else {
                    newChildren[i] = children[i];
                }
            }

        }

        children = newChildren;
        return true;
    }

    public void printChildren() {
        for (Human arr : children) {
            System.out.println(arr.fullName());
        }
    }

    public int countOfMember() {
        return children.length + 3;
    }

    public boolean isChildExistsByName(String name){
        for(Human arr : children){
            if(arr.getName().equalsIgnoreCase(name)){
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        String childrenResult = "0";

        if (children.length != 0) {
            childrenResult = Arrays.toString(children);
        }
        return "Family{" +
                "father=" + father.fullName() +
                ", mother=" + mother.fullName() +
                ", children=" + childrenResult +
                ", pet=" + pet.toString() +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(father, family.father) && Objects.equals(mother, family.mother) && Objects.equals(pet, family.pet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(father, mother, pet);
    }

}
