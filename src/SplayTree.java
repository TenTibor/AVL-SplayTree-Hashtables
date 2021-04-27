package com.company;

// Source
// https://algorithmtutor.com/Data-Structures/Tree/Splay-Trees/

public class SplayTree {
    private SplayTreeNode root;

    public SplayTree() {
        root = null;
    }

    private void printHelper(SplayTreeNode currPtr, String indent, boolean last) {
        // print the tree structure on the screen
        if (currPtr != null) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "     ";
            } else {
                System.out.print("L----");
                indent += "|    ";
            }

            System.out.println(currPtr.data);

            printHelper(currPtr.left, indent, false);
            printHelper(currPtr.right, indent, true);
        }
    }

    private SplayTreeNode searchTreeHelper(SplayTreeNode node, String key) {
        if (node == null || key.equals(node.data.getName())) {
            return node;
        }

        if (key.compareTo(node.data.getName()) < 0) {
            return searchTreeHelper(node.left, key);
        }
        return searchTreeHelper(node.right, key);
    }

    private void deleteNodeHelper(SplayTreeNode node, String key) {
        SplayTreeNode x = null;
        SplayTreeNode t = null;
        SplayTreeNode s = null;
        while (node != null) {
            if (node.data.getName().equals(key)) {
                x = node;
            }

            if (node.data.getName().compareTo(key) <= 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (x == null) {
            System.out.println("Couldn't find key in the tree");
            return;
        }
        // split operation
        splay(x);
        if (x.right != null) {
            t = x.right;
            t.parent = null;
        } else {
            t = null;
        }
        s = x;
        s.right = null;
        x = null;

        // join operation
        if (s.left != null) { // remove x
            s.left.parent = null;
        }
        root = join(s.left, t);
        s = null;
    }

    // rotate left at node x
    private void leftRotate(SplayTreeNode x) {
        SplayTreeNode y = x.right;
        x.right = y.left;
        if (y.left != null) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    // rotate right at node x
    private void rightRotate(SplayTreeNode x) {
        SplayTreeNode y = x.left;
        x.left = y.right;
        if (y.right != null) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    // Splaying operation. It moves x to the root of the tree
    private void splay(SplayTreeNode x) {
        while (x.parent != null) {
            if (x.parent.parent == null) {
                if (x == x.parent.left) {
                    // zig rotation
                    rightRotate(x.parent);
                } else {
                    // zag rotation
                    leftRotate(x.parent);
                }
            } else if (x == x.parent.left && x.parent == x.parent.parent.left) {
                // zig-zig rotation
                rightRotate(x.parent.parent);
                rightRotate(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.right) {
                // zag-zag rotation
                leftRotate(x.parent.parent);
                leftRotate(x.parent);
            } else if (x == x.parent.right && x.parent == x.parent.parent.left) {
                // zig-zag rotation
                leftRotate(x.parent);
                rightRotate(x.parent);
            } else {
                // zag-zig rotation
                rightRotate(x.parent);
                leftRotate(x.parent);
            }
        }
    }

    // joins two trees s and t
    private SplayTreeNode join(SplayTreeNode s, SplayTreeNode t) {
        if (s == null) {
            return t;
        }

        if (t == null) {
            return s;
        }

        SplayTreeNode x = maximum(s);
        splay(x);
        x.right = t;
        t.parent = x;
        return x;
    }


    private void preOrderHelper(SplayTreeNode node) {
        if (node != null) {
            System.out.print(node.data + " ");
            preOrderHelper(node.left);
            preOrderHelper(node.right);
        }
    }

    private void inOrderHelper(SplayTreeNode node) {
        if (node != null) {
            inOrderHelper(node.left);
            System.out.print(node.data + " ");
            inOrderHelper(node.right);
        }
    }

    private void postOrderHelper(SplayTreeNode node) {
        if (node != null) {
            postOrderHelper(node.left);
            postOrderHelper(node.right);
            System.out.print(node.data + " ");
        }
    }

    // Pre-Order traversal
    // Node->Left Subtree->Right Subtree
    public void preorder() {
        preOrderHelper(this.root);
    }

    // In-Order traversal
    // Left Subtree -> Node -> Right Subtree
    public void inorder() {
        inOrderHelper(this.root);
    }

    // Post-Order traversal
    // Left Subtree -> Right Subtree -> Node
    public void postorder() {
        postOrderHelper(this.root);
    }

    // search the tree for the key k
    // and return the corresponding node
    public SplayTreeNode searchTree(String k) {
        SplayTreeNode x = searchTreeHelper(root, k);
        if (x != null) {
            splay(x);
        }
        return x;
    }

    // find the node with the minimum key
    public SplayTreeNode minimum(SplayTreeNode node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    // find the node with the maximum key
    public SplayTreeNode maximum(SplayTreeNode node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    // find the successor of a given node
    public SplayTreeNode successor(SplayTreeNode x) {
        // if the right subtree is not null,
        // the successor is the leftmost node in the
        // right subtree
        if (x.right != null) {
            return minimum(x.right);
        }

        // else it is the lowest ancestor of x whose
        // left child is also an ancestor of x.
        SplayTreeNode y = x.parent;
        while (y != null && x == y.right) {
            x = y;
            y = y.parent;
        }
        return y;
    }

    // find the predecessor of a given node
    public SplayTreeNode predecessor(SplayTreeNode x) {
        // if the left subtree is not null,
        // the predecessor is the rightmost node in the
        // left subtree
        if (x.left != null) {
            return maximum(x.left);
        }

        SplayTreeNode y = x.parent;
        while (y != null && x == y.left) {
            x = y;
            y = y.parent;
        }

        return y;
    }

    // insert the key to the tree in its appropriate position
    public void insert(Person key) {
        SplayTreeNode node = new SplayTreeNode(key);
        SplayTreeNode y = null;
        SplayTreeNode x = this.root;

        while (x != null) {
            y = x;
            if (node.data.getName().compareTo(x.data.getName()) < 0) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        // y is parent of x
        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.data.getName().compareTo(y.data.getName()) < 0) {
            y.left = node;
        } else {
            y.right = node;
        }

        // splay node
        splay(node);
    }

    // delete the node from the tree
    void deleteNode(Person data) {
//        deleteNodeHelper(this.root, data);
    }

    // print the tree structure on the screen
    public void prettyPrint() {
        printHelper(this.root, "", true);
    }
}