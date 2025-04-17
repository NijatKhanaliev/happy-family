package com.happyfamily.models;

import com.happyfamily.enums.Genders;
import com.happyfamily.interfaces.HumanCreator;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Objects;
import java.util.Random;

public class Human implements HumanCreator {
    private String name;
    private String surname;
    private Integer dateOfBirth;
    private Integer iq;
    private String[][] schedule;
    private Family family;

    private final String[] nameList = {"Amal","Samir","Kamil","Qabil","Rasul","Elcan"};

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
        String result = "sly";

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
    public Human bornChild(Genders type) {
        if(this.getFamily()==null || this instanceof Man){
            throw new IllegalArgumentException("You cannot born...");
        }

        if (this instanceof Woman){
            int iq = (this.getFamily().getFather().getIq() + this.getFamily().getMother().getIq()) / 2;
            Random random = new Random();
            int randomIndex = random.nextInt(nameList.length);

            while(this.family.isChildExistsByName(nameList[randomIndex])){
                randomIndex = random.nextInt(nameList.length);
            }

            if (type.name().equalsIgnoreCase("MAN")) {
                Man human = new Man();
                human.setDateOfBirth(LocalDate.now().getYear());
                human.setIq(iq);
                human.setSurname(this.family.getFather().getSurname());
                System.out.println("samlamamkaksdk" + nameList[randomIndex]);
                human.setName(nameList[randomIndex]);

                this.family.addChild(human);

                return human;
            } else {
                Woman human = new Woman();
                human.setDateOfBirth(LocalDate.now().getYear());
                human.setIq(iq);
                human.setSurname(this.family.getFather().getSurname());
                human.setName(nameList[randomIndex]);

                this.family.addChild(human);

                return human;
            }
        }

        return null;
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
