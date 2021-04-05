package com.company;

public class NodeForAVL {
    // data
    String name = null; // ID
    int age = 0;

    // attributes for working AVL
    int depth = 1;
    int balance = 0;
    NodeForAVL left = null;
    NodeForAVL right = null;

    // just for testing purpose
    boolean debug = false;

    public NodeForAVL getBiggerItem() {
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

    public NodeForAVL rebalanced() {
        // Refresh depth
        NodeForAVL biggerNode = this.getBiggerItem();
        if (biggerNode != null)
            this.setDepth(biggerNode.getDepth() + 1);

        // Refresh balance
        balance = 0;
        if (left != null) balance += left.depth;
        if (right != null) balance -= right.depth;

        // Left and right rotation after this
        if (balance > 1 && left != null && left.balance < 0) {
            if (debug)
                System.out.println(this.name + " need left-right rotation with: " + left.getName() + '/' + left.right.getName());

            // left rotation of left node
            left.decreaseDepth();
            left.right.increaseDepth();

            // Save all nodes
            NodeForAVL top = left;
            NodeForAVL bottom = left.right;
            NodeForAVL bottomLeft = left.right.left;
            NodeForAVL bottomRight = left.right.right;

            // Switch nodes
            left = bottom;
            left.left = top;

            left.left.right = bottomLeft;
            left.right = bottomRight;
        }

        // Right and left rotation after this
        if (balance < -1 && right != null && right.balance > 0) {
            if (debug) System.out.println(this.name + " need right-left rotation");

            // left rotation of left node
            right.decreaseDepth();
            right.left.increaseDepth();

            // Save all nodes
            NodeForAVL top = right;
            NodeForAVL bottom = right.left;
            NodeForAVL bottomLeft = right.left.left;
            NodeForAVL bottomRight = right.left.right;

            // Switch nodes
            right = bottom;
            right.right = top;

            right.right.left = bottomRight;
            right.left = bottomLeft;
        }

        // Right rotation
        if (balance > 1) {
            if (debug) System.out.println(this.name + " need right rotation");

            this.decreaseDepth(2);
            balance = 0;
            NodeForAVL tempNodeRight = left.right;
            left.right = this;
            NodeForAVL tempNode = left;
            left = tempNodeRight;
            return tempNode;
        }

        // Left rotation
        if (balance < -1) {
            if (debug) System.out.println(this.name + " need left rotation");

            this.decreaseDepth(2);
            balance = 0;
            NodeForAVL tempNodeLeft = right.left;
            right.left = this;
            NodeForAVL tempNode = right;
            right = tempNodeLeft;
            return tempNode;
        }

        return this;
    }

    public NodeForAVL(String name, int age) {
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

    public void decreaseDepth() {
        this.depth--;
    }

    public void decreaseDepth(int times) {
        this.depth = this.depth - times;
    }

    public NodeForAVL getRight() {
        return right;
    }

    public NodeForAVL getLeft() {
        return left;
    }

    public void setRight(NodeForAVL right) {
        this.right = right;
    }

    public String getName() {
        return name;
    }

}
