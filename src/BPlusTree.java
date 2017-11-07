import NodeStructure.*;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Main B+ tree structure, supporting insert(key, value), search(key) and search(key1, key2)
 */
public class BPlusTree {

    private Node root;  // root of B+ tree, can be IndexNode or DataNode
    private int m;      // order of B+ tree

    /** construct a new B+ tree with order m */
    public BPlusTree(int m) {
        this.m = m;
    }

    /**
     * insert a new key-val pair to the tree
     * @param key
     * @param val
     */
    public void insert(double key, String val) {
        Pair pair = new Pair(key, val);

        // if currently there is no data, just create a new DataNode as root
        if (root == null) root = new DataNode(m, pair);

        // else recursively insert from root to DataNode
        else insert(root, pair);
        if (root.isOverfull()) { // create a new root if it is overfull
            //System.out.println("root is overfull!");
            IndexNode temp = root.split();
            temp.addToFirst(root);
            root = temp;
            //System.out.println("create new root: " + root);
        }
        //printTree();
    }

    /* recursively insert a Pair to this subtree */
    private void insert(Node node, Pair pair) {

        // if current node is a data node, insert pair to it
        if (node.isDataNode()) {
            node.insert(pair);
        }

        // if current node is a index node, search next node
        else {
            Node nextNode = node.searchChild(pair);
            insert(nextNode, pair);
            // if next node becomes overfull, split it and merge current node with new entry
            if (nextNode.isOverfull()) {
                IndexNode newNode = nextNode.split();
                node.mergeWith(newNode);
            }
        }

    }

    /**
     * search for all values corresponding to given key
     * @param key
     * @return a list of all values corresponding to this key, void if search miss
     */
    public ArrayList<String> search(double key) {

        ArrayList<String> vals = new ArrayList<>();
        if (root == null) return vals; // B+ tree is void, return nothing

        // go all the way down along the path to get to the data node
        Node node = root;
        while (!node.isDataNode()) {
            node = node.searchChild(key);
        }

        // add matching values from data node and its left neighbor
        node.search(key, vals);
        return vals;

    }

    /**
     * search for all the pairs whose key are between key1 and key2
     * @param key1 the small key
     * @param key2 the large key
     * @return a list of pairs whose keys are between key1 (include) and key2 (include)
     */
    public ArrayList<Pair> search(double key1, double key2) {

        ArrayList<Pair> pairs = new ArrayList<>();
        if (root == null) return pairs;

        // use key2 to find one data node
        Node node = root;
        while (!node.isDataNode()) {
            node = node.searchChild(key2);
        }

        // using the doubly linked list to add all pairs within the range
        node.search(key1, key2, pairs);
        return pairs;

    }

    /* tree representation for debug */
    private void printTree() {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            String line = "";
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Node node = queue.poll();
                line += "<" + node + ">";
                if (!node.isDataNode()) {
                    for (Node child : ((IndexNode) node).getChildren()) {
                        queue.offer(child);
                    }
                }
            }
            System.out.println(line);
        }
    }

}
