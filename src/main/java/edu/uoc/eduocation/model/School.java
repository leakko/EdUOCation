package edu.uoc.eduocation.model;

import java.util.LinkedList;

public class School extends NamedEntity{
    private LinkedList<Location> locations;
    private LinkedList<Group> groups;
    private LinkedList<Department> departments;

    public School(String name){
        super(name);
        locations = new LinkedList<>();
        groups = new LinkedList<>();
        departments = new LinkedList<>();
    }

    public LinkedList<Location> getLocations(){
        return locations;
    }

    public void addLocation(Location location) throws IllegalArgumentException {
        if(locations.contains(location)){
            throw new IllegalArgumentException("Location already exists");
        }
        locations.add(location);
        if (location.getSchool() != this) {
            location.setSchool(this);
        }
    }

    public void removeLocation(Location location){
        location.setSchool(null);
        locations.remove(location);
    }

    public LinkedList<Department> getDepartments(){
        return departments;
    }

    public void addDepartment(Department department) throws IllegalArgumentException {
        if(departments.contains(department)){
            throw new IllegalArgumentException("Department already exists");
        }
        departments.add(department);
    }

    public void removeDepartment(Department department){
        departments.remove(department);
    }

    public LinkedList<Group> getGroups(){
        return groups;
    }

    public void addGroup(Group group) throws IllegalArgumentException {
        if(groups.contains(group)){
            throw new IllegalArgumentException("Group already exists");
        }
        if(group.getSchool() != this) {
            group.setSchool(this);
        }
        groups.add(group);
    }

    public void removeGroup(Group group){
        group.setSchool(null);
        groups.remove(group);
    }
}
