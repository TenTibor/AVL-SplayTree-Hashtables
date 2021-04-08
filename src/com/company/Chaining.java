package com.company;

public class Chaining {
    ChainingNode[] hashTable;
    int size = 0;
    int usedIndexes = 0;
    int itemsInChain = 0;

    public Chaining(int size) {
        this.size = size;
        hashTable = new ChainingNode[size];
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
            hashTable[index] = new ChainingNode(name, age);
            usedIndexes++;

            // if we need resize
            if (usedIndexes > size / 2)
                resizeTable();
        } else if (!hashTable[index].getName().equals(name)) {
            // Check if items is not exist in chain
            for (ChainingNode item : hashTable[index].chaining) {
                if (item.getName().equals(name)) return;
            }
            itemsInChain++;
            hashTable[index].chaining.add(new ChainingNode(name, age));
        }
    }

    public ChainingNode get(String key) {
        ChainingNode itemOnIndex = hashTable[hash(key)];
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
        ChainingNode[] oldData = hashTable;
        ChainingNode[] newHashTable = new ChainingNode[size];
        this.hashTable = newHashTable;

        for (ChainingNode item : oldData) {
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
}
