package com.company;

public class Main {

    public static void main(String[] args) {

//        String[] testWords = {"1", "10", "5"};
//        String[] testWords = {"d", "a", "f", "g", "h"};
        String[] testWords = {"s", "a", "x", "d"};
//        String[] testWords = {"s", "a", "x", "d", "b"}; //TODO b is not showing
//        String[] testWords = {"a", "b", "c"};

        Item root = null;
        for (String word : testWords) {
            root = addItem(root, new Item(word));
        }

        printTree(root);
        System.out.println("===========");
        root = addItem(root, new Item("b"));
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
        // Check right side
        if (addTo.right != null) {
            // Right rotation with depth one
            Item tempItem = null;
//            System.out.println(addTo.rotated);
            if (addTo.rotated > 0) {
                if (addTo.rotated == 2) {
                    addTo.setDepth(addTo.right.left.getDepth());
                    addTo.right.left.increaseDepth();
                    addTo.right.decreaseDepth();
                    tempItem = addTo.right.left;
                    addTo.right.left = null;
                    addTo.right = null;
                } else {
                    System.out.println("im here");
                    addTo.setDepth(addTo.right.getDepth() - 1);
                    tempItem = addTo.right;
                    addTo.right = null;
                }

                // Left rotation with depth one
            } else if (addTo.rotated < 0) {
                System.out.println("im here");
                addTo.setDepth(addTo.left.getDepth() - 1);
                tempItem = addTo.left;
                addTo.left = null;
            }
            if (tempItem != null) addTo = tempItem;
        }

        // Check left side
        else if (addTo.left != null) {
            // Right rotation with depth one
            Item tempItem = null;
            if (addTo.rotated > 0) {
                System.out.println("im here");
                addTo.setDepth(addTo.right.getDepth() - 1);
                tempItem = addTo.right;
                addTo.right = null;

                // Left rotation with depth one
            } else if (addTo.rotated < 0) {
                System.out.println("im here");
                addTo.setDepth(addTo.left.getDepth() - 1);
                tempItem = addTo.left;
                addTo.left = null;
            }
            if (tempItem != null) addTo = tempItem;
        }

        return addTo;
    }

    public static void printTree(Item thisItem) {
        if (thisItem != null) {
            System.out.println(thisItem.getName() + "(" + thisItem.getDepth() + ")->" + (thisItem.left != null ? thisItem.left.getName() : "[none]") + "/" + (thisItem.right != null ? thisItem.right.getName() : "[none]"));
            printTree(thisItem.left);
            printTree(thisItem.right);
        }
    }
}
