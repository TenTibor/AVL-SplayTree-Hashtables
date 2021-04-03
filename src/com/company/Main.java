package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        // my implementation
        ArrayList<Node> importedItems = getItemFromCsvFile("test300k");
        Node root = addItemsToTree(importedItems);
        findNode("Lynn Jefferson", root);
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

    public static void findNode(String findThisName, Node root) {
        long timeStarted = System.currentTimeMillis();
        System.out.println(timeStarted);

        Node foundNode = searching(findThisName, root);
        if (foundNode == null)
            System.out.println("Item was not found");
        else {
            long timeFinished = System.currentTimeMillis();
            System.out.println(timeFinished);
            System.out.println("Item: " + foundNode.getItem() + " was found in " + (timeFinished - timeStarted) + " ms");
        }
    }

    public static Node searching(String findThisName, Node findHere) {
        Node foundNode = null;
        if (findHere.getName().equals(findThisName)) foundNode = findHere;

        else if (findHere.getName().compareTo(findThisName) < 0 && findHere.getRight() != null) {
            foundNode = searching(findThisName, findHere.getRight());
        } else if (findHere.getName().compareTo(findThisName) > 0 && findHere.getLeft() != null) {
            foundNode = searching(findThisName, findHere.getLeft());
        }

        return foundNode;
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
