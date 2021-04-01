package com.company;

//import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {


//        String[] testWords = {"1", "10", "5"};
//        String[] testWords = {"d", "a", "f", "g", "h"};
//        String[] testWords = {"s", "a", "x", "d"};
//        String[] testWords = {"s", "a", "x", "d", "b"};
//        String[] testWords = {"Zoe", "Denis", "Sienna", "Angelica", "Hayden", "Macy", "Abbey", "Javier", "Camila", "David"}; // 2 people missing
        String[] testWords = {"Zoe", "Denis", "Sienna", "Angelica", "Hayden", "Macy"}; // macy is on another side
//        String[] testWords = {"s", "a", "x", "d", "b", "c", "g", "h", "j", "z"};
//        String[] testWords = {"s", "a", "x", "d", "b", "c"};
//        String[] testWords = {"s", "a", "x", "d", "b", "c", "g"}; // this works
//        String[] testWords = {"s", "b", "x", "a", "d", "c"};
//        String[] testWords = {"a", "c", "b"}; // right&left
//        String[] testWords = {"c", "a", "b"}; // left&right
//        String[] testWords = {"a", "b", "c"}; // left
//

//        testStrings(testWords);

        testFile("custom1"); // one person is missing
    }

    public static void testStrings(String[] testWords) throws IOException {
        Node root = null;
        for (String word : testWords) {
            root = addItem(root, new Node(word));
        }

        printTree(root);
//        System.out.println("======================");
//        root = addItem(root, new Node("Macy"));
//        printTree(root);
    }

    public static void testFile(String fileName) throws IOException {
        ArrayList<Node> nodes = getFromCsvFile(fileName);
        Node root = null;
        for (Node thisNode : nodes) {
            root = addItem(root, thisNode);
        }

        printTree(root);

        root = addItem(root, new Node("Karla Griffiths", 21));
        printTree(root);

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

        return addTo.rebalanced();
    }

    public static void printTree(Node root) {
        System.out.println("============================");
        int rootCount = printNode(root, 0);
        System.out.println("Count of items is: " + rootCount);
        System.out.println("============================");
    }

    public static int printNode(Node thisNode, int count) {
        if (thisNode != null) {
            count++;
            System.out.println(thisNode.getName() + "(" + thisNode.balance + "/" + thisNode.getDepth() + ")->" + (thisNode.left != null ? thisNode.left.getName() : "[none]") + "/" + (thisNode.right != null ? thisNode.right.getName() : "[none]"));
            count = printNode(thisNode.left, count);
            count = printNode(thisNode.right, count);
            return count;
        }
        return count;
    }

    public static ArrayList<Node> getFromCsvFile(String fileName) throws IOException {

        BufferedReader reader = new BufferedReader(new FileReader("src/com/company/tests/" + fileName + ".csv"));
        String line;
        ArrayList<Node> allNodes = new ArrayList<Node>();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            Node newNode = new Node(data[0], Integer.parseInt(data[1]));
            allNodes.add(newNode);
        }
        reader.close();
        System.out.println(allNodes.size() + " items was loaded.");
        return allNodes;
    }
}
