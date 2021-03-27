package com.company;

public class Main {

    public static void main(String[] args) {
        Item root = null;
        root = addItem(root, new Item("Ahoj"));
        root = addItem(root, new Item("Moja"));
        printTree(root);
    }

    //
    public static Item addItem(Item root, Item item) {
        if (root == null)
            root = item;
        else {
            root.right = item;
        }

        return root;
    }

    public static void printTree(Item root) {
        Item thisItem = root;
        if (thisItem != null) {
            System.out.println(root.getName() + "->" + (thisItem.left != null ? thisItem.left.getName() : "[none]") + "/" + (thisItem.right != null ? thisItem.right.getName() : "[none]"));
            printTree(thisItem.left);
            printTree(thisItem.right);
        }
    }
}
