package edu.uoc.eduocation.model;

import java.util.LinkedList;

public class Department extends NamedEntity {
    LinkedList<Teacher> teachers;
    public Department(String name) {
        super(name);
        this.teachers = new LinkedList<>();
    }

    public LinkedList<Teacher> getTeachers() {
        return teachers;
    }

    public void addTeacher(Teacher teacher) {
        if(teacher.getDepartment() != this) {
            teacher.setDepartment(this);
        }
        teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher, Department newDepartment) {
        teacher.setDepartment(newDepartment);
        teachers.remove(teacher);
    }
}
