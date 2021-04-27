package com.company;

class SplayTreeNode {
    Person data; // holds the key
    SplayTreeNode parent; // pointer to the parent
    SplayTreeNode left; // pointer to left child
    SplayTreeNode right; // pointer to right child

    public SplayTreeNode(Person data) {
        this.data = data;
        this.parent = null;
        this.left = null;
        this.right = null;
    }
}
