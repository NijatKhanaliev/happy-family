package com.happyfamily.models;

import com.happyfamily.enums.Species;

import java.util.Arrays;
import java.util.Objects;

public abstract class Pet {
    private Species species;
    private String nickname;
    private Integer age;
    private Integer trickLevel;
    private String[] habits;

    static {
        System.out.println("Class name: " + Pet.class.getName());
    }

    {
        System.out.println("Object type: " + this.getClass().getTypeName());
    }

    public Pet() {
        this.species = Species.UNKNOWN;
    }

    public Pet(String nickname) {
        this.species = Species.UNKNOWN;
        this.nickname = nickname;
    }

    public Pet(String nickname, Integer age, Integer trickLevel, String[] habits) {
        if (trickLevel < 1 || trickLevel > 100) {
            throw new IllegalArgumentException("Trick level cannot be lower than 0 or bigger than 100");
        }

        this.species = Species.UNKNOWN;
        this.nickname = nickname;
        this.age = age;
        this.trickLevel = trickLevel;
        this.habits = habits;
    }

    public String getSpecies() {
        return species.name();
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        if (age < 0) {
            throw new IllegalArgumentException("Pet age cannot be lower than 0");
        }

        this.age = age;
    }

    public Integer getTrickLevel() {
        return trickLevel;
    }

    public void setTrickLevel(Integer trickLevel) {
        if (trickLevel < 1 || trickLevel > 100) {
            throw new IllegalArgumentException("Trick level cannot be lower than 0 or bigger than 100");
        }

        this.trickLevel = trickLevel;
    }

    public void printHabits(){
        System.out.println(Arrays.toString(habits));
    }

    public String[] getHabits() {
        return habits;
    }

    public void setHabits(String[] habits) {
        this.habits = habits;
    }

    public void eat() {
        System.out.println("I am eating");
    }

    public abstract void respond();


    @Override
    public String toString() {
        return species.name() + "{" +
                ", nickname='" + nickname + '\'' +
                ", age=" + age +
                ", trickLevel=" + trickLevel +
                ", habits=" + Arrays.toString(habits) +
                ", canFly=" + species.isCanFly() +
                ", numberOfLegs=" + species.getNumberOfLegs() +
                ", hasFur=" + species.isHasFur() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return Objects.equals(species, pet.species) && Objects.equals(nickname, pet.nickname) && Objects.equals(trickLevel, pet.trickLevel);
    }

    @Override
    public int hashCode() {
        return Objects.hash(species, nickname, trickLevel);
    }
}
