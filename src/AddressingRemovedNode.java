package com.company;

public class AddressingRemovedNode extends AddressingNode {

    private static AddressingRemovedNode entry = null;

    private AddressingRemovedNode() {
        super(null, null);
    }

    public static AddressingRemovedNode getUniqueDeletedEntry() {
        if (entry == null)
            entry = new AddressingRemovedNode();
        return entry;
    }
}
