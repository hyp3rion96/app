package app.exercise.adt;

import app.exercise.visualtree.DrawableTreeElement;

import java.util.AbstractCollection;
import java.util.ArrayDeque;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Class that implements a height balanced tree (a red-black-tree). Can be used for any type of Object E that implement Comparable"<"E">".
 * @author Alexander Wähling
 * @version 0.9
 */
public class RedBlackTree<E extends Comparable<E>> extends AbstractCollection<E> {
    /**
     *  boolean constant BLACK that stores the boolean value that represents the colour black (false)
     */
    private static final boolean BLACK = false;
    /**
     *  boolean constant RED that stores the boolean value that represents the colour red (true)
     */
    private static final boolean RED = true;

    /**
     * inner Node-class that is used to as data containers for the red-black tree
     */
    class Node implements DrawableTreeElement<E> {
        /**
         * Reference to the Node containing the left subtree (left meaning smaller by compareTo()
         */
        Node left;
        /**
         * Reference to the Node containing the right subtree (right meaning greater by compareTo()
         */
        Node right;
        /**
         * Reference to the parent Node
         */
        Node father;
        /**
         * Boolean variable that is used to store the colour of an individual Node.
         */
        Boolean colour;
        /**
         * Variable of type E that stores the data inside the Node.
         */
        E data;

        /**
         * default constructor creating a new Node object with colour = BLACK.
         */
        Node() {
            colour = BLACK;
        }

        /**
         * Constructs a new empty Node with colour black and sets it's father to the reference of the passed Node.
         * @param father Node to set as father for instantiated one.
         */
        Node(Node father) {
            colour = BLACK;
            this.father = father;
        }

        /**
         * Construct a new Node that holds passed data, whose father is the passed Node. Colour is set to RED.
         * @param data  data to store inside Node
         * @param father    Node to set as father for instantiated one
         */
        Node(E data, Node father) {
            this.data = data;
            colour = RED;
            this.father = father;
            left = new Node(this);
            right = new Node(this);
        }

        /**
         * Returns colour of current Node object.
         * @return colour of Node object. If colour = null, returns BLACK
         */
        boolean getColour() {
            return Boolean.TRUE.equals(colour);
        }

        /**
         * Replaces current Node with the passed Node inside the tree by setting child variables and father variable of parent und passed
         * Node respectively. Should only be called on Nodes with no childs except Null leafs.
         * @param p Node to replace current one with
         * @return reference to Node that replaced current one
         */
        Node replaceWith(Node p) {
            p.father = father;
            if(this == father.left) {
                father.left = p;
            } else {
                father.right = p;
            }
            if(this == root) {
                setAnchor(p);
                root = p;
            }
            return p;
        }

        /**
         * Returns brother of current Node. Can be Null-Data Leaf.
         * @return brother of current Node.
         */
        Node getBrother() {
            if(this == father.left)
                return father.right;
            else
                return father.left;
        }
        /**
         * Returns grandfather of current Node.
         * @return grandfather of current Node.
         */
        Node getGrandfather() {
            if(father != null)
                return father.father;
            else
                return null;
        }
        /**
         * Returns uncle of current Node. Can be Null-Data Leaf.
         * @return uncle of current Node.
         */
        Node getUncle() {
            if (father == getGrandfather().left)
                return getGrandfather().right;
            else
                return getGrandfather().left;
        }

        /**
         * Inserts data into tree with current Node as root by recursively calling insert() and after completed insertions
         * calls repairInsert() to repair properties of red-black-tree
         * @param data  data to be inserted into tree
         * @throws DuplicateElementException in case data to be inserted is already stored in tree.
         */
        void insert(E data) {
            if(this.data == null) {
                // can only be the case if it's the first piece of data to be inserted
                root = new Node(data, anchor);
            } else if (this.data.compareTo(data) == 0) {
                throw new DuplicateElementException(data + " Cannot store duplicate elements.");
            } else if (data.compareTo(this.data) < 0) {
                // if left Node is a leaf, replace leaf with leafed Node containing data
                if(left.data == null) {
                    left = new Node(data, this);
                    repairInsert(left);
                } else {
                    left.insert(data);
                }
            } else {
                if(right.data == null) {
                    right = new Node(data, this);
                    repairInsert(right);
                } else {
                    right.insert(data);
                }
            }
        }

