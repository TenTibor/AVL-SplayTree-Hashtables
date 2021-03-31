package com.company;

public class Node {
    String name = null; // ID
    int depth = 1;
    int balance = 0;
    Node left = null;
    Node right = null;

    int rotated = 0;
    // +1 -> rotated to right
    // -1 -> rotated to left

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

//TODO depth is wrong and thats why its bad if
//        if (left != null) System.out.println(getName() + ": " + balance + "/" + left.balance);
//        if (left != null) System.out.println(name + ":" + balance + "-" + depth + ", LB:" + left.balance);
        // Left and right rotation after this
        if (balance > 1 && left != null && left.balance < 0) {
            System.out.println(this.name + " need left-right rotation with: " + left.getName() + '/' + left.right.getName());

            // left rotation of left node
            left.decreaseDepth();
            left.right.increaseDepth();
            Node savedNode = left.right.left;
            left.right.left = left;
            Node tempNode = left.right;
//            tempNode.increaseDepth();
            left.right = savedNode;
            left = tempNode;
        }

        // Right and left rotation after this
        if (balance < -1 && right != null && right.balance > 0) {
            System.out.println(this.name + " need right-left rotation");

            // left rotation of left node
            right.decreaseDepth();
            right.left.increaseDepth();
            Node savedNode = right.left.right;
            right.left.right = right;
            Node tempNode = right.left;
//            tempNode.increaseDepth();
            right.left = savedNode;
            right = tempNode;
        }

        // Right rotation
        if (balance > 1) {
            System.out.println(this.name + " need right rotation");

            this.decreaseDepth(2);
            balance = 0;
            left.right = this;
            Node tempNode = left;
            left = null;
            return tempNode;
        }

        // Left rotation
        if (balance < -1) {
            System.out.println(this.name + " need left rotation");

            this.decreaseDepth(2);
            balance = 0;
            right.left = this;
//            right.increaseDepth();
            Node tempNode = right;
            right = null;
            return tempNode;
        }

        return this;
        // Need rotation?
        // zigzag wild rotation
       /* if (left == null && (right != null && right.getDepth() > 1) && right.left != null) {
            right.left.left = this;
            right.left.right = right;
            this.rotated = 2;
            System.out.println("Right zigzag need to rotate:" + name);
        } else if (left == null && (right != null && right.getDepth() > 1)) {
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
            rotated = -3;
//            Item blockToReplace = left.right;
//            Item existNode = block ToReplace.left;
//            if (existNode == null) existNode = blockToReplace.right;
//
//            if (this.getName().compareTo(existNode.getName()) < 0) {
//                this.right = existNode;
//                this.left = existNode;
//            } else if (this.getName().compareTo(existNode.getName()) > 0) {
//                this.right = existNode;//
//                this.left = existNode;
//            }

            System.out.println("Some side need to rotate: " + name + "//sides:" + left.getDepth() + "/" + right.getDepth());
        }*/
    }

    public Node(String name) {
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
