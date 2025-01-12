package edu.uoc.eduocation.model;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
        if(!students.contains(student)) {
            students.add(student);
        }
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
        if(lastTutor != null) {
            lastTutor.removeGroup(this);
        }
        this.tutor = tutor;
        if(tutor != null){
            tutor.addGroup(this);
        }
    }

    @Override
    public String toString() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", this.getName());
        map.put("tutor", this.getTutor().getName() + " " + this.getTutor().getSurname());
        map.put("studentsCount", this.students.size());

        Gson gson = new Gson();

        return gson.toJson(map);
    }
}
