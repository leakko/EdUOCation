package edu.uoc.eduocation.model;

public enum PracticeType {
    LONG("LONG"),
    SHORT("SHORT");

    private final String type;

    PracticeType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public static PracticeType getByType(String type) {
        for (PracticeType enumObj : PracticeType.values()) {
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