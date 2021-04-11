package com.company;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {

        // Load all records from CVS file.. All files are in directory "tests"
        ArrayList<Person> importedItems = getItemFromCsvFile("test100k");

        // Start tests for all implementations
        System.out.println("=====================================");
        System.out.println("Trees: AVL");
        AVL avl = new AVL();
        AVLInsertItems(avl, importedItems);
        AVLSearchItems(avl, importedItems);

        System.out.println("=====================================");
        System.out.println("Trees: Splay Tree");
        SplayTree splayTree = new SplayTree();
        splayTreeInsertItems(splayTree, importedItems);
        splayTreeSearchItems(splayTree, importedItems);

        System.out.println("=====================================");
        System.out.println("Hashtable: Chaining");
        Chaining chaining = new Chaining(importedItems.size());
        chainingInsertItems(chaining, importedItems);
        chainingSearchItems(chaining, importedItems);

        System.out.println("=====================================");
        System.out.println("Hashtable: Open addressing");
        Addressing<String, Person> addressing = new Addressing<>(importedItems.size());
        addressingInsertItems(addressing, importedItems);
        addressingSearchItems(addressing, importedItems);
    }

    public static ArrayList<Person> getItemFromCsvFile(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("src/com/company/tests/" + fileName + ".csv"));
        String line;
        ArrayList<Person> allNodes = new ArrayList<>();
        while ((line = reader.readLine()) != null) {
            String[] data = line.split(",");
            Person newNode = new Person(data[0], Integer.parseInt(data[1]));
            allNodes.add(newNode);
        }
        reader.close();
        return allNodes;
    }

    // insert many items to AVL
    public static void AVLInsertItems(AVL AVL, ArrayList<Person> items) {
        long timeStarted = System.currentTimeMillis();

        AVLNode root = null;
        for (Person thisNode : items) {
            root = AVL.addItem(root, new AVLNode(thisNode.name, thisNode.age));
        }

        long timeFinished = System.currentTimeMillis();
        System.out.println(items.size() + " items was added in " + (timeFinished - timeStarted) + " ms");
        AVL.root = root;
    }

    // search many items in AVL
    public static void AVLSearchItems(AVL tree, ArrayList<Person> items) {
        long timeStarted = System.currentTimeMillis();

        // Search any item in range
        int foundItems = 0;
        int searchedItems = 0;
        for (Person item : items) {
            searchedItems++;
            if (tree.findItem(item.name, tree.root)) foundItems++;
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println(foundItems + "(/" + searchedItems + ") items was found in: " + (timeFinished - timeStarted) + " ms");
    }

    // insert many items to SplayTree
    public static void splayTreeInsertItems(SplayTree tree, ArrayList<Person> items) {
        long timeStarted = System.currentTimeMillis();

        for (Person thisNode : items) {
            tree.insert(new Person(thisNode.name, thisNode.age));
        }

        long timeFinished = System.currentTimeMillis();
        System.out.println(items.size() + " items was added in " + (timeFinished - timeStarted) + " ms");
    }

    // search many items in Splay Tree
    public static void splayTreeSearchItems(SplayTree tree, ArrayList<Person> items) {
        long timeStarted = System.currentTimeMillis();

        // Search any item in range
        int foundItems = 0;
        int searchedItems = 0;
        for (Person item : items) {
            searchedItems++;
            if (tree.searchTree(item.name) != null) foundItems++;
            else System.out.println(item.name);
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println(foundItems + "(/" + searchedItems + ") items was found in: " + (timeFinished - timeStarted) + " ms");
    }

    // insert many items to chaining hashtable
    public static void chainingInsertItems(Chaining chaining, ArrayList<Person> importedItems) {
        long timeStarted = System.currentTimeMillis();
        for (Person thisPerson : importedItems) {
            chaining.insert(thisPerson.name, thisPerson);
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println(importedItems.size() + " items was added in: " + (timeFinished - timeStarted) + " ms");
//        System.out.println("Unique keys: " + chaining.usedIndexes);
//        System.out.println("Duplicated keys: " + chaining.itemsInChain);
    }

    // search many items in chaining hashtable
    public static void chainingSearchItems(Chaining table, ArrayList<Person> importedItems) {
        long timeStarted = System.currentTimeMillis();
        int searchedItems = 0;
        int foundItems = 0;
        for (Person importedItem : importedItems) {
            searchedItems++;
            String searchedName = importedItem.name;
            if (table.get(searchedName).getName().equals(searchedName)) foundItems++;
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println("(" + foundItems + "/" + searchedItems + ") items was found in: " + (timeFinished - timeStarted) + " ms");
    }

    // search many items in taken implementation
    private static void addressingSearchItems(Addressing<String, Person> table, ArrayList<Person> importedItems) {
        long timeStarted = System.currentTimeMillis();
        int searchedItems = 0;
        int foundItems = 0;
        for (Person importedItem : importedItems) {
            searchedItems++;
            String searchedName = importedItem.name;
            if (table.get(searchedName) == null) continue;
            if (table.get(searchedName).getName().equals(searchedName)) foundItems++;
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println("(" + foundItems + "/" + searchedItems + ") items was found in: " + (timeFinished - timeStarted) + " ms");
    }

    // add many items to taken implementation
    public static void addressingInsertItems(Addressing<String, Person> table, ArrayList<Person> importedItems) {
        long timeStarted = System.currentTimeMillis();

        for (Person thisPerson : importedItems) {
            table.put(thisPerson.getName(), new Person(thisPerson.getName(), thisPerson.getAge()));
        }

        long timeFinished = System.currentTimeMillis();
        System.out.println(importedItems.size() + " items was added in: " + (timeFinished - timeStarted) + " ms");
    }
}
