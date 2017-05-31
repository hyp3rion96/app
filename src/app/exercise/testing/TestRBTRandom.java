package app.exercise.testing;

import app.exercise.adt.DuplicateElementException;
import app.exercise.adt.RedBlackTree;
import app.exercise.visualtree.RedBlackTreeDrawer;

import java.util.Random;

/**
 * Executable Class to test the RedBlackTree. Instantiates a RedBlackTree for Integers and a RedBlackTreeDrawer to visualize the RBT.
 * Then inserts 100_000 non-equivalent Integer objects into the RedBlackTree.
 */
public class TestRBTRandom {
    public static void main(String[] args) {
        RedBlackTreeDrawer<Integer> visual = new RedBlackTreeDrawer<>();
        RedBlackTree<Integer> rbt = new RedBlackTree<>();

        Random rnd = new Random(42);
        int count = 0;
        Integer nmbr = 0;

        try {
            while(count < 100_000) {
                nmbr = rnd.nextInt();
                if(!rbt.contains(nmbr)) {
                    rbt.add(nmbr);
                    count++;
                    visual.draw(rbt.getRoot());
                    // change sleep amount for better visualization
                    Thread.sleep(0);
                }
            }
        } catch (InterruptedException | DuplicateElementException e) {
            System.out.println("rbt contains " + nmbr + rbt.contains(nmbr));
            e.printStackTrace();
        }
    }
}
