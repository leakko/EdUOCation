package edu.uoc.eduocation.model;

import java.time.LocalDate;
import java.util.LinkedList;

public class Tutor extends Teacher {

    private LinkedList<Group> groups;

    Tutor(String nif, String name, String surname, LocalDate birthday) {
        super(nif, name, surname, birthday);
        this.groups = new LinkedList<>();
    }

    public LinkedList<Group> getGroups() {
        return groups;
    }

    public void addGroup(Group group) {
        group.setTutor(this);
        groups.add(group);
    }

    public void removeGroup(Group group) {
        group.setTutor(null);
        groups.remove(group);
    }

}
