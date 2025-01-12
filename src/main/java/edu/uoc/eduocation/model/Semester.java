package edu.uoc.eduocation.model;

import java.util.LinkedList;

public class Semester extends NamedEntity {
    private LinkedList<Course> courses;

    public Semester(String name) {
        super(name);
        this.courses = new LinkedList<>();
    }

    public LinkedList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
    }

    public void removeCourse(Course course) {
        courses.remove(course);
    }
}
