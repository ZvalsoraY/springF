package ru.ok.spring.models;

import java.util.List;

public class DirectorPerson {
    private int id;
    private String name;
    private String department;
    private List<Integer> personList;

    public DirectorPerson() {
    }

    public DirectorPerson(int id, String name, String department, List<Integer> personList) {
        this.id = id;
        this.name = name;
        this.department = department;
        this.personList = personList;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDepartment() {
        return department;
    }

    public List<Integer> getPersonList() {
        return personList;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setPersonList(List<Integer> personList) {
        this.personList = personList;
    }
}
