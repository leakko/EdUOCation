package edu.uoc.eduocation.model;

import java.util.LinkedList;

public class Location {
    private String address;
    private String city;
    private String country;
    private String phoneNumber;
    private School school;
    private LinkedList<Exam> exams;

    public Location(String address, String city, String country, String phoneNumber) {
        setAddress(address);
        setCity(city);
        setCountry(country);
        setPhoneNumber(phoneNumber);
        this.exams = new LinkedList<>();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) throws IllegalArgumentException {
        if (address == null || address.isEmpty() || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or empty");
        }
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) throws IllegalArgumentException {
        if (city == null || city.isEmpty() || city.isBlank()) {
            throw new IllegalArgumentException("City cannot be null or empty");
        }
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) throws IllegalArgumentException {
        if (country == null || country.isEmpty() || country.isBlank()) {
            throw new IllegalArgumentException("Country cannot be null or empty");
        }
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) throws IllegalArgumentException {
        if (phoneNumber == null || phoneNumber.length() != 9) {
            throw new IllegalArgumentException("Phone number must have 9 digits");
        }
        this.phoneNumber = phoneNumber;
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        School lastSchool;
        lastSchool = getSchool();
        if(school != null && !school.getLocations().contains(this)) {
            school.addLocation(this);
        }
        if(lastSchool != null){
            lastSchool.removeLocation(this);
        }
        this.school = school;
    }

    public LinkedList<Exam> getExams() {
        return exams;
    }

    public void addExam(Exam exam) {
        this.exams.add(exam);
        if(exam.getLocation() != this) {
            exam.setLocation(this);
        }
    }

    public void removeExam(Exam exam, Location newLocation) {
        exam.setLocation(newLocation);
        this.exams.remove(exam);
    }

    public static Location getByAddress(LinkedList<Location> locations, String address) {
        return locations.stream()
                .filter(entity -> entity.getAddress().equals(address))
                .findFirst()
                .orElse(null);
    }
}
