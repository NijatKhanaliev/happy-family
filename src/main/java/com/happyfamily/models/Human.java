package com.happyfamily.models;

import com.happyfamily.enums.DayOfWeek;

import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import java.util.Objects;
import java.util.Random;

public class Human {
    private String name;
    private String surname;
    private long birthDate;
    private Integer iq;
    private Map<DayOfWeek, String> schedule;
    private Family family;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    static {
        System.out.println("Class name: " + Human.class.getName());
    }

    {
        System.out.println("Object type: " + this.getClass().getTypeName());
    }

    public Human() {
        this.iq=80;
        this.name="unknown";
        this.surname="unknown";
    }

    public Human(String name, String surname, String birthDate) {
        this.name = name;
        this.surname = surname;
        this.iq = 80;
        LocalDate date = LocalDate.parse(birthDate, formatter);
        if (date.isBefore(LocalDate.of(1900, 1, 1)) || date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Year cannot be lower than 1900 or bigger than current year");
        }
        this.birthDate = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    public Human(String name, String surname, String birthDate, Integer iq, Family family, Map<DayOfWeek, String> schedule) {
        this.name = name;
        this.surname = surname;
        this.iq = iq;
        this.schedule = schedule;
        this.family = family;
        LocalDate date = LocalDate.parse(birthDate, formatter);
        if (date.isBefore(LocalDate.of(1900, 1, 1)) || date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Year cannot be lower than 1900 or bigger than current year");
        }
        this.birthDate = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
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

    public String getBirthDate() {
        LocalDate date = Instant.ofEpochMilli(birthDate).atZone(ZoneId.systemDefault()).toLocalDate();
        return date.format(formatter);
    }

    public void setBirthDate(String birthDate) {
        LocalDate date = LocalDate.parse(birthDate, formatter);
        if (date.isBefore(LocalDate.of(1900, 1, 1)) || date.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Year cannot be lower than 1900 or bigger than current year");
        }
        this.birthDate = date.atStartOfDay(ZoneId.systemDefault()).toInstant().toEpochMilli();
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

    public Map<DayOfWeek, String> getSchedule() {
        return schedule;
    }

    public void setSchedule(Map<DayOfWeek, String> schedule) {
        this.schedule = schedule;
    }

    public Family getFamily() {
        return family;
    }

    public int getAge() {
        return LocalDate.now().getYear() - Instant.ofEpochMilli(birthDate)
                .atZone(ZoneId.systemDefault())
                .toLocalDate().getYear();
    }

    public void setFamily(Family family) {
        this.family = family;
    }

    public String describeAge() {
        LocalDate birth = Instant.ofEpochMilli(birthDate)
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
        Period age = Period.between(birth, LocalDate.now());
        return String.format("%d years, %d months, %d days",
                age.getYears(), age.getMonths(), age.getDays());
    }

    public void greetPet() {
        family.getPet().forEach((p) -> System.out.println("Hello " + p.getNickname()));
    }

    public void printSchedule() {
        System.out.println(schedule);
    }

    public String fullName() {
        return this.name + " " + this.surname;
    }

    public void describePet() {
        if (this.family == null) {
            System.out.println("You do not have family");
            return;
        } else if (family.getPet() == null) {
            System.out.println("You do not have a pet");
            return;
        }

        String result = "sly";

        if (family.getPet().stream().findAny().get().getAge() == null) {
            System.out.println("pet age is null");
            return;
        }

        if (family.getPet().stream().anyMatch((p) -> p.getTrickLevel() > 50)) {
            result = "very sly";
        } else if (family.getPet().stream().anyMatch((p) -> p.getTrickLevel() < 50)) {
            result = "almost not sly";
        }

        System.out.println("I have an " + family.getPet().stream().findAny().get().getSpecies() + " is " + family.getPet().stream().findAny().get().getAge() +
                " years old, he is " + result);
    }

    public boolean feedPet(boolean isTimeToFeed) {
        if (isTimeToFeed) {
            System.out.println("Hm... I will feed " + name + "'s " + family.getPet().stream().findAny().get().getNickname());
            return true;
        } else {
            Random random = new Random();
            int randomNumber = random.nextInt(101);
            if (randomNumber < family.getPet().stream().findAny().get().getTrickLevel()) {
                System.out.println("Hm... I will feed " + family.getPet().stream().findAny().get().getNickname());
                return true;
            } else {
                System.out.println("I think " + family.getPet().stream().findAny().get() + " is not hungry.");
                return false;
            }
        }
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", birth date=" + Instant.ofEpochMilli(birthDate)
                .atZone(ZoneId.systemDefault())
                .toLocalDate()
                .format(formatter) +
                ", iq=" + iq +
                ", schedule=" + schedule +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Human human = (Human) o;
        return Objects.equals(name, human.name) && Objects.equals(surname, human.surname)
                && Objects.equals(birthDate, human.birthDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, birthDate);
    }
}
