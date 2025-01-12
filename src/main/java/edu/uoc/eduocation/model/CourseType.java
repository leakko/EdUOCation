package edu.uoc.eduocation.model;

public enum CourseType {
    WITH_EXAM("CourseWithExam"),
    WITHOUT_EXAM("CourseWithoutExam"),
    WITH_PRACTICE_GROUP("CourseWithPracticeGroup"),
    WITH_PRACTICE_INDIVIDUAL("CourseWithPracticeIndividual");

    private final String type;

    CourseType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return '[' + type.toUpperCase() + ']';
    }
}
