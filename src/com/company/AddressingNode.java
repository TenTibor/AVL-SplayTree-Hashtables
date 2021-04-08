package com.company;

public class AddressingNode {
    private String key;
    private Person value;

    AddressingNode(String key, Person value) {
        this.key = key;
        this.value = value;
    }

    public Person getValue() {
        return value;
    }

    public void setValue(Person value) {
        this.value = value;
    }

    public String getKey() {
        return key;
    }
}
