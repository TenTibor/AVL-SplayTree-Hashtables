package com.company;

public class Main {

    public static void main(String[] args) {
        Item root = null;
        root = addItem(root, new Item("Ahoj"));
        root = addItem(root, new Item("Moja"));
        root = addItem(root, new Item("Tvoja"));
        System.out.println(root.getName());
    }

    //
    public static Item addItem(Item root, Item item) {
        if (root == null)
            root = item;
        else {

        }

        return root;
    }
}
