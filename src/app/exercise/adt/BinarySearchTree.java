/**
 * This Class provides methods for working with a binary search tree
 * @author Alexander Wähling 17.05.2017
 * @version 0.1
 */

package app.exercise.adt;

public class BinarySearchTree<T extends Comparable<T>> {
    /**
     * This is the root of the BST
     */
    public Node root;
    private String rstr=" ", str=" ";
    /**hex rechner online
     * The Constructor
     */
   
    public BinarySearchTree(){
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
                root = newNode;
                return;
            }
            Node current = root;
            Node parent = null;
            while(true){
                parent = current;
                if(id.compareTo(current.data) < 0){				
                    current = current.left;
                    if(current==null){
                        parent.left = newNode;
                        return;
                    }
                }else{
                    current = current.right;
                    if(current==null){
                        parent.right = newNode;
                        return;
                    }
                }
            }
        } else {
            throw new IllegalArgumentException();
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

    /**
     * The recursive function toString returns the Values of the
     * BST sorted. It needs a starting node as parameter
     * @return The BST as String sorted
     * @param node the starting node
     */
	public String toString(Node node){
		if(node!=null){
			toString(node.left);
            str += " " + ((node.data).toString());
			toString(node.right);
		} 
        return str;
	}
    /**
     * The ToString-method Overrides toString in the superclass and uses the overloading toString-Method
     * to return the BST as String sorted.
     */
    @Override
    public String toString() {
        return toString(root);
    }

    /**
     * The recursive function toReversedString returns the Values of the
     * BST reversed sorted. It needs a starting node as parameter
     * @return The BST as String revered sorted
     * @param node the starting node
     */
    public String toReverseString(Node node) {
        if(node != null){
            toReverseString(node.right);
            rstr += " " + ((node.data).toString());
            toReverseString(node.left);
        }
        return rstr;
    }

    /**
     * The ToReversedString-method uses the overloading toString-Method
     * to return the BST as String reversed sorted.
     */
    public String toReverseString(){
         return toReverseString(root);
    }

    /**
     * This inner-Class implements Node-Objects, wich are used for implementing Trees
     */
    class Node {
        T data;
        Node left;
        Node right;	
        public Node(T data){
            this.data = data;
            left = null;
            right = null;
        }
    }
}

