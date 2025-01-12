package edu.uoc.eduocation.model;

import java.util.LinkedList;

public abstract class NamedEntity {
    private String name;
    public NamedEntity(String name) throws IllegalArgumentException {
        setName(name);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws IllegalArgumentException {
        if (name == null || name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty");
        }
        this.name = name;
    }

    public static <T extends NamedEntity> T findByName(LinkedList<T> entities, String name) {
        return entities.stream()
                .filter(entity -> entity.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
