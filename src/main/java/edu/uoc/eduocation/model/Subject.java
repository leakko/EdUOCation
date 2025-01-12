package edu.uoc.eduocation.model;

import java.util.LinkedList;

public class Subject extends NamedEntity {
    private String code;
    private int credits;
    private int hours;
    private String room;
    private int maxMembers;
    private LinkedList<Course> courses;
    private Teacher teacher;
    private PracticeType practiceType;
    private Exam exam;
    private SubjectType type;


    public Subject(String name, String code, int credits, int hours) {
        super(name);
        setCode(code);
        setCredits(credits);
        setHours(hours);
        this.courses = new LinkedList<>();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        if(credits < 0) {
            throw new IllegalArgumentException("Credits cannot be negative");
        }
        this.credits = credits;
    }

    public int getHours() {
        return hours;
    }

    public void setHours(int hours) {
        if(hours < 0) {
            throw new IllegalArgumentException("Hours cannot be negative");
        }
        this.hours = hours;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getMaxMembers() {
        return maxMembers;
    }

    public void setMaxMembers(int maxMembers) {
        this.maxMembers = maxMembers;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        if(teacher == null) {
            throw new IllegalArgumentException("Teacher cannot be null");
        }
        this.teacher = teacher;
        teacher.addSubject(this);
    }

    public PracticeType getPracticeType() {
        return practiceType;
    }

    public void setPracticeType(PracticeType practiceType) {
        this.practiceType = practiceType;
    }

    public LinkedList<Course> getCourses() {
        return courses;
    }

    public void addCourse(Course course) {
        courses.add(course);
        course.setSubject(this);
    }

    public void removeCourse(Course course) {
        course.setSubject(null);
        courses.remove(course);
    }

    public Exam getExam() {
        return exam;
    }

    public void setExam(Exam exam) {
        if(exam.getLocation() == null) {
            throw new IllegalArgumentException("Exam location cannot be null");
        }
        this.exam = exam;
    }

    public SubjectType getType() {
        return type;
    }

    public void setType(SubjectType type) {
        this.type = type;
    }

    public static Subject getByCode(LinkedList<Subject> subjects, String code) {
        return subjects.stream()
                .filter(subject -> subject.getCode().equals(code))
                .findFirst()
                .orElse(null);
    }

}
