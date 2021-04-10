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
//        int finalHash = key.length();
//        for (int i = 0; i < key.length(); i++) {
//            finalHash = finalHash * 31 + key.charAt(i) * key.substring(0, i).hashCode();
//              finalHash = finalHash + key.charAt(i);
//        }
//        System.out.println(key.hashCode());

        return Math.abs(key.hashCode() % size);
    }

    public void insert(String key, Person person) {
        int index = hash(key);
        if (hashTable[index] == null) {
            hashTable[index] = new ChainingNode(person.getName(), person.getAge());
            usedIndexes++;

            // if we need resize
            if (usedIndexes > size / 2)
                resizeTable();
        } else if (!hashTable[index].getName().equals(key)) {
            // Check if items is not exist in chain
            for (ChainingNode item : hashTable[index].chaining) {
                if (item.getName().equals(key)) return;
            }
            itemsInChain++;
            hashTable[index].chaining.add(new ChainingNode(person.getName(), person.getAge()));
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
        usedIndexes = 0;
        itemsInChain = 0;

        for (ChainingNode item : oldData) {
            if (item != null) {
                if (item.chaining.size() > 0) {
                    for (int i = 0; i < item.chaining.size(); i++) {
                        insert(item.chaining.get(i).getName(), new Person(item.chaining.get(i).getName(), item.chaining.get(i).getAge()));
                    }
                }
                insert(item.getName(), new Person(item.getName(), item.getAge()));
            }
        }
    }
}
