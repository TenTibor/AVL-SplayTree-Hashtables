package com.company;

public class AVL {
    AVLNode root = null; // root of the tree

    // Recursive function to adding new node
    public AVLNode addItem(AVLNode addTo, AVLNode addThis) {
        if (addTo == null) // insert if node is free
            addTo = addThis;

            // Choose right or left side by name difference
        else if (addTo.getName().compareTo(addThis.getName()) < 0) {
            addTo.right = addItem(addTo.right, addThis);
        } else if (addTo.getName().compareTo(addThis.getName()) > 0) {
            addTo.left = addItem(addTo.left, addThis);
        }

        // return rebalanced node
        return addTo.rebalanced();
    }

    // Recursive function to find node
    public boolean findItem(String findThisName, AVLNode findHere) {
        boolean found = false;
        if (findHere.getName().equals(findThisName))
            found = true;

            // Choose right or left side by name difference
        else if (findHere.getName().compareTo(findThisName) < 0 && findHere.getRight() != null) {
            found = findItem(findThisName, findHere.getRight());
        } else if (findHere.getName().compareTo(findThisName) > 0 && findHere.getLeft() != null) {
            found = findItem(findThisName, findHere.getLeft());
        }

        return found;
    }

    // print all nodes
    public void printTree() {
        System.out.println("---------------------------");
        int rootCount = printNode(this.root, 0);
        System.out.println("Count of items is: " + rootCount);
        System.out.println("===========================");
    }

    // print one node
    public int printNode(AVLNode thisNode, int count) {
        if (thisNode != null) {
            count++;
            System.out.println(thisNode.getName() + "(" + thisNode.balance + "/" + thisNode.getDepth() + ")=> " + (thisNode.left != null ? thisNode.left.getName() : "-") + "/" + (thisNode.right != null ? thisNode.right.getName() : "-"));
            count = printNode(thisNode.left, count);
            count = printNode(thisNode.right, count);
            return count;
        }
        return count;
    }
}
