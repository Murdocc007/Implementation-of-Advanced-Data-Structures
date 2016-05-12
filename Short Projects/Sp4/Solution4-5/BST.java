/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * @author rbk Binary search tree (nonrecursive version)
 *
 */
import java.util.*;

public class BST<T extends Comparable<? super T>> {

    public static final boolean RED = false;
    public static final boolean BLACK = true;

    class Entry<T> {

        T element;
        Entry<T> left, right, parent;
        //1 for red
        //0 for black
        boolean color;

        Entry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
            element = x;
            left = l;
            right = r;
            parent = p;
        }
    }

    Entry<T> root;
    int size;

    BST() {
        root = null;
        size = 0;
    }

    // Find x in subtree rooted at node t.  Returns node where search ends.
    Entry<T> find(Entry<T> t, T x) {
        Entry<T> pre = t;
        while (t != null) {
            pre = t;
            int cmp = x.compareTo(t.element);
            if (cmp == 0) {
                return t;
            } else if (cmp < 0) {
                t = t.left;
            } else {
                t = t.right;
            }
        }
        return pre;
    }

    // Is x contained in tree?
    public boolean contains(T x) {
        Entry<T> node = find(root, x);
        return node == null ? false : x.equals(node.element);
    }

    // Add x to tree.  If tree contains a node with same key, replace element by x.
    // Returns true if x is a new element added to tree.
    public boolean add(T x) {
        if (size == 0) {
            root = new Entry<>(x, null, null, null);
        } else {
            Entry<T> node = find(root, x);
            int cmp = x.compareTo(node.element);
            if (cmp == 0) {
                node.element = x;
                return false;
            }
            Entry<T> newNode = new Entry<>(x, null, null, node);
            if (cmp < 0) {
                node.left = newNode;
            } else {
                node.right = newNode;
            }
        }
        size++;
        return true;
    }

    // Remove x from tree.  Return x if found, otherwise return null
    public T remove(T x) {
        T rv = null;
        if (size > 0) {
            Entry<T> node = find(root, x);
            if (x.equals(node.element)) {
                rv = node.element;
                remove(node);
                size--;
            }
        }
        return rv;
    }

    // Called when node has at most one child.  Returns that child.
    Entry<T> oneChild(Entry<T> node) {
        return node.left == null ? node.right : node.left;
    }

    // Remove a node from tree
    void remove(Entry<T> node) {
        if (node.left != null && node.right != null) {
            removeTwo(node);
        } else {
            removeOne(node);
        }
    }

    // remove node that has at most one child
    void removeOne(Entry<T> node) {
        if (node == root) {
            root = oneChild(root);
        } else {
            Entry<T> p = node.parent;
            if (p.left == node) {
                p.left = oneChild(node);
            } else {
                p.right = oneChild(node);
            }
        }
    }

    // remove node that has two children
    void removeTwo(Entry<T> node) {
        Entry<T> minRight = node.right;
        while (minRight.left != null) {
            minRight = minRight.left;
        }
        node.element = minRight.element;
        removeOne(minRight);
    }

    //rotates the node to the lefy and returns the new rotated node
    public Entry rotateLeft(Entry node) {
        if (node.right != null) {
            Entry rightChild = node.right;//child node
            Entry grandparent = node.parent;
            if (grandparent != null) {
                if (isLeftChild(node)) {
                    grandparent.left = rightChild;
                } else {
                    grandparent.right = rightChild;
                }
            }
            //if the current node is the root, then
            // the right child becomes the root node
            if (node == root) {
                root = rightChild;
            }
            rightChild.parent = grandparent;
            node.right = rightChild.left;//assigning the left child of the right node to the parent node
            rightChild.left = node;//the parent node comes to the left of the right child
            node.parent = rightChild;//the child node becomes the parent
        }
        return node;
    }

    //rotates the node to the right and returns the new rotated node    
    public Entry rotateRight(Entry node) {
        if (node.left != null) {
            Entry leftChild = node.left;//child node
            Entry grandparent = node.parent;
            if (grandparent != null) {
                if (isLeftChild(node)) {
                    grandparent.left = leftChild;
                } else {
                    grandparent.right = leftChild;
                }
            }
            //if the current node is the root then the
            //the left child becomes the root node
            if (node == root) {
                root = leftChild;
            }
            leftChild.parent = grandparent;
            node.left = leftChild.right;//assigning the left child of the right node to the parent node
            leftChild.right = node;//the parent node comes to the left of the right child
            node.parent = leftChild;//the child node becomes the parent
        }
        return node;
    }

    //returns the sibling of the parent pointer
    public Entry uncle(Entry node) {
        if (node.parent == null || node.parent.parent == null) {
            return null;
        }
        Entry grandparent = node.parent.parent;
        if (grandparent.left == node.parent) {
            return grandparent.right;
        }
        return grandparent.left;
    }

    //checks if the node is the left child of the parent
    public boolean isLeftChild(Entry node) {
        if (node.parent == null) {
            return false;
        }
        return node.parent.left == node;
    }

    //checks if the node is the right child of the parent
    public boolean isRightChild(Entry node) {
        if (node.parent == null) {
            return false;
        }
        return node.parent.right == node;
    }

    public static void main(String[] args) {
        BST<Integer> t = new BST<>();
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            int x = in.nextInt();
            if (x > 0) {
                System.out.print("Add " + x + " : ");
                t.add(x);
                t.printTree();
            } else if (x < 0) {
                System.out.print("Remove " + x + " : ");
                t.remove(-x);
                t.printTree();
            } else {
                Comparable[] arr = t.toArray();
                System.out.print("Final: ");
                for (int i = 0; i < t.size; i++) {
                    System.out.print(arr[i] + " ");
                }
                System.out.println();
                return;
            }
        }
    }

    // Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
        Comparable[] arr = new Comparable[size];
        inOrder(root, arr, 0);
        return arr;
    }

    // Recursive in-order traversal of tree rooted at "node".
    // "index" is next element of array to be written.
    // Returns index of next entry of arr to be written.
    int inOrder(Entry<T> node, Comparable[] arr, int index) {
        if (node != null) {
            index = inOrder(node.left, arr, index);
            arr[index++] = node.element;
            index = inOrder(node.right, arr, index);
        }
        return index;
    }

    public void printTree() {
        System.out.print("[" + size + "]");
        printTree(root);
        System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
        if (node != null) {
            printTree(node.left);
            System.out.print(" " + node.element);
            printTree(node.right);
        }
    }
}
/*
 Sample input:
 1 3 5 7 9 2 4 6 8 10 -3 -6 -3 0

 Output:
 Add 1 : [1] 1
 Add 3 : [2] 1 3
 Add 5 : [3] 1 3 5
 Add 7 : [4] 1 3 5 7
 Add 9 : [5] 1 3 5 7 9
 Add 2 : [6] 1 2 3 5 7 9
 Add 4 : [7] 1 2 3 4 5 7 9
 Add 6 : [8] 1 2 3 4 5 6 7 9
 Add 8 : [9] 1 2 3 4 5 6 7 8 9
 Add 10 : [10] 1 2 3 4 5 6 7 8 9 10
 Remove -3 : [9] 1 2 4 5 6 7 8 9 10
 Remove -6 : [8] 1 2 4 5 7 8 9 10
 Remove -3 : [8] 1 2 4 5 7 8 9 10
 Final: 1 2 4 5 7 8 9 10 

 Extending to AVL tree:

 class AVLEntry<T> extends Entry<T> {
 int height;
 AVLEntry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
 super(x,l,r,p);
 height = 0;
 }
 }

 Extending to Red-Black tree:

 private static final boolean RED   = false;
 private static final boolean BLACK = true;

 class RBEntry<T> extends Entry<T> {
 boolean color;
 RBEntry(T x, Entry<T> l, Entry<T> r, Entry<T> p) {
 super(x,l,r,p);
 color = RED;
 }
 }

 */
