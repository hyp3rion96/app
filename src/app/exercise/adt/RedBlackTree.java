/**
 * This Class provides methods for working with a Red-Black-Tree
 * @author Alexander Wähling 29.05.2017
 * @version 0.1
 */

package app.exercise.adt;
import app.exercise.algebra.*;
import java.util.AbstractCollection;
import app.exercise.visualtree.*;

public class RedBlackTree<T extends Comparable<T>> {// extends java.util.AbstractCollection<E>  {
    /**
     * This is the root of the BST
     */
    private Node root;
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
    class Node implements DrawableTreeElement<T> {
        T data;
        boolean colour;
        Node left;
        Node right;
        Node father;
        public Node(T data){
            this.data = data;
            left = null;
            right = null;
            colour = RED;
            father = null;
        }
        public Node(Node father, T data) {
            this.data = data;
            left = null;
            right = null;
            colour = RED;
            this.father = father;
        }
        public DrawableTreeElement<T> getLeft() {
            return this.left;
        }
        public DrawableTreeElement<T> getRight() {
            return this.right;
        }

        public boolean isRed() {
            return this.colour;
        }

        public T getValue() {
            return this.data;
        }
    }
    /**
     * The Constructor
     */
    public RedBlackTree(){
        this.root = null;
    }

    public Node getRoot() {
        return root;
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
			}else if(current.data.compareTo(id) > 0){
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
                        father.left = new Node(father, id);
                        repair(father.left);
                        return;
                    }
                }else{
                    current = current.right;
                    if(current==null){
                        father.right = new Node(father, id);
                        repair(father.right);
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
        rotate.left.left = swap.left;
        rotate.left.right = swap.right.left;
        rotate.right = swap.right.right;
        if(rotate.father == null)  {
            this.root = rotate;
        }
    }

    private void rotateRight(Node rotate) {
        Node swap = rotate;
        rotate = rotate.left;
        rotate.right = swap;
        rotate.right.right = swap.right;
        rotate.right.left = swap.left.right;
        rotate.left = swap.left.left;
        if(rotate.father == null)  {
            this.root = rotate;
        }
    }

    private Node findUncle(Node start) {
        if(start.father.father.left.data.equals(start.father)) {
          return start.father.father.right;
        }
        if(start.father.father.right.data.equals(start.father)) {
          return start.father.father.left;
        }
        return start.father.father.left;
    }
    private void repair(Node start) {
        if(start.father.colour == BLACK || start == root) {
            return;
        }

        if(start.father == root && (start.father).colour == RED) {
            start.father.colour = BLACK;
            return;
        }

        if(start.father.father.right.colour == RED && (start.father).father.left.colour == RED) {
            start.father.father.colour = RED;
            start.father.father.right.colour = BLACK;
            start.father.father.left.colour = BLACK;
            return;
        }

        if(start.father.colour == RED && findUncle(start).colour == RED) {

            if(start.father.father.left.data.compareTo(start.father.data) == 0 && 
            start.father.right.data.compareTo(start.data) == 0) {
                rotateLeft(start.father);
                fourthree(start.father);
                return;
            }

            if(start.father.father.right.data.compareTo(start.father.data) == 0 && 
            start.father.left.data.compareTo(start.data) == 0) {
                rotateRight(start.father);
                fourfour(start.father);
                return;            
            }

            if(start.father.father.left.data.compareTo(start.father.data) == 0 && 
            start.father.left.data.compareTo(start.data) == 0) {
                fourthree(start);
            }

            if(start.father.father.right.data.compareTo(start.father.data) == 0 && 
            start.father.right.data.compareTo(start.data) == 0) {
                fourfour(start);
            }

        }
    }

    private void fourthree(Node start) {
        boolean swap;
        swap = start.father.colour;
        start.father.colour = start.father.father.colour;
        start.father.father.colour = swap;
        rotateRight(start.father.father);
    }

    private void fourfour(Node start) {
        boolean swap;
        swap = start.father.colour;
        start.father.colour = start.father.father.colour;
        start.father.father.colour = swap;
        rotateLeft(start.father.father);
    }

    private void delete(Node del) {

    }

    private Node follow(Node start) {
        Node current = start;
        while(current != null) {
            if(current.left != null) {
                current = current.left;
            } else {
                return current;
            }

        }
        return current;
    }
}
