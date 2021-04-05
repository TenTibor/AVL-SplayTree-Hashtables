package com.company;

import java.util.ArrayList;

public class PersonForHash {
    private String name = null; // ID
    private int age = 0;
    ArrayList<PersonForHash> chaining = new ArrayList<>();

    public PersonForHash() {

    }

    public PersonForHash(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void print() {
        System.out.println("[" + name + ", " + age + "]");
    }

    public String getName() {
        return name;
    }
}
