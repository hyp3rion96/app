package app.exercise.testing;

import app.exercise.adt.RedBlackTree;
import app.exercise.visualtree.RedBlackTreeDrawer;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Executable class to test the RedBlackTree. Reads Integers from the command line
 * and stores them inside the RedBlackTree. After each insertion the current RedBlackTree is visualized by using a RedBlackTreeDrawer. After insertion is completed the black height of the tree is printed, as well as the elements of the tree in ascending order and in descending order.
 * Then all Integers that are smaller than 0 are removed from the tree and it's elements are again printed in ascending order.
 */
public class TestRBTInteger {
    public static void main(String[] args) {

        //use Scanner to read from Standard inout
        Scanner in = new Scanner(System.in);
        RedBlackTree<Integer> rbt = new RedBlackTree<>();
        RedBlackTreeDrawer<Integer> visual = new RedBlackTreeDrawer<>();

        String next;

        while (in.hasNextInt()) {
            rbt.add(in.nextInt());
            visual.draw(rbt.getRoot());
        }

        System.out.println("Black height of tree: " + rbt.getBlackHeight());
        System.out.println("In-order:");

        for (Iterator<Integer> it = rbt.iterator(); it.hasNext(); ) {
            System.out.print( it.next() + "   ");
        }

        System.out.println("\nReverse in-order:");
        for(Iterator<Integer> rIt = rbt.rIterator(); rIt.hasNext(); )
            System.out.print(rIt.next() + "   ");

        for(Iterator<Integer> it = rbt.iterator(); it.hasNext(); ) {
            if(it.next() < 0) {
                it.remove();
                visual.draw(rbt.getRoot());
            }
        }
        System.out.println("\nAfter deletion of values < 1: ");
        for(Integer integer : rbt)
            System.out.print(integer    + "   ");

    }
}
