package com.company;

public class Main {

    public static void main(String[] args) {
        Item root = null;
        root = addItem(root, new Item("5"));
        root = addItem(root, new Item("4"));
        root = addItem(root, new Item("6"));
        root = addItem(root, new Item("7"));
        root = addItem(root, new Item("8"));
        printTree(root);
    }

    public static Item addItem(Item addTo, Item addThis) {
        //If this item is empty
        if (addTo == null)
            addTo = addThis;

            // Else continue in tree
        else if (addTo.getName().compareTo(addThis.getName()) < 0) {
            addTo.right = addItem(addTo.right, addThis);
        } else if (addTo.getName().compareTo(addThis.getName()) > 0) {
            addTo.left = addItem(addTo.left, addThis);
        }

        // set new depth
        addTo.refreshDepth();

        // check if was rotation

//        Right rotation with depth one
        if (addTo.right != null && addTo.right.rotated > 0) {
            addTo.right.setDepth(1);
            Item tempItem = addTo.right.right;
            addTo.right.right = null;
            addTo.right = tempItem;
        }

        return addTo;
    }

    public static void printTree(Item root) {
        Item thisItem = root;
        if (thisItem != null) {
            System.out.println(root.getName() + "(" + thisItem.getDepth() + ")->" + (thisItem.left != null ? thisItem.left.getName() : "[none]") + "/" + (thisItem.right != null ? thisItem.right.getName() : "[none]"));
            printTree(thisItem.left);
            printTree(thisItem.right);
        }
    }
}
