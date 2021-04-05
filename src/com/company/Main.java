package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Person> importedItems = getItemFromCsvFile("test100k");

        // my implementation
        System.out.println("====================");
        System.out.println("AVL: Moja implementácia");
        MyAVL myAVL = new MyAVL();
        myAVL.addItemsToTree(importedItems);
        myAVL.searchManyItems(importedItems);

        System.out.println("====================");
        System.out.println("Hashovanie: Moja implementácia");
        MyHashTable hashTable = new MyHashTable(200000);
        hashTable.addItemsToTree(importedItems);
        hashTable.searchManyItems(importedItems);
    }

    public static ArrayList<Person> getItemFromCsvFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/com/company/tests/" + fileName + ".csv"));
        String line;
        ArrayList<Person> allNodes = new ArrayList<Person>();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            Person newNode = new Person(data[0], Integer.parseInt(data[1]));
            allNodes.add(newNode);
        }
        reader.close();
//        System.out.println(allNodes.size() + " items was loaded.");
        return allNodes;
    }
}
