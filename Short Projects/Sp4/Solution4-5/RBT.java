/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.util.Scanner;

/**
 *
 * @author aditya,divyanshu,radhika,shweta
 */
public class RBT<T extends Comparable<? super T>> extends BST<T> {

    RBT() {
        root = null;
        size = 0;
    }

    public boolean add(T x) {
        if (size == 0) {
            root = new Entry<>(x, null, null, null);
            root.color = BLACK;//paint the root black
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
            repair(newNode);
        }
        size++;
        return true;
    }

    public void repair(Entry node) {
        node.color = RED;//set the node color to be red

        //problem arises when the color
        // of the node turns out to be red
        if (node != root && node.parent.color == RED) {

            //Case 1:
            if (uncle(node)!=null && uncle(node).color == RED) {
                //set the color of the parent and the uncle
                //to black and the color of the grandparent
                //to red and recurse for with node as grandparent
                node.parent.color = BLACK;
                uncle(node).color = BLACK;
                node.parent.parent.color = RED;
                repair(node.parent.parent);
            } //Case 2:
            //2.1: Uncle is black.Both the children and the parent are on the left hand side of the parent
            //the added node is aligned with the grandparent
            else if (isLeftChild(node) && isLeftChild(node.parent)) {
               //set the color of the parent node to be black
                // and set the color of grandparent to red and rotate
                // the grandparent
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateRight(node.parent.parent);

            } //2.2 Uncle is black.Both the children and the parent are on the right hand side of the parent
            //the added node is aligned with the grandparent
            else if (isRightChild(node) && isRightChild(node.parent)) {
               //set the color of the parent node to be black
                // and set the color of grandparent to red and rotate
                // the grandparent
                node.parent.color = BLACK;
                node.parent.parent.color = RED;
                rotateLeft(node.parent.parent);
            } //Case 3:
            //3.1 Uncle is black.Node is the left child and the node's parent is the right child
            else if (isLeftChild(node) && isRightChild(node.parent)) {
                node = rotateRight(node.parent);
                //rotate right the node's parent and apply case 2
                repair(node);
            } //3.2 Uncle is black.Node is the right child and the node's parent is the left child
            else {
                node = rotateLeft(node.parent);
                //rotate left the node's parent and apply case 2
                repair(node);
            }

        }
        //to maintain the red black tree property
        root.color = BLACK;
    }

    public static void main(String[] args) {
        RBT<Integer> t = new RBT<>();
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

}
