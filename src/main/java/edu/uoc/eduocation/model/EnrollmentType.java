package edu.uoc.eduocation.model;

public enum EnrollmentType {
    INDIVIDUAL("INDIVIDUAL"),
    MULTIPLE("MULTIPLE");

    private final String type;

    EnrollmentType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static EnrollmentType getByType(String type) {
        for (EnrollmentType enumObj : EnrollmentType.values()) {
            if (enumObj.getType().equals(type)) {
                return enumObj;
            }
        }
        return null;
    }
}