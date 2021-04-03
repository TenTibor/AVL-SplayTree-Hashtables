package com.company;

import java.io.IOException;
import java.util.ArrayList;

public class MyAVL {
    Node root = null;

    public void addItemsToTree(ArrayList<Node> items) throws IOException {
        long timeStarted = System.currentTimeMillis();
        Node root = null;
        for (Node thisNode : items) {
            root = addItem(root, thisNode);
        }

        long timeFinished = System.currentTimeMillis();
        System.out.println(items.size() + " items was added in " + (timeFinished - timeStarted) + " ms");
        this.root = root;
    }

    public Node addItem(Node addTo, Node addThis) {
        if (addTo == null)
            addTo = addThis;

        else if (addTo.getName().compareTo(addThis.getName()) < 0) {
            addTo.right = addItem(addTo.right, addThis);
        } else if (addTo.getName().compareTo(addThis.getName()) > 0) {
            addTo.left = addItem(addTo.left, addThis);
        }

        return addTo.rebalanced();
    }

    public void searchManyItems(ArrayList<Node> items) {
        long timeStarted = System.currentTimeMillis();

        // Pick some range
        int countOfAllItems = items.size();
        int downIndex = countOfAllItems / 4;
        int upIndex = countOfAllItems / 3 + downIndex;

        // Search any item in range
        int foundItems = 0;
        for (int i = downIndex; i < upIndex; i++) {
            if (findItem(items.get(i).getName(), this.root)) foundItems++;
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println(foundItems + "(/" + (upIndex - downIndex) + ") items was found in: " + (timeFinished - timeStarted) + " ms");
    }

    public boolean findItem(String findThisName, Node findHere) {
        boolean found = false;
        if (findHere.getName().equals(findThisName)) found = true;

        else if (findHere.getName().compareTo(findThisName) < 0 && findHere.getRight() != null) {
            found = findItem(findThisName, findHere.getRight());
        } else if (findHere.getName().compareTo(findThisName) > 0 && findHere.getLeft() != null) {
            found = findItem(findThisName, findHere.getLeft());
        }

        return found;
    }

    public void printTree() {
        System.out.println("---------------------------");
        int rootCount = printNode(this.root, 0);
        System.out.println("Count of items is: " + rootCount);
        System.out.println("===========================");
    }

    public int printNode(Node thisNode, int count) {
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
