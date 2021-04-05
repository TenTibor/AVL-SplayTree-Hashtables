package com.company;

import java.util.ArrayList;

public class MyHashTable {
    ArrayList<PersonForHash> hashTable;
    int size = 0;

    public MyHashTable(int size) {
        this.size = size;
        hashTable = new ArrayList<>(size);
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
        System.out.println("hash:" + finalHash % size);
        return finalHash % size;
    }

    public void insert(String name, int age) {
        int index = hashIndex(name);
        if (hashTable.get(index) == null) {
            hashTable.set(index, new PersonForHash(name, age));
        } else {
            System.out.println("Chaining is happening");
            // chaining
            hashTable.get(index).chaining.add(new PersonForHash(name, age));
        }
    }

    public void get(String key) {
        PersonForHash itemOnIndex = hashTable.get(hashIndex(key));
        if (itemOnIndex.chaining.size() == 0 || itemOnIndex.getName().equals(key))
            itemOnIndex.print();
        else {
            int indexOfChaining = 0;
            while (!itemOnIndex.chaining.get(indexOfChaining).getName().equals(key)){
                indexOfChaining++;
            }
            itemOnIndex.chaining.get(indexOfChaining).print();
        }
    }

    public void addItemsToTree(ArrayList<Node> importedItems) {
    }
}
