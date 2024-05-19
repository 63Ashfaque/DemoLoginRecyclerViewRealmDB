package com.ashfaque.demologinrecyclerview.model;

import io.realm.RealmObject;


public class Person extends RealmObject {

    int id;
    private String name;

    private int age;

    private String city;

    // Default constructor required for calls to DataSnapshot.getValue(User.class)
    public Person() {
    }

    public Person(String name, int age, String city,int id) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.city = city;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getters and setters for fields
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}

