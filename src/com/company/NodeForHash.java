package com.company;

import java.util.ArrayList;

public class NodeForHash {
    private String name = null; // ID
    private int age = 0;
    ArrayList<NodeForHash> chaining = new ArrayList<>();

    public NodeForHash() {

    }

    public NodeForHash(String name, int age) {
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
