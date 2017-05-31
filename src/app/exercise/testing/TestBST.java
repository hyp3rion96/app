package app.exercise.testing;

import app.exercise.adt.BinarySearchTree;
import app.exercise.algebra.CompRational;

import java.util.Random;
import java.util.Scanner;

/**
 * Executable class that is  used to test the {@link CompRational} Class and the {@link app.exercise.adt.BinarySearchTree}
 * @author Robin Hundt
 * @version 0.5
 */
public class TestBST {

    public static void main(String[] args) {
        //use Scanner to read from Standard inout
        Scanner in = new Scanner(System.in);
        BinarySearchTree<CompRational> bst = new BinarySearchTree<>();

        String next;
        CompRational first = null;
        CompRational last = null;
        while (in.hasNext()) {
            // check if next two tokens are Integer numbers
            if((next = in.next()).matches("-?\\d+") && in.hasNext("-?\\d+")) {
                CompRational cr = new CompRational(Integer.parseInt(next), in.nextInt());
                if(first == null)
                    first = cr;
                last = cr;
                bst.insert(cr);
            }
            else {
                System.err.println("Only pairs of integers as input");
                System.exit(1);
            }
        }

        System.out.println("String representation of BST:");
        System.out.println(bst);
        System.out.println("Reverse String representation of BST:");
        System.out.println(bst.toReverseString());
        System.out.println("Min element:");
        CompRational min = bst.min();
        System.out.println(min);
        System.out.println("Max element:");
        CompRational max = bst.max();
        System.out.println(max);
        System.out.println("BST contains " + first + ": " + bst.contains(first));
        System.out.println("BST contains " + last + ": " + bst.contains(last));

        int minBound = min.getNum() * max.getDenom();
        int maxBound = max.getNum() * min.getDenom();
        int commonDenom = first.getDenom() * last.getDenom();
        Random rand = new Random(42);
        //generate Random ComcpRational between the lowest and highest CompRational inside the BST
        CompRational rnd = new CompRational(rand.nextInt(maxBound - minBound) + minBound, commonDenom);
        System.out.println("BST contains " + rnd + ": " + bst.contains(rnd));
    }
}
