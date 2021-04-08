package com.company;

public class AddressingHashtable<K, V> {
    private final static int TABLE_SIZE = 120000;
//    https://www.algolist.net/Data_structures/Hash_table/Open_addressing

    HashEntry[] table;


    AddressingHashtable() {

        table = new HashEntry[TABLE_SIZE];

        for (int i = 0; i < TABLE_SIZE; i++)

            table[i] = null;

    }


    public Person get(String key) {

        int hash = (h(key) % TABLE_SIZE);

        int initialHash = -1;

        while (hash != initialHash
                && (table[hash] == DeletedEntry.getUniqueDeletedEntry() || table[hash] != null
                && table[hash].getKey() != key)) {

            if (initialHash == -1)
                initialHash = hash;

            hash = (hash + 1) % TABLE_SIZE;
        }

        if (table[hash] == null || hash == initialHash)

            return null;

        else

            return table[hash].getValue();

    }

    public int h(String key) {
        int finalHash = key.length();
        for (int i = 0; i < key.length(); i++) {
            finalHash = finalHash + key.charAt(i);
        }

        return finalHash;
    }

    public void put(String key, Person value) {

        int hash = (h(key) % TABLE_SIZE);

        int initialHash = -1;

        int indexOfDeletedEntry = -1;

        while (hash != initialHash

                && (table[hash] == DeletedEntry.getUniqueDeletedEntry() || table[hash] != null

                && table[hash].getKey() != key)) {

            if (initialHash == -1)

                initialHash = hash;

            if (table[hash] == DeletedEntry.getUniqueDeletedEntry())

                indexOfDeletedEntry = hash;

            hash = (hash + 1) % TABLE_SIZE;

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

        int hash = (h(key) % TABLE_SIZE);

        int initialHash = -1;

        while (hash != initialHash

                && (table[hash] == DeletedEntry.getUniqueDeletedEntry() || table[hash] != null

                && table[hash].getKey() != key)) {

            if (initialHash == -1)

                initialHash = hash;

            hash = (hash + 1) % TABLE_SIZE;

        }

        if (hash != initialHash && table[hash] != null)

            table[hash] = DeletedEntry.getUniqueDeletedEntry();

    }

}