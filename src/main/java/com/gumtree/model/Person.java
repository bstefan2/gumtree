package com.gumtree.model;

import java.time.LocalDate;

public class Person {
    /** Genre */
    public enum Sex {
        Male,
        Female
    }

    /** person's name */
    private String name;

    /** person's genre */
    private Sex sex;

    /** birth date */
    private LocalDate birthDate;

    public Person(String name, Sex sex, LocalDate birthDate) {
        this.name = name;
        this.sex = sex;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public Sex getSex() {
        return sex;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }
}