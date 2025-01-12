package edu.uoc.eduocation.model;

import com.google.gson.Gson;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class Enrollment {
    private double grade;
    private LinkedList<Student> students;
    private Course course;
    private EnrollmentType type;
    private EnrollmentStatus status;

   public Enrollment(EnrollmentType type) {
       setType(type);
       students = new LinkedList<>();
   }

    public double getGrade() {
        return grade;
    }

    public boolean setGrade(double grade) {
       if (grade < 0 || grade > 10) {
           return false;
       }
        this.grade = grade;
       return true;
    }

    public EnrollmentType getType() {
       return type;
    }

    public void setType(EnrollmentType type) {
       this.type = type;
    }

    public LinkedList<Student> getStudents() {
        return students;
    }

    public void addStudent(Student student) {
       if (getType() == EnrollmentType.INDIVIDUAL) {
           getStudents().clear();
       }
       students.add(student);
       if(!student.getEnrollments().contains(this)) {
           student.addEnrollment(this);
       }
    }

    public void removeStudent(Student student) {
        student.getEnrollments().forEach(enrollment -> {
            if(enrollment == this) {
                student.removeEnrollment(enrollment);
            }
        });
       students.remove(student);
    }

    public Course getCourse() {
       return course;
    }

    public void setCourse(Course course) {
       Course lastCourse = getCourse();
       if (lastCourse != null) {
           lastCourse.removeEnrollment(this);
       }
        this.course = course;
       if (course != null) {
           course.addEnrollment(this);
       }
    }

    public EnrollmentStatus getStatus() {
       return status;
    }

    public void setStatus(EnrollmentStatus status) {
       this.status = status;
    }

    public static Enrollment getByNif(LinkedList<Enrollment> enrollments, String nif) {
        return enrollments.stream()
                .filter(enrollment -> enrollment.getStudents().getFirst().getNif().equals(nif))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        Map<String, Object> map = new HashMap<>();
        map.put("course", this.course.getSubject().getName());
        map.put("semester", this.getCourse().getSemester().getName());
        map.put("status", this.getStatus().toString());
        map.put("mark", this.getGrade());

        Gson gson = new Gson();

        return gson.toJson(map);
    }
}
