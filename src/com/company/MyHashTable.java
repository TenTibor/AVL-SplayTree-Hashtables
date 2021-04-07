package com.company;

import java.util.ArrayList;

public class MyHashTable {
    NodeForHash[] hashTable;
    int size = 0;
    int usedIndexes = 0;
    int itemsInChain = 0;

    public MyHashTable(int size) {
        this.size = size;
        hashTable = new NodeForHash[size];
//        for (int i = 0; i < size; i++) {
//            hashTable.add(null);
//        }
    }

    public int hash(String key) {
        // Calculate hash by sum of letters.. But modulo size for staying in list capacity
        int finalHash = key.length();
        for (int i = 0; i < key.length(); i++) {
            finalHash = finalHash * key.charAt(0) + key.charAt(i) + i * finalHash;
        }
//        System.out.println(Math.abs11(finalHash % size));

        return Math.abs(finalHash % size);
    }

    public void insert(String name, int age) {
        int index = hash(name);
        if (hashTable[index] == null) {
            hashTable[index] = new NodeForHash(name, age);
            usedIndexes++;

            // if we need resize
            if (usedIndexes > size / 2)
                resizeTable();
        } else if (!hashTable[index].getName().equals(name)) {
            // chaining
            itemsInChain++;
            hashTable[index].chaining.add(new NodeForHash(name, age));
        }
    }

    public NodeForHash get(String key) {
        NodeForHash itemOnIndex = hashTable[hash(key)];
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

        System.out.println("Resize with: " + bonusCapacity);
//        for (int i = 0; i < bonusCapacity; i++) {
//            hashTable.add(null);
//        }
//        this.size += bonusCapacity;
    }

    public void addItemsToTree(ArrayList<Person> importedItems) {
        long timeStarted = System.currentTimeMillis();
        for (Person thisPerson : importedItems) {
            insert(thisPerson.name, thisPerson.age);
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println(importedItems.size() + " items was added in: " + (timeFinished - timeStarted) + " ms");
        System.out.println("Unique keys: " + usedIndexes);
        System.out.println("Duplicated keys: " + itemsInChain);
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
