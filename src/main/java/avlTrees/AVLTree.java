package avlTrees;

/*
 *  Java Program to Implement AVL Tree
 */

import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Math.max;

/* Class AVLNode */
class AVLNode<T extends Comparable<T>>
{
    T data;
    int height;
    AVLNode<T> left, right;

    /* Constructor */
    public AVLNode(T n)
    {
        data = n;
        height = 0;
        left = null;
        right = null;
    }
}

/* Class AVLTree */
class AVLTree<T extends Comparable<T>>
{
    private AVLNode<T> root;

    /* Constructor */
    public AVLTree()
    {
        root = null;
    }
    /* Function to check if tree is empty */
    public boolean isEmpty()
    {
        return root == null;
    }
    /* Make the tree logically empty */
    public void makeEmpty()
    {
        root = null;
    }
    /* Function to insert data */
    public void insert(T data) {
        root = insert(data, root);
    }
    /* Function to get height of node */
    private int height(AVLNode<T> t ) {
        return t == null ? -1 : t.height;
    }

    /* Function to insert data recursively */
    private AVLNode<T> insert(T x, AVLNode<T> t) {
        if (t == null)
            t = new AVLNode<>(x);
        else if (x.compareTo(t.data) < 0) {
            t.left = insert( x, t.left );
            if( height( t.left ) - height( t.right ) == 2 )
                if( x.compareTo(t.left.data) < 0)
                    t = rotateWithLeftChild( t );
                else
                    t = doubleWithLeftChild( t );
        }
        else if( x.compareTo(t.data) > 0) {
            t.right = insert( x, t.right );
            if( height( t.right ) - height( t.left ) == 2 )
                if( x.compareTo(t.right.data) > 0)
                    t = rotateWithRightChild( t );
                else
                    t = doubleWithRightChild( t );
        }
        // else
              // Duplicate; do nothing
        t.height = max(height(t.left), height(t.right)) + 1;
        return t;
    }
    /* Rotate binary tree node with left child */
    private AVLNode<T> rotateWithLeftChild(AVLNode<T> k2)
    {
        AVLNode<T> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = max(height(k2.left), height(k2.right)) + 1;
        k1.height = max(height(k1.left), k2.height) + 1;
        return k1;
    }

    /* Rotate binary tree node with right child */
    private AVLNode<T> rotateWithRightChild(AVLNode<T> k1)
    {
        AVLNode<T> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = max(height(k1.left), height(k1.right)) + 1;
        k2.height = max(height(k2.right), k1.height) + 1;
        return k2;
    }
    /**
     * Double rotate binary tree node: first left child
     * with its right child; then node k3 with new left child */
    private AVLNode<T> doubleWithLeftChild(AVLNode<T> k3)
    {
        k3.left = rotateWithRightChild( k3.left );
        return rotateWithLeftChild( k3 );
    }
    /**
     * Double rotate binary tree node: first right child
     * with its left child; then node k1 with new right child */
    private AVLNode<T> doubleWithRightChild(AVLNode<T> k1)
    {
        k1.right = rotateWithLeftChild( k1.right );
        return rotateWithRightChild( k1 );
    }
    /* Functions to count number of nodes */
    public int countNodes()
    {
        return countNodes(root);
    }
    private int countNodes(AVLNode<T> r)
    {
        if (r == null)
            return 0;
        else {
            int l = 1;
            l += countNodes(r.left);
            l += countNodes(r.right);
            return l;
        }
    }
    /* Functions to search for an element */
    public boolean search(T val) {
        return search(root, val);
    }
    private boolean search(AVLNode<T> r, T val)
    {
        boolean found = false;
        while ((r != null) && !found) {
            T rval = r.data;
            if (val.compareTo(rval) < 0)
                r = r.left;
            else if (val.compareTo(rval) > 0)
                r = r.right;
            else {
                found = true;
                break;
            }
            found = search(r, val);
        }
        return found;
    }
    /* Function to return root node */
    public AVLNode<T> getRoot() {
        return root;
    }
    /* Function for inorder traversal */
    public void inorder() {
        inorder(root);
    }
    private void inorder(AVLNode<T> r) {
        if (r != null) {
            inorder(r.left);
            System.out.print(r.data +" ");
            inorder(r.right);
        }
    }
    /* Function for preorder traversal */
    public void preorder() {
        preorder(root);
    }
    private void preorder(AVLNode<T> r) {
        if (r != null)
        {
            System.out.print(r.data +" ");
            preorder(r.left);
            preorder(r.right);
        }
    }
    /* Function for postorder traversal */
    public void postorder() {
        postorder(root);
    }
    private void postorder(AVLNode<T> r) {
        if (r != null)
        {
            postorder(r.left);
            postorder(r.right);
            System.out.print(r.data +" ");
        }
    }
    void printTree() {
        printTree(root);
        System.out.println();
    }
    private void printTree(AVLNode<T> node) {
        if (node == null) return;
        printTree(node.left);
        System.out.printf("Key: %-15s", node.data);
        printTree(node.right);
    }
    public void levelOrder() { levelOrder(root); }

    public void levelOrder( AVLNode<T> root) {
        if (root == null) return;
        Queue<AVLNode<T>> q = new LinkedList<>();
        int level = 0;
        q.add(root);
        q.add(null);
        while (!q.isEmpty()) {
            AVLNode<T> curr = q.poll();
            if (curr == null) {
                if (!q.isEmpty()) {
                    q.add(null);
                    System.out.println();
                    level++;
                }
            } else {
                String left = "Null";
                String right = "Null";
                if (curr.left != null) {
                    left = String.valueOf(curr.left.data);
                    q.add(curr.left);
                }
                if (curr.right != null) {
                    right = String.valueOf(curr.right.data);
                    q.add(curr.right);
                }

                System.out.println("Key: " + curr.data +
                    ", Left: " + left + ", Right: " + right + ", Level: " + level);
//                System.out.println(curr + " Level: " + level);
            }
        }
    }
}
