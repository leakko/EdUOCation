package edu.uoc.eduocation.model;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Teacher extends Person{

    private Department department;
    private LinkedList<Subject> subjects;

    public Teacher (String nif, String name, String surname, LocalDate birthday) {
        super(nif, name, surname, birthday);
        this.subjects = new LinkedList<>();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        if (department == null) {
            throw new NullPointerException("Department is null");
        }
        Department lastDepartment = getDepartment();
        if (lastDepartment != null) {
            lastDepartment.removeTeacher(this, department);
        }

        this.department = department;
        department.addTeacher(this);
    }

    public LinkedList<Subject> getSubjects() {
        return subjects;
    }

    public void addSubject(Subject subject) {
        if (subject != null && subject.getTeacher() != this) {
            subject.setTeacher(this);
        }
        subjects.add(subject);
    }

    public void removeSubject(Subject subject, Teacher newTeacher) {
        subject.setTeacher(newTeacher);
        subjects.remove(subject);
    }

    public static Teacher getTeacherByNif(LinkedList<Teacher> teachers, String nif) {
        return teachers.stream()
                .filter(teacher -> teacher.getNif().equals(nif))
                .findFirst()
                .orElse(null);
    }
}
