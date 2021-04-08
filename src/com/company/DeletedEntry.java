package com.company;

public class DeletedEntry extends HashEntry {

    private static DeletedEntry entry = null;


    private DeletedEntry() {

        super(null, null);

    }


    public static DeletedEntry getUniqueDeletedEntry() {

        if (entry == null)

            entry = new DeletedEntry();

        return entry;

    }

}
