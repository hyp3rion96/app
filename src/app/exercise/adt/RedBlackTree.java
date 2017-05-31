/**
 * This Class provides methods for working with a Red-Black-Tree
 * @author Alexander Wähling 29.05.2017
 * @version 0.1
 */

package app.exercise.adt;
import app.exercise.algebra.*;
import java.util.AbstractCollection;

public class RedBlackTree<T extends Comparable<T>> extends java.util.AbstractCollection<E>  {
    /**
     * This is the root of the BST
     */
    public Node root;
    /**
     * These are the Values for the Color of the Nodes.
     */
    private static final boolean RED = true, BLACK = false;

    /**
     * This is the return of the String-Methods
     */
    private String rstr=" ", str=" ";

    /**
     * This inner-Class implements Node-Objects, wich are used for implementing Trees
     */
    class Node {
        Node father;
        T data;
        boolean colour;
        Node left;
        Node right;
        public Node(T data){
            this.data = data;
            left = null;
            right = null;
            colour = RED;
        }
        public Node(Node father, T data) {
            this.data = data;
            left = null;
            right = null;
            colour = RED;
            this father = father;
        }
    }
    /**
     * The Constructor
     */

    public RedBlackTree(){
        this.root = null;
    }
    /**
     * Contains checks, if a given Objects is part of the BST
     * @param id the Object, which is to be checked
     * @return Bool, weather id is part of BST or not
     */
    public boolean contains(T id){
		Node current = root;
		while(current!=null){
			if(current.data.equals(id)){
				return true;
			}else if((current.data).compareTo(id) > 0){
				current = current.left;
			}else{
				current = current.right;
			}
		}
		return false;
	}

    /**
     * This class inserts an elementto the BST and throws the illegalArgumentException if the element is
     * already part of the BST
     * @param id
     */
    public void insert(T id){
        if(!contains(id)) {
            Node newNode = new Node(id);
            if(root==null){
                root = new Node(id);
                return;
            }
            Node current = root;
            Node father = null;
            while(true){
               father = current;
                if(id.compareTo(current.data) < 0){
                    current = current.left;
                    if(current==null){
                       father.left = new Node father, id);
                        return;
                    }
                }else{
                    current = current.right;
                    if(current==null){
                       father.right = new Node father, id);
                        return;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException("Node already exists");
        }
	}

    /**
     * This recursive min-Function returns the smallest Object, which is part of the
     * BST
     * @return smallest part of the Tree
     */
    public T min() {
        Node current = root;
        while (current.left != null) {
            current = current.left;
        }
        return (current.data);
    }

    /**
     * The recursive Method returns the biggest member of the tree
     * @return the biggest member of the tree
     */
    public T max() {
        Node current = root;
        while(current.right != null)  {
            current = current.right;
        }
        return (current.data);
    }

    private void rotateLeft(Node rotate) {
        Node swap = rotate;
        rotate = rotate.right;
        rotate.left = swap;
        (rotate.left).left = swap.left;
        (rotate.left).right = (swap.right).left;
        rotate.right = (swap.right).right;
    }

    private void rotateRight(Node rotate) {
        Node swap = rotate;
        rotate = rotate.left;
        rotate.right = swap;
        (rotate.right).right = swap.right;
        (rotate.right).left = (swap.left).right;
        rotate.left = (swap.left).left;
    }

    private Node findUncle(Node start) {
        if(Node.father.father.left == Node.father) {
          return Node.father.father.right;
        }
        if(Node.father.father.right == Node.father) {
          return Node.father.father.left;
        }
    }
    private void repair(Node start) {
        if((start.father).colour == BLACK || start == root) {
            return;
        }

        if(start.father == root && (start.father).colour == RED) {
            (start.father).colour = BLACK;
            return;
        }

        if((start.father)father.right.colour == RED && (start.father).father.left.colour == RED) {
            (start.father).father.colour = RED;
            (start.father)father.right.colour = BLACK;
            (start.father).father.left.colour = BLACK;
            return;
        }

        if(start.father.colour == RED && findUncle(start).colour == RED) {

        }
    }


}
