package com.company;

public class Main {

    public static void main(String[] args) {
        Item root = null;
        root = addItem(root, new Item("ahob"));
        root = addItem(root, new Item("ahoa"));
        root = addItem(root, new Item("ahoc"));
        printTree(root);
    }

    public static Item addItem(Item addTo, Item addThis) {
        //If first item
        if (addTo == null)
            addTo = addThis;
        else if (addTo.getName().compareTo(addThis.getName()) < 0) {
            addTo.right = addThis;
        } else if (addTo.getName().compareTo(addThis.getName()) > 0) {
            addTo.left = addThis;
        }

        return addTo;
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
