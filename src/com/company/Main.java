package com.company;

public class Main {

    public static void main(String[] args) {

//        String[] testWords = {"1", "10", "5"};
//        String[] testWords = {"d", "a", "f", "g", "h"};
//        String[] testWords = {"s", "a", "x", "d"};
//        String[] testWords = {"s", "a", "x", "d", "b"};
//        String[] testWords = {"s", "a", "x", "d", "b","c", "g", "h", "j", "z"}; //TODO fix this
        String[] testWords = {"a", "c", "b"}; // right&left
//        String[] testWords = {"c", "a", "b"}; // left&right
//        String[] testWords = {"a", "b", "c"}; // left

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
        addTo = addTo.rebalanced();

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
