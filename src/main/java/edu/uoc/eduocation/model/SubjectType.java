package edu.uoc.eduocation.model;

public enum SubjectType {
    WITH_EXAM("CourseWithExam"),
    WITHOUT_EXAM("CourseWithoutExam"),
    WITH_PRACTICE_GROUP("CourseWithPracticeGroup"),
    WITH_PRACTICE_INDIVIDUAL("CourseWithPracticeIndividual");

    private final String type;

    SubjectType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static SubjectType getByType(String type) {
        for (SubjectType enumObj : SubjectType.values()) {
            if (enumObj.getType().equals(type)) {
                return enumObj;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return '[' + type.toUpperCase() + ']';
    }
}
