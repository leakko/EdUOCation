package edu.uoc.eduocation.model;

public enum EnrollmentStatus {
    PENDING ("PENDING"),
    IN_PROGRESS("IN_PROGRESS"),
    COMPLETED("COMPLETED");

    private final String status;

    EnrollmentStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static EnrollmentStatus getByStatus(String status) {
        for (EnrollmentStatus enumObj : EnrollmentStatus.values()) {
            if (enumObj.getStatus().equals(status)) {
                return enumObj;
            }
        }
        return null;
    }
}