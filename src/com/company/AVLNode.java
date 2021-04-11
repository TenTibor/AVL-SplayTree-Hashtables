package com.company;

public class AVLNode {
    // data
    String name = null; // ID
    int age = 0;

    // attributes for working AVL
    int depth = 1;
    int balance = 0;
    AVLNode left = null;
    AVLNode right = null;

    // just for testing purpose.
    // set true to see rotation
    boolean debug = false;

    // Return node with bigger depth
    public AVLNode getBiggerItem() {
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

    // set new balance and depth
    public AVLNode rebalanced() {
        // Refresh depth
        AVLNode biggerNode = this.getBiggerItem();
        if (biggerNode != null)
            this.setDepth(biggerNode.getDepth() + 1);

        // Refresh balance
        balance = 0;
        if (left != null) balance += left.depth;
        if (right != null) balance -= right.depth;

        // Left rotation before right rotation
        if (balance > 1 && left != null && left.balance < 0) {
            if (debug)
                System.out.println(this.name + " had left-right rotation");

            // change depth of switched nodes
            left.decreaseDepth();
            left.right.increaseDepth();

            // Save all nodes
            AVLNode top = left;
            AVLNode bottom = left.right;
            AVLNode bottomLeft = left.right.left;
            AVLNode bottomRight = left.right.right;

            // Switch nodes
            left = bottom;
            left.left = top;

            left.left.right = bottomLeft;
            left.right = bottomRight;
        }

        // Right rotation before left rotation
        if (balance < -1 && right != null && right.balance > 0) {
            if (debug) System.out.println(this.name + " had right-left rotation");

            // change depth of switched nodes
            right.decreaseDepth();
            right.left.increaseDepth();

            // Save all nodes
            AVLNode top = right;
            AVLNode bottom = right.left;
            AVLNode bottomLeft = right.left.left;
            AVLNode bottomRight = right.left.right;

            // Switch nodes
            right = bottom;
            right.right = top;

            right.right.left = bottomRight;
            right.left = bottomLeft;
        }

        // Right rotation
        if (balance > 1) {
            if (debug) System.out.println(this.name + " had right rotation");

            this.decreaseDepth(2);
            balance = 0;
            AVLNode tempNodeRight = left.right;
            left.right = this;
            AVLNode tempNode = left;
            left = tempNodeRight;
            return tempNode;
        }

        // Left rotation
        if (balance < -1) {
            if (debug) System.out.println(this.name + " had left rotation");

            this.decreaseDepth(2);
            balance = 0;
            AVLNode tempNodeLeft = right.left;
            right.left = this;
            AVLNode tempNode = right;
            right = tempNodeLeft;
            return tempNode;
        }

        return this;
    }

    public AVLNode(String name, int age) {
        this.name = name;
        this.age = age;
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

    // decrease depth by one
    public void decreaseDepth() {
        this.depth--;
    }

    // decrease depth more times
    public void decreaseDepth(int times) {
        this.depth = this.depth - times;
    }

    public AVLNode getRight() {
        return right;
    }

    public AVLNode getLeft() {
        return left;
    }

    public void setRight(AVLNode right) {
        this.right = right;
    }

    public String getName() {
        return name;
    }

}
