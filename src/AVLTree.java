package src;

import java.util.ArrayList;
import java.util.List;

public class AVLTree {
    private static class Node {
        Contact contact;
        Node left, right;
        int height;

        Node(Contact contact) {
            this.contact = contact;
            this.height = 1;
        }
    }

    private Node root;

    private int height(Node n) { return n == null ? 0 : n.height; }
    private int getBalance(Node n) { return n == null ? 0 : height(n.left) - height(n.right); }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;
        x.right = y;
        y.left = T2;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;
        y.left = x;
        x.right = T2;
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        return y;
    }

    public void insert(Contact contact) {
        root = insert(root, contact);
    }

    private Node insert(Node node, Contact contact) {
        if (node == null) return new Node(contact);

        int cmp = contact.getName().compareToIgnoreCase(node.contact.getName());
        if (cmp < 0) node.left = insert(node.left, contact);
        else node.right = insert(node.right, contact);

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        if (balance > 1 && contact.getName().compareToIgnoreCase(node.left.contact.getName()) < 0)
            return rightRotate(node);

        if (balance < -1 && contact.getName().compareToIgnoreCase(node.right.contact.getName()) > 0)
            return leftRotate(node);

        if (balance > 1 && contact.getName().compareToIgnoreCase(node.left.contact.getName()) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && contact.getName().compareToIgnoreCase(node.right.contact.getName()) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node.left);
        }

        return node;
    }

    public List<Contact> getInOrderTraversal() {
        List<Contact> list = new ArrayList<>();
        inOrder(root, list);
        return list;
    }

    private void inOrder(Node node, List<Contact> list) {
        if (node != null) {
            inOrder(node.left, list);
            list.add(node.contact);
            inOrder(node.right, list);
        }
    }
}
