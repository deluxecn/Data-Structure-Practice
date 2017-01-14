import java.util.ArrayDeque;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;

/**
 * BST class for HW6.
 * @author Luxiao Ding (id: luxiaod)
 * @param <T> Generic type
 */
public class BST<T extends Comparable<T>> implements Iterable<T>, BSTInterface<T> {
    /**
     * Root node.
     */
    private Node<T> root;
    /**
     * Comparator of the tree.
     */
    private Comparator<T> comparator;

    /**
     * No-arg constructor.
     */
    public BST() {
        this(null);
    }

    /**
     * Constructor with specified comparator.
     * @param comp specified comparator
     */
    public BST(Comparator<T> comp) {
        comparator = comp;
        root = null;
    }

    /**
     * Get the comparator.
     * @return comparator
     */
    public Comparator<T> comparator() {
        return comparator;
    }

    /**
     * Get the root node of this BST.
     * @return root node of this BST.
     */
    public T getRoot() {
        if (root == null) {
            return null;
        } else {
            return root.data;
        }
    }

    /**
     * Get the height of this BST.
     * @return height of this BST.
     */
    public int getHeight() {
        if (root == null) {
            return 0;
        }
        return getHeightHelper(root);
    }

    /**
     * Helper method used to get the height recursively.
     * @param r current node
     * @return height of current node
     */
    private int getHeightHelper(Node<T> r) {
        if (r == null) {
            return -1;
        } else {
            return 1 + Math.max(getHeightHelper(r.left),
                    getHeightHelper(r.right));
        }
    }

    /**
     * Get the number of nodes in this BST.
     * @return number of nodes in this BST.
     */
    public int getNumberOfNodes() {
        return getNumberOfNodesHelper(root);
    }

    /**
     * Helper method used to get the number of nodes in this BST.
     * @param r current node
     * @return number of nodes so far
     */
    private int getNumberOfNodesHelper(Node<T> r) {
        if (r == null) {
            return 0;
        } else {
            return 1 + getNumberOfNodesHelper(r.left)
                    + getNumberOfNodesHelper(r.right);
        }
    }

    @Override
    public T search(T toSearch) {
        return searchHelper(root, toSearch);
    }

    /**
     * Helper method used to search a node.
     * @param r current node
     * @param toSearch the node to look for
     * @return the data of the node to be searched for
     */
    private T searchHelper(Node<T> r, T toSearch) {
        if (r == null || toSearch == null) {
            return null;
        }
        int res = 0;
        if (comparator == null) {
            res = toSearch.compareTo(r.data);
        } else {
            res = comparator.compare(toSearch, r.data);
        }

        if (res == 0) {
            return toSearch;
        } else if (res < 0) {
            return searchHelper(r.left, toSearch);
        } else {
            return searchHelper(r.right, toSearch);
        }
    }

    @Override
    public void insert(T toInsert) {
        if (toInsert == null) {
            return;
        }
        if (root == null) {
            root = new Node<T>(toInsert);
            return;
        }
        insertHelper(root, root, toInsert, 0);
    }

    /**
     * Helper method used to insert a node.
     * @param parent parent node
     * @param cur current node
     * @param toInsert the node to be inserted
     * @param comp used to decide if current node is left or right child of the
     *            parent node
     */
    private void insertHelper(Node<T> parent, Node<T> cur, T toInsert,
            int comp) {
        if (cur == null) {
            if (comp < 0) {
                parent.left = new Node<T>(toInsert);
            } else if (comp > 0) {
                parent.right = new Node<T>(toInsert);
            }
            return;
        }
        int res = 0;
        if (comparator == null) {
            res = toInsert.compareTo(cur.data);
        } else {
            res = comparator.compare(toInsert, cur.data);
        }

        if (res < 0) {
            insertHelper(cur, cur.left, toInsert, res);
        } else if (res > 0) {
            insertHelper(cur, cur.right, toInsert, res);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new BSTIterator();
    }

    /**
     * Nested class that impelments Iterator.
     * @author Luxao Ding (id: lxuiaod)
     */
    private class BSTIterator implements Iterator<T> {
        /**
         * Nest node of the iterator.
         */
        private Node<T> nextNode;
        /**
         * A stack that stores parent nodes.
         */
        private Deque<Node<T>> dq = new ArrayDeque<Node<T>>();

        /**
         * No-arg constructor.
         */
        BSTIterator() {
            if (root == null) {
                nextNode = null;
                return;
            }
            Node<T> cur = root;
            while (cur != null) {
                dq.push(cur);
                cur = cur.left;
            }
            nextNode = dq.poll();
            if (nextNode != null) {
                cur = nextNode.right;
                if (cur != null) {
                    dq.push(cur);
                    cur = cur.left;
                    while (cur != null) {
                        dq.push(cur);
                        cur = cur.left;
                    }
                }
            }
        }

        @Override
        public boolean hasNext() {
            return nextNode != null;
        }

        @Override
        public T next() {
            if (!hasNext()) {
                return null;
            }
            T ret = nextNode.data;
            nextNode = dq.poll();
            if (nextNode != null) {
                Node<T> cur = nextNode.right;
                if (cur != null) {
                    dq.push(cur);
                    cur = cur.left;
                    while (cur != null) {
                        dq.push(cur);
                        cur = cur.left;
                    }
                }
            }
            return ret;
        }
    }

    /**
     * Node class.
     * @author Luxiao Ding (id: luxiaod)
     * @param <T> Generic type
     */
    private static class Node<T> {
        /**
         * Data of the node.
         */
        private T data;
        /**
         * Left child.
         */
        private Node<T> left;
        /**
         * Right child.
         */
        private Node<T> right;

        /**
         * Constructor with data value.
         * @param d data value
         */
        Node(T d) {
            this(d, null, null);
        }

        /**
         * Constructor with data, left child, and right child.
         * @param d data value
         * @param l left child
         * @param r right child
         */
        Node(T d, Node<T> l, Node<T> r) {
            data = d;
            left = l;
            right = r;
        }

        @Override
        public String toString() {
            return data.toString();
        }
    }

}