        /**
         * Checks if passed data is already stored in tree by recursively calling contains() on child Nodes depending on the result of this.data.compareTo(data).
         * @param data  data to be checked if it's inside the tree
         * @return  true if data is present in tree, false otherwise
         */
        boolean contains(E data) {
            if(this.data != null) {
                if(this.data.compareTo(data) == 0) {
                    return true;
                } else if(data.compareTo(this.data) < 0) {
                    return left.contains(data);
                } else if(data.compareTo(this.data) > 0) {
                    return right.contains(data);
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }

        /**
         * Returns left child of current Node.
         * @return left child
         */
        @Override
        public Node getLeft() {
            return left;
        }
        /**
         * Returns right child of current Node.
         * @return right child
         */
        @Override
        public Node getRight() {
            return right;
        }

        /**
         * Returns if colour of Node is Red or not.
         * @return true, if colour is red, false otherwise
         */
        @Override
        public boolean isRed() {
            return colour;
        }

        /**
         * Returns data stored in Node.
         * @return  data field of Node object
         */
        @Override
        public E getValue() {
            return data;
        }

    }



    /*-----------------------------------------------------------------------------------------------------------------*/


    /**
     * anchor always points to root. Does not hold any data.
     */
    private Node anchor;
    /**
     * Reference to the current root of the red black tree.
     */
    private Node root;
    /**
     * Keeps track of the current size of the red black tree.
     */
    private int size = 0;

    /**
     * Default constructor that initializes a new RedBlackTree by properly creating and setting the anchor and root Nodes.
     */
    public RedBlackTree() {
        anchor = new Node();
        root = new Node(anchor);
        anchor.left = root;
        anchor.right = root;
    }

    /**
     * Returns current black height of the red black tree.
     * @return black height if the tree
     */
    public int getBlackHeight() {
        int depth = 0;
        Node pointer = root;
        do {
            if(pointer.getColour() == BLACK)
                depth++;
            pointer = pointer.left;
        } while (pointer != null);
        return depth;
    }

    /**
     * Returns current root of the tree.
     * @return root of tree
     */
    public Node getRoot() {
        return root;
    }

    /**
     * Returns Node whose data is the least greatest compared to passed Node. In other words, returns the Node whose data is the next greatest compared to passed.
     * @param p Node to find closest Node to
     * @return closest Node in ascending order from p
     */
    private Node getClosestNode(Node p) {
        Node curr = p.right;
        while (curr.left.data != null)
            curr = curr.left;
        return curr;
    }
    /**
     * Returns Node whose data is the least less compared to passed Node. In other words, returns the Node whose data is the next small compared to passed Node.
     * @param p Node to find closest Node to
     * @return closest Node in descending order from p
     */
    private Node rGetClosestNode(Node p) {
        Node curr = p.left;
        while(curr.right.data != null)
            curr = curr.right;
        return curr;
    }

    /**
     * sets the child references of the anchor to the passed Node, as well as the father reference of the passed Node to anchor. The passed
     * Node should always be the root of the RedBlackTree
     * @param root Node that anchor should point to.
     */
    private void setAnchor(Node root) {
        anchor.left = root;
        anchor.right = root;
        root.father = anchor;
    }

    /**
     * swaps the colours of the two passed Node objects
     */
    private void swapColours(Node o, Node p) {
        boolean clr = o.getColour();
        o.colour = p.getColour();
        p.colour = clr;
    }

    /**
     * Repairs the properties of the RedBlackTree after insertion.
     * @param p problematic Node to start repair from
     */
    private void repairInsert(Node p) {
        if(p != root && p.father.colour != BLACK) {
            if(p.father == root && root.colour == RED) {
                // FALL 2
                root.colour = BLACK;
            } else if(p.getGrandfather().left.colour == RED && p.getGrandfather().right.colour == RED) {
                // FALL 3
                p.getGrandfather().colour = RED;
                p.getGrandfather().left.colour = BLACK;
                p.getGrandfather().right.colour = BLACK;
                repairInsert(p.getGrandfather());
            } else if(p.father.colour == RED && p.getUncle().colour == BLACK) {
                // FALL 4
                if (p.father == p.getGrandfather().left && p == p.father.right) {
                    // FALL 4.1
                    leftRotate(p.father);
                    repairInsert(p.left);
                } else if (p.father == p.getGrandfather().right && p == p.father.left) {
                    // FALL 4.2
                    rightRotate(p.father);
                    repairInsert(p.right);
                } else if (p.father == p.getGrandfather().left && p == p.father.left) {
                    // FALL 4.3
                    swapColours(p.father, p.getGrandfather());
                    rightRotate(p.getGrandfather());
                } else if (p.father == p.getGrandfather().right && p == p.father.right) {
                    // FALL 4.4
                    swapColours(p.father, p.getGrandfather());
                    leftRotate(p.getGrandfather());
                }
            }
        }
    }

    /**
     * Detes passed Node from RedBlackTree. Calls repairDelete() after insertion to restore the properties of the RedBlackTree.
     * @param p Node to delete from tree.
     */
    private void delete(Node p) {
        if(p.left.data == null && p.right.data == null) {
            // FALL 1
            boolean clr = p.colour;
            Node probl = p.replaceWith(new Node(p.father));
            if(clr == BLACK)
                repairDelete(probl);
        } else if(p.left.data == null ^ p.right.data == null) {
            // FALL 2
            if(p.left.data == null) {
                boolean clr = p.colour;
                Node probl = p.replaceWith(p.right);
                if(clr == BLACK)
                    repairDelete(probl);
            } else {
                boolean clr = p.colour;
                Node probl = p.replaceWith(p.left);
                if(clr == BLACK)
                    repairDelete(probl);
            }
        } else {
            // FALL 3
            // no child is leaf
            Node closest = getClosestNode(p);
            p.data = closest.data;
            delete(closest);
        }


    }

    /**
     * Restores properties of the RedBlackTree after a deletion.
     * @param p problematic Node to start repair from.
     */
    private void repairDelete(Node p) {
        if(p != root) {
            if (p.getColour() == RED) {
                // Schleifenanfang 1.
                p.colour = BLACK;
            } else if(p == p.father.left) {
                // FALL 1
                if(p.getBrother().getColour() == RED) {
                    // FALL 1.1
                    p.getBrother().colour = BLACK;
                    p.father.colour = RED;
                    leftRotate(p.father);
                }
                if(p.getBrother().right != null && p.getBrother().left != null && p.getBrother().right.getColour() == BLACK && p.getBrother().left.getColour() == BLACK) {
                    // FALL 1.2 return to Schleifenanfang
                    p.getBrother().colour = RED;
                    repairDelete(p.father);
                } else {
                    if(p.getBrother().left.getColour() == RED) {
                        // FALL 1.3
                        p.getBrother().colour = RED;
                        p.getBrother().left.colour = BLACK;
                        rightRotate(p.getBrother());
                    }
                    // FALL 1.4
                    swapColours(p.getBrother(), p.father);
                    p.getBrother().right.colour = BLACK;
                    leftRotate(p.father);
                }
            } else {
                // FALL 2
                if(p.getBrother().getColour() == RED) {
                    // FALL 2.1
                    p.getBrother().colour = BLACK;
                    p.father.colour = RED;
                    rightRotate(p.father);
                }
                if(p.getBrother().right != null && p.getBrother().left != null && p.getBrother().right.getColour() == BLACK && p.getBrother().left.getColour() == BLACK) {
                    // FALL 2.2
                    p.getBrother().colour = RED;
                    repairDelete(p.father);
                } else if(p.getBrother().right != null) {
                    if(p.getBrother().right != null && p.getBrother().right.getColour() == RED) {
                        // FALL 2.3
                        p.getBrother().colour = RED;
                        p.getBrother().right.colour = BLACK;
                        leftRotate(p.getBrother());
                    }
                    // FALL 2.4
                    swapColours(p.getBrother(), p.father);
                    p.getBrother().left.colour = BLACK;
                    rightRotate(p.father);
                }
            }
        }
    }

    /**
     * Performs a left rotation of the passed Node.
     * @param p Node to leftroatte.
     */
    private void leftRotate(Node p) {
        if(p != root) {
            if(p.father.left == p)
                p.father.left = p.right;
            else
                p.father.right = p.right;
            Node father = p.father;
            p.father = p.right;
            p.right = p.father.left;
            p.father.left = p;
            p.right.father = p;
            p.father.father = father;
        } else {
            root = p.right;
            p.father = root;
            p.right = root.left;
            root.left = p;
            p.right.father = p;
            setAnchor(root);
        }
    }
    /**
     * Performs a right rotation of the passed Node.
     * @param p Node to right rotate.
     */
    private void rightRotate(Node p) {
        if(p != root) {
            if (p.father.left == p)
                p.father.left = p.left;
            else
                p.father.right = p.left;
            Node father = p.father;
            p.father = p.left;
            p.left = p.father.right;
            p.father.right = p;
            p.left.father = p;
            p.father.father = father;
        } else {
            root = p.left;
            p.father = root;
            p.left = root.right;
            root.right = p;
            p.left.father = p;
            setAnchor(root);
        }
    }

    /**
     * Returns whether passed data is stored inside RedBlackTree by calling the node.contains() Method on the current root.
     * @param element element to look for in tree
     * @return  true if element
     */
    public boolean contains(E element) {
        return root.contains(element);
    }

    /**
     * Returns the Node wih the minimal data inside.
     * @return Minimum Node
     */
    private Node minNode() {
        Node min = root;
        if(root.data != null) {
            while (min.left.data != null) {
                min = min.left;
            }
            return min;
        } else {
            return null;
        }
    }

    /**
     * Returns the Node with the maximum data inside.
     * @return  Maximum Node
     */
    private Node maxNode() {
        Node max = root;
        if(root.data != null) {
            while(max.right.data != null) {
                max = max.right;
            }
            return max;
        } else {
            return null;
        }
    }

    /**
     * Add passed Data into RedBlackTree by calling the insert Method of the current root with data to be inserted as argument.
     * Null object cannot be stored inside the tree. Trying so will throw a NullPointerException. Also returns a DuplicateElementException if the data is already stored inside the tree.
     * @param data  data to insert into tree.
     * @return  Returns true in insertion was successful.
     * @throws NullPointerException if the data to be stored is null
     * @throws DuplicateElementException if the data is already stored inside the tree
     */
    @Override
    public boolean add(E data) {
        if(data == null) {
            throw new NullPointerException("Cannot store null elements.");
        } else {
            root.insert(data);
            size++;
            return true;
        }
    }

    /**
     * Returns the current size (number of non-equivalent data objects) stored inside the tree.
     * @return  number of stored elements.
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns an in-order iterator to the current RedBlackTree.
     * @return  in-order iterator.
     */
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            ArrayDeque<Node> usedStack = new ArrayDeque<>();
            Node cursor = null;
            Node max = maxNode();
            boolean removable = false;

            /**
             * Returns whether next() will return a new element of the tree or throw an exception.
             * @return true if tree has more elements to be iterated over, false otherwise
             */
            @Override
            public boolean hasNext() {
                return cursor != max;
            }

            /**
             * Returns next element stored inside RedBlackTree by natural ordering.
             * @return net element
             */
            @Override
            public E next() {
                if(cursor == null) {
                    cursor = minNode();
                    usedStack.push(cursor);
                    removable = true;
                    return cursor.data;
                } else {
                    if(hasNext()) {
                        if(cursor.right.data != null) {
                            cursor = getClosestNode(cursor);
                        } else if(cursor != root) {
                            while (cursor.equals(usedStack.peek()) ) {
                                cursor = cursor.father;
                                usedStack.pop();
                            }
                        }
                        usedStack.push(cursor);
                        removable = true;
                        return cursor.data;
                    } else {
                        throw new NoSuchElementException("No more elements in RedBlackTree.");
                    }
                }
            }

            /**
             * Deletes last element returned by next(). Can only be called ONCE after a call to next().
             */
            @Override
            public void remove() {
                if(removable) {
                    delete(usedStack.peek());
                    size--;
                    removable = false;
                } else {
                    throw new IllegalStateException("Next method hasn't been called or remove has already been called after last next call.");
                }
            }
        };
    }

