package com.happyfamily.models;

import java.util.*;

public class Family{
    private final Human father;
    private final Human mother;
    private List<Human> children = new ArrayList<>();
    private Set<Pet> pet = new HashSet<>();

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

    public Family(Human father, Human mother, Set<Pet> pet) {
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

    public List<Human> getChildren() {
        return children;
    }

    public void setChildren(List<Human> children) {
        this.children = children;
    }

    public Set<Pet> getPet() {
        return pet;
    }

    public void setPet(Set<Pet> pet) {
        this.pet = pet;
    }

    public void addChild(Human child) {
        if (isChildExists(child)) {
            System.out.println("child already exists.");
            return;
        }
        children.add(child);
    }

    public boolean deleteChild(int index) {
        if (index >= children.size()) {
            System.out.println("Child is not exists with index: " + index);
            return false;
        }

        children.get(index).setFamily(null);
        children.remove(index);

        return true;
    }

    public void deleteChild(Human child) {
        System.out.println(child.getName());
        child.setFamily(null);
        children.remove(child);
    }

    public void printChildren() {
        System.out.println(children);
    }

    public int countOfMember() {
        if (pet == null) {
            return children.size() + 2;
        } else {
            return children.size() + 2 + pet.size();
        }
    }

    public int countOfPeople(){
        return children.size() + 2;
    }

    public boolean isChildExistsByName(String name) {
        if(children.isEmpty()){
            return false;
        }

        if (children.stream().anyMatch((c) -> c.getName().equals(name))) {
            return true;
        }

        return false;
    }

    public boolean isChildExists(Human human) {
        if(children.isEmpty()){
            return false;
        }

        if (children.contains(human)) {
            return true;
        }

        return false;
    }

    public void addPet(Pet pet) {
        if (this.pet.contains(pet)) {
            System.out.println("Pet already exists");
            return;
        }

        this.pet.add(pet);
    }

    @Override
    public String toString() {
        return "Family{" +
                "father=" + father.fullName() +
                ", mother=" + mother.fullName() +
                ", children=" + children +
                ", pet=" + (pet!=null ? pet.toString() : "none")  +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Family family = (Family) o;
        return Objects.equals(father, family.father) && Objects.equals(mother, family.mother);
    }

    @Override
    public int hashCode() {
        return Objects.hash(father, mother);
    }

}
