package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Node> importedItems = getItemFromCsvFile("test100k");

        // my implementation
        System.out.println("====================");
        System.out.println("AVL: Moja implementácia");
        MyAVL myAVL = new MyAVL();
        myAVL.addItemsToTree(importedItems);
        myAVL.searchManyItems(importedItems);

        System.out.println("====================");
        System.out.println("AVL: Cudzia implementácia");
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
