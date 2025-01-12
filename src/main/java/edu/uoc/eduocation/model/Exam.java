package edu.uoc.eduocation.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Exam {
    private LocalDate date;
    private LocalTime time;
    private String room;
    private Location location;
    final private Subject subject;

    public Exam(Subject subject) {
        this.subject = subject;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        Location lastLocation = getLocation();
        if(lastLocation != null) {
            lastLocation.removeExam(this, location);
        }
        this.location = location;
        location.addExam(this);
    }
}
