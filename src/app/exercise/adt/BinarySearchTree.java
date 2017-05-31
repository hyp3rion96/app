package app.exercise.adt;

import java.util.Collections;

/**
 * Implementation of BinarySearchTree for Objects that extend Comparable and whose compareTo() method is equal consistent.
 * @author Alexander Wähling
 * @version 0.1
 */
public class BinarySearchTree <T extends Comparable<T>> {
    /**
     * Nested Node class to store the Tree information.
     */
  	public static final String DELIM = "\n";

    private class Node {
        /**
         * stores Data of Type <i><T extends Comparable></i>
         */
        T data;
        /**
         * store reference to left and right subtree
         */
        Node left, right;


        /**
         * Default constructor that initializes data, left and right with <i>null</i>.
         */
        Node() {
            this.data = null;
            left = null;
            right = null;
        }

        /**
         * Constructor that initializes data with the passed value and left, right with <i>null</i>.
         * @param data Object to set as data of current {@link Node}
         */
        Node(T data) {
            this.data = data;
            left = null;
            right = null;
        }

        /**
         * inserts <i>T data</i> at a correct position in the BST by using the <i>compareTo(T)</i> method.
         * @param data data to insert.
         */
        void insert(T data) {
            if(this.data == null)
                this.data = data;
            else if (this.data.compareTo(data) == 0)
                throw new IllegalArgumentException("Cannot store duplicate elements.");
            else if (data.compareTo(this.data) < 0)
                if(left == null)
                    left = new Node(data);
                else
                    left.insert(data);
            else
                if(right == null)
                    right = new Node(data);
                else
                	right.insert(data);
        }

        /**
         * Checks subtruee from current {@link Node} if it contains Data equal to passed Data.
         * @param data Element to search for in Tree
         * @return true if subtree contains data, false otherwise
         */
        boolean contains(T data) {
            if(this.data.equals(data))
                return true;
            else if(data.compareTo(this.data) < 0 && left != null)
                return left.contains(data);
            else if(data.compareTo(this.data) > 0 && right != null)
                return right.contains(data);
            else
                return false;
        }

        /**
         * Recursive method that constructs a String representation of the current {@link BinarySearchTree} in ascending
         * order.
         * @return String representation of elements in {@link BinarySearchTree} in ascending order seperated by newlines.
         */
        String getString() {
            if(left == null && right == null)
                return data.toString() + DELIM;
            else if(left == null && right != null)
                return data.toString() + DELIM + right.getString();
            else if(left != null && right == null)
                return left.getString() + data.toString() + DELIM;
            else
                return left.getString() + data.toString() + DELIM + right.getString();
        }

    }

    /**
     * Reference to the root of the {@link BinarySearchTree}.
     */
    private Node root;

    /**
     * Default constructor that initializes the {@link #root} {@link Node} with the default {@link Node} constructor.
     */
    public BinarySearchTree() {
        root = new Node();
    }

    /**
     * insert data into current {@link BinarySearchTree} by calling {@link Node#insert(Comparable)} with {@link #root}.
     * @param data data to insert.
     */
    public void insert(T data) {
        root.insert(data);
    }

    /**
     * Returns String representation in ascending order of current object by calling {@link Node#getString()} on the {@link #root}.
     * @return String representation of current {@link BinarySearchTree}
     */
    public boolean contains(T data){
        return root.contains(data);
    }

    /**
     * Returns minimal element of BST
     * @return reference to minimal elemnt of current BST
     */
    public T min() {
        Node  curr = root;
        while (curr.left != null)
            curr = curr.left;
        return curr.data;
    }

    /**
     * Returns maximum element of current BST
     * @return reference to maximum element of current BST
     */
    public T max() {
        Node  curr = root;
        while (curr.right != null)
            curr = curr.right;
        return curr.data;
    }

    @Override
    public String toString() {
        return root.getString();
    }

    /**
     * Returns String representation in descending order of current object by reversing the String returned from {@link #toString()}.
     * @return reverse String representation of current {@link BinarySearchTree}
     */
    public String toReverseString() {
        StringBuilder stringBuilder = new StringBuilder();
        String stringBST[] = this.toString().split(BinarySearchTree.DELIM);
        for(int i = stringBST.length-1; i >= 0; i--)
            stringBuilder.append(stringBST[i] + BinarySearchTree.DELIM);
        return stringBuilder.toString();
    }
}
