package com.momentoustech.catalog.domain.model;

/**
 * Created by Kleyvert on 10/3/16.
 */
public class Entity {

    protected long id;

    protected String name;

    // Constructor
    public Entity() {}

    public Entity(String name) {
        this.name = name;
    }

    // Getters & setter

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
