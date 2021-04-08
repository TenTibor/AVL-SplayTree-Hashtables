package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        ArrayList<Person> importedItems = getItemFromCsvFile("test100k");

        System.out.println("====================");
        System.out.println("Trees: AVL");
        MyAVL myAVL = new MyAVL();
        insertItemsToAVL(myAVL, importedItems);
        AVLSearchManyItems(myAVL, importedItems);

        System.out.println("====================");
        System.out.println("Trees: Splay Tree");
        SplayTree splayTree = new SplayTree();
        insertItemsToSplayTree(splayTree, importedItems);
        SplayTreeSearchManyItems(splayTree, importedItems);

        System.out.println("====================");
        System.out.println("Hashtable: Chaining");
        MyHashTable myHashTable = new MyHashTable(importedItems.size());
        myHashTable.addManyItems(importedItems);
        hashTableSearchManyItems(myHashTable, importedItems);

        System.out.println("====================");
        System.out.println("Hashtable: Open addressing");
        AddressingHashtable<String, Person> addressingHashtable = importItemsFromFile(importedItems);
        takenHashTableSearchManyItems(addressingHashtable, importedItems);

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

    // insert many items to AVL
    public static void insertItemsToAVL(MyAVL myAVL, ArrayList<Person> items) throws IOException {
        long timeStarted = System.currentTimeMillis();

        NodeForAVL root = null;
        for (Person thisNode : items) {
            root = myAVL.addItem(root, new NodeForAVL(thisNode.name, thisNode.age));
        }

        long timeFinished = System.currentTimeMillis();
        System.out.println(items.size() + " items was added in " + (timeFinished - timeStarted) + " ms");
        myAVL.root = root;
    }

    // search many items in AVL
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

    // insert many items to SplayTree
    public static void insertItemsToSplayTree(SplayTree myAVL, ArrayList<Person> items) throws IOException {
        long timeStarted = System.currentTimeMillis();

        for (Person thisNode : items) {
            myAVL.insert(new Person(thisNode.name, thisNode.age));
        }

        long timeFinished = System.currentTimeMillis();
        System.out.println(items.size() + " items was added in " + (timeFinished - timeStarted) + " ms");
    }

    // search many items in Splay Tree
    public static void SplayTreeSearchManyItems(SplayTree avl, ArrayList<Person> items) {
        long timeStarted = System.currentTimeMillis();

        // Search any item in range
        int foundItems = 0;
        int searchedItems = 0;
        for (int i = 0; i < items.size(); i++) {
            searchedItems++;
            if (avl.searchTree(items.get(i).name) != null) foundItems++;
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println(foundItems + "(/" + searchedItems + ") items was found in: " + (timeFinished - timeStarted) + " ms");
    }

    // search many items in my hashtable
    public static void hashTableSearchManyItems(MyHashTable table, ArrayList<Person> importedItems) {
        long timeStarted = System.currentTimeMillis();
        int searchedItems = 0;
        int foundItems = 0;
        for (int i = 0; i < importedItems.size(); i++) {
            searchedItems++;
            String searchedName = importedItems.get(i).name;
            if (table.get(searchedName).getName().equals(searchedName)) foundItems++;
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println("(" + foundItems + "/" + searchedItems + ") items was found in: " + (timeFinished - timeStarted) + " ms");
    }

    // add many items to taken implementation
    public static AddressingHashtable<String, Person> importItemsFromFile(ArrayList<Person> importedItems) {
        AddressingHashtable<String, Person> hashtable = new AddressingHashtable<>(importedItems.size());

        long timeStarted = System.currentTimeMillis();
        for (Person thisPerson : importedItems) {
            hashtable.put(thisPerson.getName(), new Person(thisPerson.getName(), thisPerson.getAge()));
        }

        long timeFinished = System.currentTimeMillis();
        System.out.println(importedItems.size() + " items was added in: " + (timeFinished - timeStarted) + " ms");
        return hashtable;
    }

    // search many items in taken implementation
    private static void takenHashTableSearchManyItems(AddressingHashtable<String, Person> table, ArrayList<Person> importedItems) {
        long timeStarted = System.currentTimeMillis();
        int searchedItems = 0;
        int foundItems = 0;
        for (int i = 0; i < importedItems.size(); i++) {
            searchedItems++;
            String searchedName = importedItems.get(i).name;
            if (table.get(searchedName) == null) continue;
            if (table.get(searchedName).getName().equals(searchedName)) foundItems++;
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println("(" + foundItems + "/" + searchedItems + ") items was found in: " + (timeFinished - timeStarted) + " ms");
    }
}
