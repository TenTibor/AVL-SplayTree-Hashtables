package com.company;

public class Node {
    String name = null; // ID
    int age = 0;

    int depth = 1;
    int balance = 0;
    Node left = null;
    Node right = null;
    boolean debug = false; // just for testing purpose

    public Node getBiggerItem() {
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

    public Node rebalanced() {
        // Refresh depth
        Node biggerNode = this.getBiggerItem();
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
            Node top = left;
            Node bottom = left.right;
            Node topLeft = left.left;
            Node bottomLeft = left.right.left;
            Node bottomRight = left.right.right;

            // Switch nodes
            left = bottom;
            left.left = top;
//            left.left.right = bottomRight;
//            if (bottomRight == null) left.left.left = bottomLeft;

            left.left.right = bottomLeft;
            left.right = bottomRight;
//            left.left.left = topLeft;

//            left = bottom;
//            left.left = top;
//            left.right = bottomRight;
//            left.left.left = topLeft;
//            left.left.right = bottomLeft;

//            Node savedNode = left.right.left;
//            if (left.right.right == null) {
//                Node savedNodeFromRight = left.right.right;
//            };
//            left.right.left = left;
//            Node tempNode = left.right;
//            left.right = savedNode;
//            left = tempNode;
        }

        // Right and left rotation after this
        if (balance < -1 && right != null && right.balance > 0) {
            if (debug) System.out.println(this.name + " need right-left rotation");

            // left rotation of left node
            right.decreaseDepth();
            right.left.increaseDepth();

            // Save all nodes
            Node top = right;
            Node bottom = right.left;
            Node topLeft = right.right;
            Node bottomLeft = right.left.left;
            Node bottomRight = right.left.right;

            // Switch nodes
            right = bottom;
            right.right = top;

            right.right.left = bottomRight;
            right.left = bottomLeft;


//            right.right.right = bottomRight;
//            if (bottomRight == null) right.right.left = bottomLeft;
//            right = bottom;
//            right.right = top;
//            right.left = bottomRight;
//            right.right.left = topLeft;
//            right.right.right = bottomLeft;

//            Node savedNode = right.left.right;
//            if (savedNode == null) savedNode = right.left.left;
//            right.left.right = right;
//            Node tempNode = right.left;
//            right.left = savedNode;
//            right = tempNode;
        }

        // Right rotation
        if (balance > 1) {
            if (debug) System.out.println(this.name + " need right rotation");

            this.decreaseDepth(2);
            balance = 0;
            Node tempNodeRight = left.right;
            left.right = this;
            Node tempNode = left;
            left = tempNodeRight;
            return tempNode;
        }

        // Left rotation
        if (balance < -1) {
            if (debug) System.out.println(this.name + " need left rotation");

            this.decreaseDepth(2);
            balance = 0;
            Node tempNodeLeft = right.left;
            right.left = this;
            Node tempNode = right;
            right = tempNodeLeft;
            return tempNode;
        }

        return this;
    }

    public Node(String name) {
        this.name = name;
    }

    public Node(String name, int age) {
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

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
