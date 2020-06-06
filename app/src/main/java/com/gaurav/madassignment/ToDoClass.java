package com.gaurav.madassignment;

public class ToDoClass {

    String id;
    String name, dueDate, description;

    public ToDoClass(String id, String name, String dueDate, String description) {
        this.id = id;
        this.name = name;
        this.dueDate = dueDate;
        this.description = description;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
