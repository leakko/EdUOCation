package edu.uoc.eduocation.model;

import java.time.LocalDate;
import java.util.LinkedList;

public abstract class Person extends NamedEntity {
    private String nif;
    private String surname;
    private LocalDate birthDate;

    public Person(String nif, String name, String surname, LocalDate birthday) {
        super(name);
        setNif(nif);
        setSurname(surname);
        setBirthDate(birthday);
    }

    public String getNif() {
        return nif;
    }

    public void setNif(String nif) {
        if(nif == null || nif.isEmpty()) {
            throw new IllegalArgumentException("Nif is null or empty");
        }
        this.nif = nif;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        if(surname == null || surname.isEmpty()) {
            throw new IllegalArgumentException("Surname is null or empty");
        }
        this.surname = surname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        if(nif == null) {
            throw new IllegalArgumentException("Birthdate is null");
        }
        this.birthDate = birthDate;
    }

    public static <T extends Person> T findByNif (LinkedList<T> persons, String nif) {
        return persons.stream()
                .filter(entity -> entity.getNif().equals(nif))
                .findFirst()
                .orElse(null);
    }
}
