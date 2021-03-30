package com.company;

public class Main {

    public static void main(String[] args) {

//        String[] testWords = {"1", "10", "5"};
//        String[] testWords = {"d", "a", "f", "g", "h"};
//        String[] testWords = {"s", "a", "x", "d"};
//        String[] testWords = {"s", "a", "x", "d", "b"};
//        String[] testWords = {"s", "a", "x", "d", "b","c", "g", "h", "j", "z"}; //TODO fix this
        String[] testWords = {"c", "b", "a", "d"};
//        String[] testWords = {"a", "b", "c"};

        Node root = null;
        for (String word : testWords) {
            root = addItem(root, new Node(word));
        }

        printTree(root);
//        System.out.println("===========");
//        root = addItem(root, new Item("b"));
//        printTree(root);
    }

    public static Node addItem(Node addTo, Node addThis) {
        //If this item is empty
        if (addTo == null)
            addTo = addThis;

            // Else continue in tree
        else if (addTo.getName().compareTo(addThis.getName()) < 0) {
            addTo.right = addItem(addTo.right, addThis);
        } else if (addTo.getName().compareTo(addThis.getName()) > 0) {
            addTo.left = addItem(addTo.left, addThis);
        }

        // set new balance
        addTo = addTo.refreshBalance();

        // check if was rotation
        // Check right side
//        if (addTo.right != null) {
//            // Right rotation with depth one
//            Node tempNode = null;
////            System.out.println(addTo.rotated);
//            if (addTo.rotated > 0) {
//                if (addTo.rotated == 2) {
//                    addTo.setDepth(addTo.right.left.getDepth());
//                    addTo.right.left.increaseDepth();
//                    addTo.right.decreaseDepth();
//                    tempNode = addTo.right.left;
//                    addTo.right.left = null;
//                    addTo.right = null;
//                } else {
//                    System.out.println("im here");
//                    addTo.setDepth(addTo.right.getDepth() - 1);
//                    tempNode = addTo.right;
//                    addTo.right = null;
//                }
//
//                // Left rotation with depth one
//            } else if (addTo.rotated < 0) {
//                System.out.println("im here");
//                addTo.setDepth(addTo.left.getDepth() - 1);
//                tempNode = addTo.left;
//                addTo.left = null;
//            }
//            if (tempNode != null) addTo = tempNode;
//        }
//
//        // Check left side
//        else if (addTo.left != null) {
//            // Right rotation with depth one
//            Node tempNode = null;
//            if (addTo.rotated > 0) {
//                System.out.println("im here");
//                addTo.setDepth(addTo.right.getDepth() - 1);
//                tempNode = addTo.right;
//                addTo.right = null;
//
//                // Left rotation with depth one
//            } else if (addTo.rotated < 0) {
//                System.out.println("im here");
//                addTo.setDepth(addTo.left.getDepth() - 1);
//                tempNode = addTo.left;
//                addTo.left = null;
//            }
//            if (tempNode != null) addTo = tempNode;
//        }

        return addTo;
    }

    public static void printTree(Node thisNode) {
        if (thisNode != null) {
            System.out.println(thisNode.getName() + "(" + thisNode.balance + "/" + thisNode.getDepth() + ")->" + (thisNode.left != null ? thisNode.left.getName() : "[none]") + "/" + (thisNode.right != null ? thisNode.right.getName() : "[none]"));
            printTree(thisNode.left);
            printTree(thisNode.right);
        }
    }
}
