package app.exercise.testing;
import app.exercise.adt.RedBlackTree;
import app.exercise.visualtree.*;
public class testRBT {
    public static void main(String[] args) {
        RedBlackTreeDrawer<Integer> test = new RedBlackTreeDrawer();
        RedBlackTree<Integer> RBT = new RedBlackTree();
        int[] nmbr = {13,8,17,1,11,15,25,6,16,27};
        for (int i = 0; i < nmbr.length; i++) {
            RBT.insert(nmbr[i]);
        }
        test.draw(RBT.getRoot());
    }
}