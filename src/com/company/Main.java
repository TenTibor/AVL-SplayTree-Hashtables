package com.company;

public class Main {

    public static void main(String[] args) {

        String[] testWords = {"5", "0", "9", "8", "7", "20", "1", "2"};

        Item root = null;
        for (String word:testWords) {
            root = addItem(root, new Item(word));
        }

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
            if (addTo.right.rotated > 0) {
                addTo.right.setDepth(addTo.right.right.getDepth() - 1);
                tempItem = addTo.right.right;
                addTo.right.right = null;

                // Left rotation with depth one
            } else if (addTo.right.rotated < 0) {
                addTo.right.setDepth(addTo.right.left.getDepth() - 1);
                tempItem = addTo.right.left;
                addTo.right.left = null;
            }
            if (tempItem != null) addTo.right = tempItem;
        }

        // Check left side
        if (addTo.left != null) {
            // Right rotation with depth one
            Item tempItem = null;
            if (addTo.left.rotated > 0) {
                addTo.left.setDepth(addTo.left.right.getDepth() - 1);
                tempItem = addTo.left.right;
                addTo.left.right = null;

                // Left rotation with depth one
            } else if (addTo.left.rotated < 0) {
                addTo.left.setDepth(addTo.left.left.getDepth() - 1);
                tempItem = addTo.left.left;
                addTo.left.left = null;
            }
            if (tempItem != null) addTo.left = tempItem;
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
