package app.exercise.testing;

import app.exercise.adt.RedBlackTree;
import app.exercise.algebra.CompRational;
import app.exercise.visualtree.RedBlackTreeDrawer;

import java.util.Iterator;
import java.util.Scanner;

/**
 * Executable class to test the RedBlackTree. Reads pairs of Integers from the command line, interprets those as rational numbers (numerator, denominator)
 * and stores them inside the RedBlackTree. After each insertion the current RedBlackTree is visualized by using a RedBlackTreeDrawer. After insertion is completed the black height of the tree is printed, as well as he elements of the tree in ascending order and in descending order.
 * Then all Rational numbers that are smaller than 1 are removed from the tree and it's elements are again printed in ascending order.
 */
public class TestRBTRational {
    public static void main(String[] args) {

        //use Scanner to read from Standard inout
        Scanner in = new Scanner(System.in);
        RedBlackTree<CompRational> rbt = new RedBlackTree<>();
        RedBlackTreeDrawer<CompRational> visual = new RedBlackTreeDrawer<>();

        String next;

        while (in.hasNext()) {
            // check if next two tokens are Integer numbers
            if((next = in.next()).matches("-?\\d+") && in.hasNext("-?\\d+")) {
                rbt.add(new CompRational(Integer.parseInt(next), in.nextInt()) );
                visual.draw(rbt.getRoot());
            }
            else {
                System.err.println("Only pairs of integers as input");
                System.exit(1);
            }
        }

        System.out.println("Black height of tree: " + rbt.getBlackHeight());
        System.out.println("In-order:");

        for (Iterator<CompRational> it = rbt.iterator(); it.hasNext(); ) {
            System.out.print( it.next() + "   ");
        }
        System.out.println("\nReverse in-order:");
        for(Iterator<CompRational> rIt = rbt.rIterator(); rIt.hasNext(); )
            System.out.print(rIt.next() + "   ");

        CompRational one = new CompRational(1 , 1);
        for(Iterator<CompRational> it = rbt.iterator(); it.hasNext(); ) {
            if(it.next().compareTo( one ) < 0) {
                it.remove();
                visual.draw(rbt.getRoot());
            }
        }
        System.out.println("\nAfter deletion of values < 1: ");
        for(CompRational cr : rbt)
            System.out.print(cr + "   ");

    }
}
