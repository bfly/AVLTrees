package avlTrees;

/*
 *  Java Program to Implement AVL Tree
 */

import java.util.Scanner;

/* Class AVL Tree Test */
public class AVLTreeMain
{
    public static void main(String[] args) {
        new AVLTreeMain().go();
    }
    private void go() {
        Scanner scan = new Scanner(System.in);
        /* Creating object of AVLTree */
        AVLTree<String> avlt = new AVLTree<>();

        System.out.println("AVLTree Tree Test\n");
        enterElements(scan, avlt);

        /*  Perform tree operations  */
        while (true) {
            System.out.println("\nAVLTree Operations");
            System.out.println("1. insert ");
            System.out.println("2. search");
            System.out.println("3. count nodes");
            System.out.println("4. check empty");
            System.out.println("5. print tree");
            System.out.println("6. clear tree");
            System.out.println("9. exit");

            char choice = scan.nextLine().charAt(0);
            switch (choice) {
                case '1' -> enterElements(scan, avlt);
                case '2' -> {
                    System.out.println("Enter element to search");
                    System.out.println("Search result : " + avlt.search(scan.nextLine()));
                }
                case '3' -> System.out.println("Nodes = " + avlt.countNodes());
                case '4' -> System.out.println("Empty status = " + avlt.isEmpty());
                case '5' -> printTree(avlt);
                case '6' -> {
                    System.out.println("\nTree Cleared");
                    avlt.makeEmpty();
                }
                case '9' -> System.exit(0);
                default -> System.out.println("Wrong Entry \n ");
            }
            displayTreeNodes(avlt);
        }
    }

    private void displayTreeNodes( AVLTree<String> avlt ) {
        /*  Display tree  */
        System.out.print("\nPost order : ");
        avlt.postorder();
        System.out.print("\nPre order : ");
        avlt.preorder();
        System.out.print("\nIn order : ");
        avlt.inorder();
        System.out.println();
    }

    private void enterElements( Scanner scan, AVLTree<String> avlt ) {
        System.out.println("Enter elements to insert, \\ to exit");
        while (true) {
            String element = scan.next().trim();
            if (element.charAt(0) == '\\') break;
            avlt.insert(element);
        }
        scan.nextLine();
        displayTreeNodes(avlt);
    }
    public void printTree(AVLTree<String> avlt) {
        int height = avlt.getRoot().height;
        for ( int i = 0; i <= height; i++ ) {
            System.out.printf("lvl%d ", i);
        }
        System.out.println();

        printTree(avlt.getRoot(),0);
    }
    // print tree horizontally
    protected void printTree(AVLNode<String> p, int depth) {
        if (p != null) {
            printTree(p.right,depth+1);
            for (int i = 1; i <= depth; i++)
                System.out.print("     ");
            System.out.println(p.data);
            printTree(p.left,depth+1);
        }
    }
}
