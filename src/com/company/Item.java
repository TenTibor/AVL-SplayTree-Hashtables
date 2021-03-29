package com.company;

public class Item {
    String name = null; // ID
    int depth = 1;
    Item left = null;
    Item right = null;

    public Item getBiggerItem() {
        if (this.right == null) {
            if (this.left == null) {
                return null;
            }
            return this.left;
        } else if (this.left == null) {
            return this.right;
        }

        if (this.right.getDepth() > this.left.getDepth())
            return this.right;
        else
            return this.left;

    }

    public void refreshDepth() {
        Item biggerItem = this.getBiggerItem();
        if (biggerItem != null)
            this.setDepth(biggerItem.getDepth() + 1);
    }

    public Item(String name) {
        this.name = name;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }

    public void increaseDepth() {
        this.depth++;
    }

    public Item getLeft() {
        return left;
    }

    public void setLeft(Item left) {
        this.left = left;
    }

    public Item getRight() {
        return right;
    }

    public void setRight(Item right) {
        this.right = right;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
