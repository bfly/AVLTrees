package avlTrees;

import edu.dcccd.Card;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static edu.dcccd.Card.Rank;
import static edu.dcccd.Card.Suit;

class AVLTreeTest {
    @Test
    @DisplayName("50,30,10,70,60")
    void testAVLTree1() {
        AVLTree<Integer> tree = new AVLTree<>();
        Integer[] ints = new Integer[] {50, 30, 10, 70, 60};
        for ( Integer i : ints ) tree.insert(i);
        tree.printTree();
        System.out.println();
        tree.levelOrder();
    }

    @Test
    @DisplayName("this is a list of strings")
    void testAVLTree2() {
        AVLTree<String> tree = new AVLTree<>();
        String[] strings = "this is a list of strings".split(" ");
        for ( String s : strings ) tree.insert(s);
        tree.printTree();
        System.out.println();
        tree.levelOrder();
    }

    @Test
    @DisplayName("Cards")
    void testRedBlackTree3() {
        AVLTree<Card> tree = new AVLTree<>();
        for ( Rank rank : Rank.values()) {
            for ( Suit suit : Suit.values()) {
                Card card = new Card(rank, suit);
                tree.insert(card);
            }
        }
        tree.printTree();
        System.out.println();
        tree.levelOrder();
    }
}