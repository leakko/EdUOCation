package edu.uoc.eduocation.controller;

import edu.uoc.eduocation.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Controller class for the EdUOCation application
 */
public class EdUOCationController {

    //TODO: Define the attributes
    private LinkedList<School> schools;
    private LinkedList<Teacher> teachers;
    private LinkedList<Subject> subjects;

    /**
     * Constructor that loads the data from the files
     * @param schoolsFilename Name of the file with the schools data
     * @param teachersFilename Name of the file with the teachers data
     * @param courseFilename Name of the file with the courses data
     * @param studentGroupsFilename Name of the file with the student groups data
     * @param enrollmentsFilename Name of the file with the enrollments data
     */
    public EdUOCationController(String schoolsFilename, String teachersFilename, String courseFilename, String studentGroupsFilename, String enrollmentsFilename) {
        //TODO
        schools = new LinkedList<>();
        teachers = new LinkedList<>();
        subjects = new LinkedList<>();
        loadSchools(schoolsFilename);
        loadTeachers(teachersFilename);
        loadCourses(courseFilename);
        loadStudentGroups(studentGroupsFilename);
        loadEnrollments(enrollmentsFilename);
    }

    /**
     * Load schools from a file
     * @param filename Name of the file to load
     */
    private void loadSchools(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/data/" + filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip comments or empty lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split school name and locations
                String[] parts = line.split("\\|", 2);
                if (parts.length < 2) {
                    continue; // Skip malformed lines
                }

                String schoolName = parts[0].trim();
                String[] locationStrings = parts[1].split("\\|");

                addSchool(schoolName, locationStrings);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Load teachers from a file
     * @param filename Name of the file to load
     */
    private void loadTeachers(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/data/" + filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip comments or empty lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split teacher data
                String[] parts = line.split("\\|");
                if (parts.length != 5) {
                    continue; // Skip malformed lines
                }

                String nif = parts[0].trim();
                String name = parts[1].trim();
                String surname = parts[2].trim();
                LocalDate birthdate = LocalDate.parse(parts[3].trim());
                String department = parts[4].trim();

                addTeacher(nif, name, surname, birthdate, department);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Load courses from a file
     * @param filename Name of the file to load
     */
    private void loadCourses(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/data/" + filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip comments or empty lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split course data
                String[] parts = line.split("\\|");
                if (parts.length < 6) {
                    continue; // Skip malformed lines
                }

                String type = parts[0].trim();
                String name = parts[1].trim();
                String code = parts[2].trim();
                int credits = Integer.parseInt(parts[3].trim());
                int hours = Integer.parseInt(parts[4].trim());
                String teacherNif = parts[5].trim();
                String additionalInfo = parts.length > 6 ? parts[6].trim() : null;

                addCourse(type, name, code, credits, hours, teacherNif, additionalInfo);
            }
        } catch (Exception e) {
            System.err.println("Error reading file: " + e.getMessage());
        }
    }

