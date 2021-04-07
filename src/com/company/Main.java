package com.company;

import java.io.*;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Person> importedItems = getItemFromCsvFile("test100k");

        System.out.println("====================");
        System.out.println("AVL: Moja implementácia");
        MyAVL myAVL = new MyAVL();
        myAVL.addItemsToTree(importedItems);
        AVLSearchManyItems(myAVL, importedItems);

        System.out.println("====================");
        System.out.println("Hashovanie: Moja implementácia");
        MyHashTable myHashTable = new MyHashTable(100000);
        myHashTable.addManyItems(importedItems);
        hashTableSearchManyItems(myHashTable, importedItems);
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
        return allNodes;
    }

    public static void hashTableSearchManyItems(MyHashTable table, ArrayList<Person> importedItems) {
        long timeStarted = System.currentTimeMillis();
        int searchedItems = 0;
        int foundItems = 0;
        for (int i = 0; i < importedItems.size(); i += 2) {
            searchedItems++;
            String searchedName = importedItems.get(i).name;
            if (table.get(searchedName).getName().equals(searchedName)) foundItems++;
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println("(" + foundItems + "/" + searchedItems + ") items was found in: " + (timeFinished - timeStarted) + " ms");
    }

    public static void AVLSearchManyItems(MyAVL avl, ArrayList<Person> items) {
        long timeStarted = System.currentTimeMillis();

        // Search any item in range
        int foundItems = 0;
        int searchedItems = 0;
        for (int i = 0; i < items.size(); i += 2) {
            searchedItems++;
            if (avl.findItem(items.get(i).name, avl.root)) foundItems++;
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println(foundItems + "(/" + searchedItems + ") items was found in: " + (timeFinished - timeStarted) + " ms");
    }
}
