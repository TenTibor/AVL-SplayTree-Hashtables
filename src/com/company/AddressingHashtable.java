package com.company;

public class AddressingHashtable<K, V> {
    private int tableSize = 0;
//    https://www.algolist.net/Data_structures/Hash_table/Open_addressing

    HashEntry[] table;


    AddressingHashtable(int size) {
        tableSize = size;
        table = new HashEntry[tableSize];

        for (int i = 0; i < tableSize; i++)

            table[i] = null;

    }


    public Person get(String key) {

        int hash = (h(key) % tableSize);

        int initialHash = -1;

        while (hash != initialHash
                && (table[hash] == DeletedEntry.getUniqueDeletedEntry() || table[hash] != null
                && table[hash].getKey() != key)) {

            if (initialHash == -1)
                initialHash = hash;

            hash = (hash + 1) % tableSize;
        }

        if (table[hash] == null || hash == initialHash)

            return null;

        else

            return table[hash].getValue();

    }

    public int h(String key) {
        int finalHash = key.length();
        for (int i = 0; i < key.length(); i++) {
            finalHash = finalHash * key.charAt(0) + key.charAt(i) + i * finalHash;
        }

        return Math.abs(finalHash);
    }

    public void put(String key, Person value) {

        int hash = (h(key) % tableSize);

        int initialHash = -1;

        int indexOfDeletedEntry = -1;
        while (hash != initialHash

                && (table[hash] == DeletedEntry.getUniqueDeletedEntry() || table[hash] != null

                && table[hash].getKey() != key)) {

            if (initialHash == -1)

                initialHash = hash;

            if (table[hash] == DeletedEntry.getUniqueDeletedEntry())

                indexOfDeletedEntry = hash;

            hash = (hash + 1) % tableSize;
        }
        if ((table[hash] == null || hash == initialHash)

                && indexOfDeletedEntry != -1)

            table[indexOfDeletedEntry] = new HashEntry(key, value);

        else if (initialHash != hash)

            if (table[hash] != DeletedEntry.getUniqueDeletedEntry()

                    && table[hash] != null && table[hash].getKey() == key)

                table[hash].setValue(value);

            else

                table[hash] = new HashEntry(key, value);

    }


    public void remove(String key) {

        int hash = (h(key) % tableSize);

        int initialHash = -1;

        while (hash != initialHash

                && (table[hash] == DeletedEntry.getUniqueDeletedEntry() || table[hash] != null

                && table[hash].getKey() != key)) {

            if (initialHash == -1)

                initialHash = hash;

            hash = (hash + 1) % tableSize;

        }

        if (hash != initialHash && table[hash] != null)

            table[hash] = DeletedEntry.getUniqueDeletedEntry();

    }

}