    /**
     * Load student groups from a file
     * @param filename Name of the file to load
     */
    private void loadStudentGroups(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/data/" + filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip comments or empty lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split school name, group name, tutor, and students
                String[] parts = line.split("\\|", 4);
                if (parts.length < 4) {
                    continue; // Skip malformed lines
                }

                String schoolName = parts[0].trim();
                String groupName = parts[1].trim();
                String tutorNif = parts[2].trim();
                String[] studentData = parts[3].split(",");

                addStudentGroup(schoolName, groupName, tutorNif, studentData);
            }
        } catch (Exception e) {
            System.err.println("Error reading student groups file: " + e.getMessage());
        }
    }

    /**
     * Load the enrollments from a file
     * @param filename Name of the file to load
     */
    private void loadEnrollments(String filename) {
        try (InputStream is = getClass().getResourceAsStream("/data/" + filename);
             BufferedReader reader = new BufferedReader(new InputStreamReader(is))) {

            String line;
            while ((line = reader.readLine()) != null) {
                // Skip comments or empty lines
                if (line.startsWith("#") || line.trim().isEmpty()) {
                    continue;
                }

                // Split enrollment data
                String[] parts = line.split("\\|", 5);
                if (parts.length < 5) {
                    continue; // Skip malformed lines
                }

                String studentNif = parts[0].trim();
                String courseCode = parts[1].trim();
                String semester = parts[2].trim();
                String enrollmentType = parts[3].trim();
                String additionalInfo = parts[4].trim();

                addEnrollment(studentNif, courseCode, semester, enrollmentType, additionalInfo);
            }
        } catch (Exception e) {
            System.err.println("Error reading enrollments file: " + e.getMessage());
        }
    }

    /**
     * Add a student group to the controller
     * @param name Name of the student group
     * @param locations Array of strings with the format "name, address, city, country"
     */
    public void addSchool(String name, String... locations) {
        //TODO
        School school = new School(name);
        for (String locationString : locations) {
            String[] parts = locationString.split(",");
            Location location = new Location(parts[0].trim(), parts[1].trim(), parts[2].trim(), parts[3].trim());
            school.addLocation(location);
        }
        schools.add(school);
    }

    /**
     * Add a student group to the controller
     * @param nif NIF of the teacher
     * @param name Name of the teacher
     * @param surname Surname of the teacher
     * @param birthdate Birthdate of the teacher
     * @param department Department of the teacher
     */
    public void addTeacher(String nif, String name, String surname, LocalDate birthdate, String department) {
        //TODO
        Department departm = new Department(department);
        Teacher teacher = new Teacher(nif, name, surname, birthdate);
        teacher.setDepartment(departm);
        teachers.add(teacher);
    }

    /**
     * Add a student group to the controller
     * @param type Type of the course
     * @param name Name of the course
     * @param code Code of the course
     * @param credits Credits of the course
     * @param hours Hours of the course
     * @param teacherNif NIF of the teacher
     * @param additionalInfo Additional information for the course (exam or practice information)
     */
    public void addCourse(String type, String name, String code, int credits, int hours, String teacherNif, String additionalInfo) {
        //TODO
        Subject subject = new Subject(name, code, credits, hours);
        Teacher foundTeacher = Teacher.getTeacherByNif(teachers, teacherNif);
        if(foundTeacher == null) {
            throw new IllegalArgumentException("Teacher not found");
        }
        subject.setTeacher(foundTeacher);

        SubjectType subjectType = SubjectType.getByType(type);
        subject.setType(subjectType);

        if(subjectType == SubjectType.WITHOUT_EXAM) {
            subjects.add(subject);
            return;
        }

        String[] addInfo = additionalInfo.split(",");

        if (subjectType == SubjectType.WITH_EXAM) {
            String dateAndTime = addInfo[0];
            String schoolName = addInfo[1];
            String locationAddress = addInfo[2];
            String roomName = addInfo[3];

            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            LocalDate date = LocalDate.parse(dateAndTime.substring(0, 10), dateFormatter);
            LocalTime time = LocalTime.parse(dateAndTime.substring(11), timeFormatter);

            Exam exam = new Exam(subject);
            exam.setDate(date);
            exam.setTime(time);
            School school = NamedEntity.findByName(schools, schoolName);
            Location courseLocation = Location.getByAddress(school.getLocations(), locationAddress);
            exam.setLocation(courseLocation);
            subject.setExam(exam);
            subject.setType(subjectType);
            subject.setRoom(roomName);
            subjects.add(subject);
            return;
        }

        String practiceLength = addInfo[0];
        PracticeType practiceType = PracticeType.getByType(practiceLength);
        if(practiceType == null) {
            throw new IllegalArgumentException("Practice type not found");
        }
        subject.setPracticeType(practiceType);

        if(subjectType == SubjectType.WITH_PRACTICE_INDIVIDUAL) {
          subjects.add(subject);
          return;
        }

        if(subjectType == SubjectType.WITH_PRACTICE_GROUP) {
            int maxGroupMembers = Integer.parseInt(addInfo[1]);

            subject.setMaxMembers(maxGroupMembers);
            subjects.add(subject);
        }
    }

    /**
     * Add a group of students to the university
     * @param schoolName Name of the school
     * @param groupName Name of the group
     * @param tutorNIF NIF of the tutor
     * @param studentData Array of the students data
     */
    public void addStudentGroup(String schoolName, String groupName, String tutorNIF, String[] studentData) {
        //TODO

        School school = NamedEntity.findByName(schools, schoolName);
        if(school == null) {
            throw new IllegalArgumentException("School not found");
        }
        Group group = NamedEntity.findByName(getAllGroups(), groupName);
        if(group == null) {
            group = new Group(groupName);
            group.setSchool(school);
        }
        Teacher teacher = Person.findByNif(teachers, tutorNIF);
        if(teacher == null) {
            throw new IllegalArgumentException("Teacher not found");
        }

        Tutor tutor = new Tutor(
                teacher.getNif(),
                teacher.getName(),
                teacher.getSurname(),
                teacher.getBirthDate()
        );
        group.setTutor(tutor);

        for (String studentInfo : studentData) {
            String[] parts = studentInfo.split(":");
            Student student = new Student(parts[0], parts[1], parts[2], LocalDate.parse(parts[3]));
            group.addStudent(student);
        }
    }

    /**
     * Add an enrollment to the student
     * @param studentNIF NIF of the student
     * @param courseCode Code of the course
     * @param semester Semester of the enrollment
     * @param enrollmentType Type of the enrollment (individual or multiple)
     * @param additionalInfo Additional information for the enrollment
     */
    public void addEnrollment(String studentNIF, String courseCode, String semester, String enrollmentType, String additionalInfo) {
        //TODO

        Subject subject = Subject.getByCode(subjects, courseCode);
        EnrollmentType newEnrollmentType = EnrollmentType.getByType(enrollmentType);
        Student student = Person.findByNif(getAllStudents(), studentNIF);

        Semester enrollmentSemester = NamedEntity.findByName(getAllSemesters(), semester);
        if (enrollmentSemester == null) {
            enrollmentSemester = new Semester(semester);
        }

        Enrollment enrollment = new Enrollment(newEnrollmentType);
        Course course = Course.getCourse(getAllCourses(), subject, enrollmentSemester);
        if (course == null) {
            course = new Course();
            course.setSemester(enrollmentSemester);
            course.setSubject(subject);
        }

        enrollment.addStudent(student);

        if (newEnrollmentType == EnrollmentType.MULTIPLE) {
            String[] studentsNifs = additionalInfo.split(",");
            for (String additionalStudentNif : studentsNifs) {
                Student additionalStudent = Person.findByNif(getAllStudents(), additionalStudentNif);
                enrollment.addStudent(additionalStudent);
            }
        }

        enrollment.setStatus(EnrollmentStatus.PENDING);
        course.addEnrollment(enrollment);
    }

    public boolean updateEnrollmentMark(String course, String semester, String status, String studentNif, double mark) {
        //TODO
        Subject studentSubject = NamedEntity.findByName(subjects, course);
        if (studentSubject == null) {
            return false;
        }
        Semester studentSemester = NamedEntity.findByName(getAllSemesters(), semester);

        if (studentSemester == null) {
            return false;
        }

        EnrollmentStatus enrollmentStatus = EnrollmentStatus.getByStatus(status);
        if (enrollmentStatus == null) {
            return false;
        }

        Student student = Person.findByNif(getAllStudents(), studentNif);

        Course studentCourse = Course.getCourse(getAllCourses(), studentSubject, studentSemester);

        if (studentCourse == null) {
            return false;
        }

        Enrollment enrollment = Enrollment.getByNif(studentCourse.getEnrollments(), studentNif);

        return enrollment.setGrade(mark);
    }


    /**
     * Get the list of schools
     * @return List of schools
     */
    public List<String> getSchools() {
        //TODO
        if(schools == null) {
            return new LinkedList<>();
        }
        return schools.stream()
                .map(School::toString)
                .collect(Collectors.toList());
    }

    /**
     * Get the list of teachers
     * @return List of teachers
     */
    public List<String> getTeachers() {
        //TODO
        if(teachers == null) {
            return new LinkedList<>();
        }
        return teachers.stream()
                .map(NamedEntity::toString)
                .collect(Collectors.toList());
    }

    /**
     * Get the list of courses
     * @return List of courses
     */
    public List<String> getCourses() {
        //TODO
        if(subjects == null) {
            return new LinkedList<>();
        }
        return subjects.stream()
                .map(subject -> subject.getName() + "|" + subject.getCode())
                .collect(Collectors.toList());
    }

    /**
     * Get the list of groups for a school
     * @param schoolName Name of the school
     * @return List of groups
     */
    public List<String> getGroups(String schoolName) {
        //TODO
        School school = getSchoolByName(schoolName);
        LinkedList<Group> groups = school.getGroups();

        if(groups == null) {
            return new LinkedList<>();
        }
        return groups.stream()
                .map(NamedEntity::toString)
                .collect(Collectors.toList());
    }

    /**
     * Get the list of students for a group
     * @param schoolName Name of the school
     * @param groupName Name of the group
     * @return List of students
     */
    public List<String> getStudents(String schoolName, String groupName) {
        //TODO
        School school = getSchoolByName(schoolName);
        LinkedList<Group> groups = school.getGroups();

        if(groups == null) {
            return new LinkedList<>();
        }

        Group group = NamedEntity.findByName(getAllGroups(), groupName);

        return group.getStudents().stream()
                .map(NamedEntity::toString)
                .collect(Collectors.toList());
    }

    /**
     * Get the list of enrollments for a student
     * @param schoolName Name of the school
     * @param groupName Name of the group
     * @param studentNIF NIF of the student
     * @return List of enrollments
     */
    public List<String> getEnrollments(String schoolName, String groupName, String studentNIF) {
        //TODO
        School school = getSchoolByName(schoolName);
        LinkedList<Group> groups = school.getGroups();

        if(groups == null) {
            return new LinkedList<>();
        }

        Group group = NamedEntity.findByName(getAllGroups(), groupName);

        LinkedList<Student> students = group.getStudents();

        Student student = Person.findByNif(getAllStudents(), studentNIF);

        return student.getEnrollments().stream()
                .map(Enrollment::toString)
                .collect(Collectors.toList());
    }

    public LinkedList<Location> getAllLocations() {
        LinkedList<Location> locations = new LinkedList<>();

        for (School school : schools) {
            LinkedList<Location> schoolLocations = school.getLocations();
            locations.addAll(schoolLocations);
        }

        return locations;
    }

    public LinkedList<Group> getAllGroups() {
        LinkedList<Group> groups = new LinkedList<>();

        for (School school : schools) {
            LinkedList<Group> groupLocations = school.getGroups();
            groups.addAll(groupLocations);
        }

        return groups;
    }

    public LinkedList<Student> getAllStudents() {
        LinkedList<Student> students = new LinkedList<>();

        for (Group group : getAllGroups()) {
            LinkedList<Student> groupStudents = group.getStudents();
            students.addAll(groupStudents);
        }

        return students;
    }

    public LinkedList<Course> getAllCourses() {
        LinkedList<Course> courses = new LinkedList<>();

        for (Subject subject : subjects) {
            LinkedList<Course> subjectCourses = subject.getCourses();
            courses.addAll(subjectCourses);
        }

        return courses;
    }

    public LinkedList<Semester> getAllSemesters() {
        LinkedList<Semester> semesters = new LinkedList<>();

        for (Course course : getAllCourses()) {
            Semester semester = course.getSemester();
            semesters.add(semester);
        }

        return semesters;
    }

    public LinkedList<Enrollment> getAllEnrollments() {
        LinkedList<Enrollment> enrollments = new LinkedList<>();

        for (Student student : getAllStudents()) {
            LinkedList<Enrollment> studentEnrollments = student.getEnrollments();
            enrollments.addAll(studentEnrollments);
        }

        return enrollments;
    }

    public School getSchoolByName(String schoolName) {
        return NamedEntity.findByName(schools, schoolName);
    }
}
