package com.company;

import java.util.ArrayList;

public class MyHashTable {
    ArrayList<NodeForHash> hashTable;
    int size = 0;
    int usedIndexes = 0;
    int itemsInChain = 0;

    public MyHashTable(int size) {
        this.size = size;
        hashTable = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            hashTable.add(null);
        }
    }

    public int hashIndex(String key) {
        // Calculate hash by sum of letters.. But modulo size for staying in list capacity
        int finalHash = 0;
        for (int i = 0; i < key.length(); i++) {
            finalHash = finalHash + key.charAt(i);
        }
//        System.out.println("hash:" + finalHash % size);
        return finalHash % size;
    }

    public void insert(String name, int age) {
        int index = hashIndex(name);
        if (hashTable.get(index) == null) {
            hashTable.set(index, new NodeForHash(name, age));
//            freeIndexes--;
            usedIndexes++;
//            System.out.println((freeIndexes * 100) / size);
//            if ((freeIndexes * 100) / size < 25)
//                System.out.println((freeIndexes * 100) / size);
        } else {
            // chaining
            itemsInChain++;
            hashTable.get(index).chaining.add(new NodeForHash(name, age));
        }
    }

    public NodeForHash get(String key) {
        NodeForHash itemOnIndex = hashTable.get(hashIndex(key));
        if (itemOnIndex.chaining.size() == 0 || itemOnIndex.getName().equals(key))
            return itemOnIndex;
        else {
            int indexOfChaining = 0;
            while (!itemOnIndex.chaining.get(indexOfChaining).getName().equals(key)) {
                indexOfChaining++;
            }
            return itemOnIndex.chaining.get(indexOfChaining);
        }
    }

    public void resizeTable() {
        int bonusCapacity = size / 3;
        System.out.println(bonusCapacity);
    }

    public void addItemsToTree(ArrayList<Person> importedItems) {
        long timeStarted = System.currentTimeMillis();
        for (Person thisPerson : importedItems) {
            insert(thisPerson.name, thisPerson.age);
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println(importedItems.size() + " items was added in: " + (timeFinished - timeStarted) + " ms");
        System.out.println("Used indexes: " + usedIndexes);
        System.out.println("Items in chains: " + itemsInChain);
    }

    public void searchManyItems(ArrayList<Person> importedItems) {
        long timeStarted = System.currentTimeMillis();
        int searchedItems = 0;
        int foundItems = 0;
        for (int i = 0; i < importedItems.size(); i += 2) {
            searchedItems++;
            String searchedName = importedItems.get(i).name;
            if (get(searchedName).getName().equals(searchedName)) foundItems++;
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println("(" + foundItems + "/" + searchedItems + ") items was found in: " + (timeFinished - timeStarted) + " ms");
    }
}