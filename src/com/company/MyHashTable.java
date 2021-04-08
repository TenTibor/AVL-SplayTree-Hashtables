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
    }

    public int hash(String key) {
        int finalHash = key.length();
        for (int i = 0; i < key.length(); i++) {
            finalHash = finalHash * key.charAt(0) + key.charAt(i) + i * finalHash;
        }

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
            // Check if items is not exist in chain
            for (NodeForHash item : hashTable[index].chaining) {
                if (item.getName().equals(name)) return;
            }
            itemsInChain++;
            hashTable[index].chaining.add(new NodeForHash(name, age));
        }
    }

    public NodeForHash get(String key) {
        NodeForHash itemOnIndex = hashTable[hash(key)];
        if (itemOnIndex == null) return null;
        if (itemOnIndex.getName().equals(key))
            return itemOnIndex;
        else if (itemOnIndex.chaining.size() > 0) {
            int indexOfChaining = 0;
            while (!itemOnIndex.chaining.get(indexOfChaining).getName().equals(key)) {
                indexOfChaining++;
            }
            return itemOnIndex.chaining.get(indexOfChaining);
        }
        return null;
    }

    public void resizeTable() {
        int bonusCapacity = size / 2;
        this.size = size + bonusCapacity;
        NodeForHash[] oldData = hashTable;
        NodeForHash[] newHashTable = new NodeForHash[size];
        this.hashTable = newHashTable;

        for (NodeForHash item : oldData) {
            if (item != null) {
                if (item.chaining.size() > 0) {
                    for (int i = 0; i < item.chaining.size(); i++) {
                        insert(item.chaining.get(i).getName(), item.chaining.get(i).getAge());
                    }
                }
                insert(item.getName(), item.getAge());
            }
        }
    }

    public void addManyItems(ArrayList<Person> importedItems) {
        long timeStarted = System.currentTimeMillis();
        for (Person thisPerson : importedItems) {
            insert(thisPerson.name, thisPerson.age);
        }
        long timeFinished = System.currentTimeMillis();
        System.out.println(importedItems.size() + " items was added in: " + (timeFinished - timeStarted) + " ms");
        System.out.println("Unique keys: " + usedIndexes);
        System.out.println("Duplicated keys: " + itemsInChain);
    }
}
