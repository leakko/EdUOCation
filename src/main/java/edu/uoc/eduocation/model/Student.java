package edu.uoc.eduocation.model;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Student extends Person {
    private Group group;
    private LinkedList<Enrollment> enrollments;


    public Student(String nif, String name, String surname, LocalDate birthday) {
        super(nif, name, surname, birthday);
        enrollments = new LinkedList<>();
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        Group lastGroup = getGroup();
        if(lastGroup != null) {
            lastGroup.removeStudent(this);
        }
        this.group = group;
        if(group != null) {
            group.addStudent(this);
        }
    }

    public LinkedList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
        enrollment.addStudent(this);
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollments.remove(enrollment);
    }
}
