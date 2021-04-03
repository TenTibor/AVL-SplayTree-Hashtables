package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Node> importedItems = getItemFromCsvFile("test100k");
        // my implementation
        Node root = addItemsToTree(importedItems);
        searchManyItems(importedItems, root);
    }

    public static Node addItem(Node addTo, Node addThis) {
        if (addTo == null)
            addTo = addThis;

        else if (addTo.getName().compareTo(addThis.getName()) < 0) {
            addTo.right = addItem(addTo.right, addThis);
        } else if (addTo.getName().compareTo(addThis.getName()) > 0) {
            addTo.left = addItem(addTo.left, addThis);
        }

        return addTo.rebalanced();
    }

    public static void searchManyItems(ArrayList<Node> items, Node root) {
        long timeStarted = System.currentTimeMillis();

        // Pick some range
        int countOfAllItems = items.size();
        int downIndex = countOfAllItems / 4;
        int upIndex = countOfAllItems / 3 + downIndex;

        // Search any item in range
        int foundItems = 0;
        for (int i = downIndex; i < upIndex; i++) {
            if (findItem(items.get(i).getName(), root)) foundItems++;
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println(foundItems + "(/" + (upIndex-downIndex) + ") items was found in: " + (timeFinished - timeStarted) + " ms");

    }

    public static boolean findItem(String findThisName, Node findHere) {
        boolean found = false;
        if (findHere.getName().equals(findThisName)) found = true;

        else if (findHere.getName().compareTo(findThisName) < 0 && findHere.getRight() != null) {
            found = findItem(findThisName, findHere.getRight());
        } else if (findHere.getName().compareTo(findThisName) > 0 && findHere.getLeft() != null) {
            found = findItem(findThisName, findHere.getLeft());
        }

        return found;
    }

    public static void testStrings(String[] testWords) throws IOException {
        Node root = null;
        for (String word : testWords) {
            root = addItem(root, new Node(word));
        }

        printTree(root);
    }

    public static Node addItemsToTree(ArrayList<Node> items) throws IOException {
        long timeStarted = System.currentTimeMillis();
        Node root = null;
        for (Node thisNode : items) {
            root = addItem(root, thisNode);
        }

        long timeFinished = System.currentTimeMillis();
        System.out.println(items.size() + " items was added in " + (timeFinished - timeStarted) + " ms");
        return root;
    }

    public static void printTree(Node root) {
        System.out.println("---------------------------");
        int rootCount = printNode(root, 0);
        System.out.println("Count of items is: " + rootCount);
        System.out.println("===========================");
    }

    public static int printNode(Node thisNode, int count) {
        if (thisNode != null) {
            count++;
            System.out.println(thisNode.getName() + "(" + thisNode.balance + "/" + thisNode.getDepth() + ")=> " + (thisNode.left != null ? thisNode.left.getName() : "-") + "/" + (thisNode.right != null ? thisNode.right.getName() : "-"));
            count = printNode(thisNode.left, count);
            count = printNode(thisNode.right, count);
            return count;
        }
        return count;
    }

    public static ArrayList<Node> getItemFromCsvFile(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/com/company/tests/" + fileName + ".csv"));
        String line;
        ArrayList<Node> allNodes = new ArrayList<Node>();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            Node newNode = new Node(data[0], Integer.parseInt(data[1]));
            allNodes.add(newNode);
        }
        reader.close();
//        System.out.println(allNodes.size() + " items was loaded.");
        return allNodes;
    }
}
