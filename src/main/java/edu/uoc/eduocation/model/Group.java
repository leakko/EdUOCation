package edu.uoc.eduocation.model;

import java.util.LinkedList;

public class Group extends NamedEntity {
    private School school;
    private LinkedList<Student> students;
    private Tutor tutor;

    public Group(String name) {
        super(name);
        students = new LinkedList<>();
    }

    public School getSchool() {
        return school;
    }

    public void setSchool(School school) {
        School lastSchool = getSchool();
        if(lastSchool != null) {
            lastSchool.removeGroup(this);
        }
        this.school = school;
        school.addGroup(this);
    }

    public LinkedList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
        students.add(student);
        if(student.getGroup() != this) {
            student.setGroup(this);
        }
    }

    public void removeStudent(Student student) {
        student.setGroup(null);
        students.remove(student);
    }

    public Tutor getTutor() {
        return tutor;
    }

    public void setTutor(Tutor tutor) {
        Tutor lastTutor = getTutor();
        lastTutor.removeGroup(this);
        if(tutor != null){
            tutor.addGroup(this);
        }
        this.tutor = tutor;
    }
}
