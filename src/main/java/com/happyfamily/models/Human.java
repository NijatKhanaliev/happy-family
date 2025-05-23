package com.happyfamily.models;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Human {
    private String name;
    private String surname;
    private Integer dateOfBirth;
    private Integer iq;
    private String[][] schedule;
    private Family family;

    static {
        System.out.println("Class name: " + Human.class.getName());
    }

    {
        System.out.println("Object type: " + this.getClass().getTypeName());
    }

    public Human() {

    }

    public Human(String name, String surname, Integer dateOfBirth) {
        if (dateOfBirth < 1900 || dateOfBirth > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Year cannot be lower than 1900 or bigger than current year");
        }

        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
    }


    public Human(String name, String surname, Integer dateOfBirth, Integer iq, Family family, String[][] schedule) {
        if (dateOfBirth < 1900 || dateOfBirth > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Year cannot be lower than 1900 or bigger than current year");
        }

        this.name = name;
        this.surname = surname;
        this.dateOfBirth = dateOfBirth;
        this.iq = iq;
        this.schedule = schedule;
        this.family = family;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Integer getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Integer dateOfBirth) {
        if (dateOfBirth < 1900 || dateOfBirth > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Year cannot be lower than 1900 or bigger than current year");
        }

        this.dateOfBirth = dateOfBirth;
    }

    public Integer getIq() {
        return iq;
    }

    public void setIq(Integer iq) {
        if (iq < 1 || iq > 100) {
            throw new IllegalArgumentException("IQ cannot be lower than 0 or bigger than 100");
        }

        this.iq = iq;
    }

    public String[][] getSchedule() {
        return schedule;
    }

    public void setSchedule(String[][] schedule) {
        this.schedule = schedule;
    }

    public Family getFamily() {
        return family;
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public int getAge() {
        return LocalDate.now().getYear() - dateOfBirth;
    }

    public void greetPet() {
        System.out.println("Hello, " + family.getPet().getNickname());
    }

    public void printSchedule() {
        for (String[] arr : schedule) {
            for (String str : arr) {
                System.out.print(str + " ");
            }
            System.out.println();
        }
    }

    public String fullName() {
        return this.name + " " + this.surname;
    }

    public void describePet() {
        if(this.family==null){
            System.out.println("You do not have family");
            return;
        }else if(family.getPet()==null){
            System.out.println("You do not have a pet");
            return;
        }

        String result = "sly";

        if(family.getPet().getAge()==null){
            System.out.println("pet age is null");
            return;
        }

        if (family.getPet().getAge() > 50) {
            result = "very sly";
        } else if (family.getPet().getAge() < 50) {
            result = "almost not sly";
        }

        System.out.println("I have an " + family.getPet().getSpecies() + " is " + family.getPet().getAge() +
                " years old, he is " + result);
    }

    public boolean feedPet(boolean isTimeToFeed) {
        if (isTimeToFeed) {
            System.out.println("Hm... I will feed " + name + "'s " + family.getPet().getNickname());
            return true;
        } else {
            Random random = new Random();
            int randomNumber = random.nextInt(101);
            if (randomNumber < family.getPet().getTrickLevel()) {
                System.out.println("Hm... I will feed " + family.getPet().getNickname());
                return true;
            } else {
                System.out.println("I think " + family.getPet().getNickname() + " is not hungry.");
                return false;
            }
        }
    }

    @Override
    public String toString() {
        String result = "No Schedule";

        if (schedule.length != 0) {
            StringBuilder scheduleResult = new StringBuilder("[");
            for (String[] arr : schedule) {
                scheduleResult.append(Arrays.toString(arr));
            }
            scheduleResult.append("]");

            result = scheduleResult.toString();
        }


        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", year=" + dateOfBirth +
                ", iq=" + iq +
                ", schedule=" + result +
                '}';
    }


@Override
public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Human human = (Human) o;
    return Objects.equals(name, human.name) && Objects.equals(surname, human.surname)
            && Objects.equals(dateOfBirth, human.dateOfBirth);
}

@Override
public int hashCode() {
    return Objects.hash(name, surname, dateOfBirth);
}
}
