package com.company;

import java.util.ArrayList;

public class ChainingNode {
    private String name = null; // ID
    private int age = 0;
    ArrayList<ChainingNode> chaining = new ArrayList<>();

    public ChainingNode(String name, int age) {
        this.name = name;
        this.age = age;
    }

    // print name and age of item
    public void print() {
        System.out.println("[" + name + ", " + age + "]");
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
}
