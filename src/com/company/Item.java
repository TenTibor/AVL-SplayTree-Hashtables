package com.company;

public class Item {
    String name = null; // ID
    int depth = 1;
    Item left = null;
    Item right = null;

    int rotated = 0;
    // +1 -> rotated to right
    // -1 -> rotated to left

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

        // Need rotation?
        if (left == null && (right != null && right.getDepth() > 1)) {
            // Right need rotate
            System.out.println("Right need to rotate:" + name);
            right.left = this;
            this.rotated = 1;
        } else if (right == null && (left != null && left.getDepth() > 1)) {
            // Left need rotate
            System.out.println("Left need to rotate:" + name);
            left.right = this;
            rotated = -1;
        } else if ((left != null && right != null) && (left.rotated == 0 && right.rotated == 0) && Math.abs(left.getDepth() - right.getDepth()) > 1) {
            // One side need rotate
            System.out.println("Some side need to rotate: " + name + "//sides:" + left.getDepth() + "/" + right.getDepth());
        }
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

    public void decreaseDepth() {
        this.depth--;
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