    /**
     * Returns an reverse in-order iterator for the RedBlackTree.
     * @return  reverse in-order iterator
     */
    public Iterator<E> rIterator() {
        return new Iterator<E>() {
            ArrayDeque<Node> usedStack = new ArrayDeque<>();
            Node cursor = null;
            Node min = minNode();
            boolean removable = false;

            /**
             * Returns whether next() will return a new element of the tree or throw an exception.
             * @return true if tree has more elements to be iterated over, false otherwise
             */
            @Override
            public boolean hasNext() {
                return cursor != min;
            }
            /**
             * Returns next element stored inside RedBlackTree by reversed natural ordering.
             * @return net element
             */
            @Override
            public E next() {
                if(cursor == null) {
                    cursor = maxNode();
                    usedStack.push(cursor);
                    removable = true;
                    return cursor.data;
                } else {
                    if(hasNext() ) {
                        if(cursor.left.data != null) {
                            cursor = rGetClosestNode(cursor);
                        } else if(cursor != root) {
                            while (cursor == usedStack.peek() ) {
                                cursor = cursor.father;
                                usedStack.pop();
                            }
                        }
                        usedStack.push(cursor);
                        removable = true;
                        return cursor.data;
                    } else {
                        throw new NoSuchElementException("No more elements in RedBlackTree");
                    }
                }
            }
            /**
             * Deletes last element returned by next(). Can only be called ONCE after a call to next().
             */
            @Override
            public void remove() {
                if(removable) {
                    delete(usedStack.peek());
                    size--;
                    removable = false;
                } else {
                    throw new IllegalStateException("Next method hasn't been called or remove has already been called after last next call.");
                }
            }
        };
    }
}
