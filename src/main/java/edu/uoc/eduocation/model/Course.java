package edu.uoc.eduocation.model;

import java.util.LinkedList;

public class Course {
    private LinkedList<Enrollment> enrollments;
    private Semester semester;
    private Subject subject;
    private SubjectType type;

    public Course() {
        this.enrollments = new LinkedList<>();
    }

    public LinkedList<Enrollment> getEnrollments() {
        return enrollments;
    }

    public void addEnrollment(Enrollment enrollment) {
        enrollments.add(enrollment);
        if(enrollment.getCourse() != this) {
            enrollment.setCourse(this);
        }
    }

    public void removeEnrollment(Enrollment enrollment) {
        enrollment.setCourse(null);
        enrollments.remove(enrollment);
    }

    public Semester getSemester() {
        return semester;
    }

    public void setSemester(Semester semester) {
        Semester lastSemester = getSemester();
        if (lastSemester != null) {
            lastSemester.removeCourse(this);
        }
        if (semester != null) {
            semester.addCourse(this);
        }
        this.semester = semester;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        Subject lastSubject = getSubject();
        if (lastSubject != null) {
            lastSubject.removeCourse(this);
        }
        if(subject != null && !subject.getCourses().contains(this)) {
            subject.addCourse(this);
        }
        this.subject = subject;
    }

    public static Course getCourse(LinkedList<Course> courses, Subject subject, Semester semester) {
        return courses.stream()
                .filter(course -> course.getSubject() == subject)
                .filter(course -> course.getSemester() == semester)
                .findFirst()
                .orElse(null);
    }
}
